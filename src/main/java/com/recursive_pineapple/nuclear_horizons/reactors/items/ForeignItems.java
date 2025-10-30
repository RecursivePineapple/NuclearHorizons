package com.recursive_pineapple.nuclear_horizons.reactors.items;

import static gregtech.api.enums.ItemList.Neutron_Reflector;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import gregtech.api.enums.ItemList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.FuelRodAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.foreign.ForeignBreederRodItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.foreign.ForeignHeatAbsorberItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.foreign.ForeignNeutronReflectorItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IBasicFuelRod;

import gregtech.api.items.ItemBreederCell;
import gregtech.api.items.ItemCoolantCell;
import gregtech.api.items.ItemCoolantCellIC;
import gregtech.api.items.ItemRadioactiveCellIC;

public class ForeignItems {

    public static void registerForeignReactorItems() {
        for (ItemList container : ItemList.values()) {
            if (!container.hasBeenSet()) continue;

            Item item = container.getItem();

            if (item instanceof ItemCoolantCellIC coolantCell) {
                ComponentRegistry.registerAdapter(coolantCell, new GTCoolantCellAdapter(coolantCell));
                continue;
            }

            if (item instanceof ItemBreederCell breederCell) {
                ComponentRegistry.registerAdapter(breederCell, new GTBreederCellAdapter(breederCell));
                continue;
            }

            if (item instanceof ItemRadioactiveCellIC radioactiveCell) {
                ComponentRegistry.registerAdapter(item, new GTRadioactiveCellAdapter(radioactiveCell));
            }
        }

        Item iridiumReflector = Neutron_Reflector.getItem();
        ComponentRegistry.registerAdapter(iridiumReflector, new ForeignNeutronReflectorItem(iridiumReflector));
    }

    private static <T> Object getField(@Nonnull T object, String name) {
        try {
            Field field = object.getClass()
                .getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(
                "could not read field '" + name
                    + "' on class "
                    + object.getClass()
                        .getCanonicalName(),
                e);
        }
    }

