package com.recursive_pineapple.nuclear_horizons.reactors.items;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.FuelRodAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.foreign.ForeignBreederRodItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.foreign.ForeignHeatAbsorberItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.foreign.ForeignNeutronReflectorItem;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IBasicFuelRod;

import bartworks.system.material.BWNonMetaMaterialItems;
import goodgenerator.items.ItemFuelRod;
import goodgenerator.loader.FuelRodLoader;
import gregtech.api.items.ItemBreederCell;
import gregtech.api.items.ItemCoolantCell;
import gregtech.api.items.ItemCoolantCellIC;
import gregtech.api.items.ItemRadioactiveCellIC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ForeignItems {
    
    public static void registerForeignReactorItems() {
        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_He_1.getItem());
        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_He_3.getItem());
        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_He_6.getItem());

        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_NaK_1.getItem());
        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_NaK_3.getItem());
        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_NaK_6.getItem());

        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_Sp_1.getItem());
        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_Sp_2.getItem());
        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_Sp_3.getItem());
        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.Reactor_Coolant_Sp_6.getItem());

        registerCoolantCell((ItemCoolantCellIC) gregtech.api.enums.ItemList.neutroniumHeatCapacitor.getItem());

        Item iridiumReflector = gregtech.api.enums.ItemList.Neutron_Reflector.getItem();
        ComponentRegistry.registerAdapter(iridiumReflector, new ForeignNeutronReflectorItem(iridiumReflector));

        registerBreederCell((ItemBreederCell) gregtech.api.enums.ItemList.GlowstoneCell.getItem());

        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.Uraniumcell_1.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.Uraniumcell_2.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.Uraniumcell_4.getItem());

        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.Moxcell_1.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.Moxcell_2.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.Moxcell_4.getItem());

        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.ThoriumCell_1.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.ThoriumCell_2.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.ThoriumCell_4.getItem());

        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.NaquadahCell_1.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.NaquadahCell_2.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.NaquadahCell_4.getItem());

        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.MNqCell_1.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.MNqCell_2.getItem());
        registerFuelRod((ItemRadioactiveCellIC) gregtech.api.enums.ItemList.MNqCell_4.getItem());

        registerFuelRod((ItemRadioactiveCellIC) BWNonMetaMaterialItems.TiberiumCell_1.getItem());
        registerFuelRod((ItemRadioactiveCellIC) BWNonMetaMaterialItems.TiberiumCell_2.getItem());
        registerFuelRod((ItemRadioactiveCellIC) BWNonMetaMaterialItems.TiberiumCell_4.getItem());
        registerFuelRod((ItemRadioactiveCellIC) BWNonMetaMaterialItems.TheCoreCell.getItem());

        registerFuelRod((ItemFuelRod) FuelRodLoader.rodCompressedUranium);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodCompressedUranium_2);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodCompressedUranium_4);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodCompressedPlutonium);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodCompressedPlutonium_2);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodCompressedPlutonium_4);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodLiquidUranium);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodLiquidUranium_2);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodLiquidUranium_4);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodLiquidPlutonium);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodLiquidPlutonium_2);
        registerFuelRod((ItemFuelRod) FuelRodLoader.rodLiquidPlutonium_4);
    }

    public static void registerCoolantCell(ItemCoolantCellIC coolantCell) {
        ComponentRegistry.registerAdapter(coolantCell, new GTCoolantCellAdapter(coolantCell));
    }

    public static void registerBreederCell(ItemBreederCell breederCell) {
        ComponentRegistry.registerAdapter(breederCell, new GTBreederCellAdapter(breederCell));
    }

    public static void registerFuelRod(ItemRadioactiveCellIC item) {
        ComponentRegistry.registerAdapter(item, new GTRadioactiveCellAdapter(item));
    }

    public static void registerFuelRod(ItemFuelRod item) {
        ComponentRegistry.registerAdapter(item, new GGItemFuelRodAdapter(item));
    }

    private static <T> Object getField(@Nonnull T object, String name) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("could not read field '" + name + "' on class " + object.getClass().getCanonicalName(), e);
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

                var setHeatForStack = ItemCoolantCell.class.getDeclaredMethod("setHeatForStack", ItemStack.class, int.class);
                setHeatForStack.setAccessible(true);
                SET_HEAT_FOR_STACK = lookup.unreflect(setHeatForStack);
            } catch (NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException("could not hook into ItemCoolantCellIC", e);
            }
        }

        public GTCoolantCellAdapter(ItemCoolantCellIC item) {
            super(item, (int)getField(ItemCoolantCell.class, item, "heatStorage"), false);
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
                return (int)GET_HEAT_OF_STACK.invoke(itemStack);
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
            super(item,
                (int) getField(item, "mHeatBonusStep"),
                (int) getField(item, "mHeatBonusMultiplier"),
                item.getMaxDamage());
        }

        @Override
        public ItemStack getProduct(@Nonnull ItemStack itemStack) {
            try {
                return (ItemStack)PRODUCT.invoke((ItemBreederCell)itemStack.getItem());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class GTRadioactiveCellAdapter implements IBasicFuelRod, IComponentAdapterFactory {

        private final ItemRadioactiveCellIC item;

        public GTRadioactiveCellAdapter(ItemRadioactiveCellIC item) {
            this.item = item;
        }

        @Override
        public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
            return itemStack.getItem() == item;
        }

        @Override
        public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x, int y) {
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
    }

    private static class GGItemFuelRodAdapter implements IBasicFuelRod, IComponentAdapterFactory {

        private final ItemFuelRod item;
        private final int numberOfCells;
        private final float Power;
        private final int Heat;
        private final float HeatBonus;
        private final ItemStack result;

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
    }
}