    private static <T> Object getField(Class<?> clazz, @Nonnull T object, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("could not read field '" + name + "' on class " + clazz.getCanonicalName(), e);
        }
    }

    public static class GTCoolantCellAdapter extends ForeignHeatAbsorberItem {

        private static final MethodHandle GET_HEAT_OF_STACK, SET_HEAT_FOR_STACK;

        static {
            try {
                var lookup = MethodHandles.lookup();

                var getHeatOfStack = ItemCoolantCell.class.getDeclaredMethod("getHeatOfStack", ItemStack.class);
                getHeatOfStack.setAccessible(true);
                GET_HEAT_OF_STACK = lookup.unreflect(getHeatOfStack);

                var setHeatForStack = ItemCoolantCell.class
                    .getDeclaredMethod("setHeatForStack", ItemStack.class, int.class);
                setHeatForStack.setAccessible(true);
                SET_HEAT_FOR_STACK = lookup.unreflect(setHeatForStack);
            } catch (NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException("could not hook into ItemCoolantCellIC", e);
            }
        }

        public GTCoolantCellAdapter(ItemCoolantCellIC item) {
            super(item, (int) getField(ItemCoolantCell.class, item, "heatStorage"), false);
        }

        @Override
        public int addHeat(@Nonnull ItemStack itemStack, int heat) {
            int consumed = HeatUtils.getConsumableHeat(this.maxHeat, getStoredHeat(itemStack), heat);

            setStoredHeat(itemStack, getStoredHeat(itemStack) + consumed);

            return heat - consumed;
        }

        public void setStoredHeat(@Nonnull ItemStack itemStack, int heat) {
            try {
                SET_HEAT_FOR_STACK.invoke(this.item, itemStack, heat);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public int getStoredHeat(@Nonnull ItemStack itemStack) {
            try {
                return (int) GET_HEAT_OF_STACK.invoke(itemStack);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class GTBreederCellAdapter extends ForeignBreederRodItem {

        private static final MethodHandle PRODUCT;

        static {
            try {
                var lookup = MethodHandles.lookup();

                var mProduct = ItemBreederCell.class.getDeclaredField("mProduct");
                mProduct.setAccessible(true);
                PRODUCT = lookup.unreflectGetter(mProduct);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException("could not hook into ItemBreederCell", e);
            }
        }

        public GTBreederCellAdapter(ItemBreederCell item) {
            super(
                item,
                (int) getField(item, "mHeatBonusStep"),
                (int) getField(item, "mHeatBonusMultiplier"),
                item.getMaxDamage());
        }

        @Override
        public ItemStack getProduct(@Nonnull ItemStack itemStack) {
            try {
                return (ItemStack) PRODUCT.invoke((ItemBreederCell) itemStack.getItem());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class GTRadioactiveCellAdapter implements IBasicFuelRod, IComponentAdapterFactory {

        private final ItemRadioactiveCellIC item;
        private Fluid spargeGas;
        private int spargeMin, spargeMax;

        public GTRadioactiveCellAdapter(ItemRadioactiveCellIC item) {
            this.item = item;
        }

        @Override
        public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
            return itemStack.getItem() == item;
        }

        @Override
        public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
            int y) {
            return new FuelRodAdapter(reactor, x, y, itemStack, this);
        }

        @Override
        public double getMoxEUCoefficient(@Nonnull ItemStack itemStack) {
            return 1.5;
        }

        @Override
        public double getEnergyMult(@Nonnull ItemStack itemStack) {
            return item.sEnergy;
        }

        @Override
        public double getHeatMult(@Nonnull ItemStack itemStack) {
            return item.sHeat * 4;
        }

        @Override
        public int getRodCount(@Nonnull ItemStack itemStack) {
            return item.numberOfCells;
        }

        @Override
        public boolean isMox(@Nonnull ItemStack itemStack) {
            return item.sMox;
        }

        @Override
        public int getRemainingHealth(@Nonnull ItemStack itemStack) {
            return item.getMaxDamageEx() - item.getDamageOfStack(itemStack);
        }

        @Override
        public void applyDamage(@Nonnull ItemStack itemStack, int damage) {
            item.setDamageForStack(itemStack, item.getDamageOfStack(itemStack) + damage);
        }

        @Override
        public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack) {
            return item.sDepleted;
        }

        @Override
        public @org.jetbrains.annotations.Nullable FluidStack getSpargeGas(@NotNull ItemStack itemStack) {
            if (spargeGas == null) return null;

            Random rng = ThreadLocalRandom.current();

            return new FluidStack(spargeGas, (int) (spargeMin + (spargeMax - spargeMin) * rng.nextFloat()));
        }

        public GTRadioactiveCellAdapter setSpargeGas(Fluid fluid, int min, int max) {
            this.spargeGas = fluid;
            this.spargeMin = min;
            this.spargeMax = max;
            return this;
        }
    }

    private static class GGItemFuelRodAdapter implements IBasicFuelRod, IComponentAdapterFactory {

        private final ItemFuelRod item;
        private final int numberOfCells;
        private final float Power;
        private final int Heat;
        private final float HeatBonus;
        private final ItemStack result;
        private Fluid spargeGas;
        private int spargeMin, spargeMax;

        public GGItemFuelRodAdapter(ItemFuelRod item) {
            this.item = item;
            numberOfCells = (int) getField(item, "numberOfCells");
            Power = (float) getField(item, "Power");
            Heat = (int) getField(item, "Heat");
            HeatBonus = (float) getField(item, "HeatBonus");
            result = (ItemStack) getField(item, "result");
        }

        @Override
        public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
            return itemStack.getItem() == item;
        }

        @Override
        public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
            int y) {
            return new FuelRodAdapter(reactor, x, y, itemStack, this) {

                @Override
                protected double getHeatMultiplier() {
                    return 1.0;
                }

                @Override
                protected double getEUMultiplier() {
                    return 1 + HeatBonus * ((float) reactor.getHullHeat() / (float) reactor.getMaxHullHeat());
                }
            };
        }

        @Override
        public double getEnergyMult(@Nonnull ItemStack itemStack) {
            return Power;
        }

        @Override
        public double getHeatMult(@Nonnull ItemStack itemStack) {
            return Heat;
        }

        @Override
        public int getRodCount(@Nonnull ItemStack itemStack) {
            return numberOfCells;
        }

        @Override
        public boolean isMox(@Nonnull ItemStack itemStack) {
            return true;
        }

        @Override
        public double getMoxEUCoefficient(@Nonnull ItemStack itemStack) {
            return HeatBonus;
        }

        @Override
        public double getMoxHeatCoefficient(@Nonnull ItemStack itemStack) {
            return 1.0;
        }

        @Override
        public int getRemainingHealth(@Nonnull ItemStack itemStack) {
            return item.getMaxCustomDamage(itemStack) - item.getCustomDamage(itemStack);
        }

        @Override
        public void applyDamage(@Nonnull ItemStack itemStack, int damage) {
            item.setCustomDamage(itemStack, item.getCustomDamage(itemStack) + damage);
        }

        @Override
        public @Nullable ItemStack getProduct(@Nonnull ItemStack itemStack) {
            return result;
        }

        @Override
        public @org.jetbrains.annotations.Nullable FluidStack getSpargeGas(@NotNull ItemStack itemStack) {
            if (spargeGas == null) return null;

            Random rng = ThreadLocalRandom.current();

            return new FluidStack(spargeGas, (int) (spargeMin + (spargeMax - spargeMin) * rng.nextFloat()));
        }

        public GGItemFuelRodAdapter setSpargeGas(Fluid fluid, int min, int max) {
            this.spargeGas = fluid;
            this.spargeMin = min;
            this.spargeMax = max;
            return this;
        }
    }
}
