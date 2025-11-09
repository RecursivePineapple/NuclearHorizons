package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.BreederRodAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.HeatUtils;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.IBreederRod;
import cpw.mods.fml.common.registry.GameRegistry;

public class BasicBreederRodItem extends Item implements IBreederRod, IComponentAdapterFactory {

    private final String name;
    public final int heatDivisor;
    public final int heatMultiplier;
    public final int maxNeutrons;

    @Nullable
    private ItemStack product;

    public BasicBreederRodItem(String name, String textureName, int heatDivisor, int heatMultiplier, int maxNeutrons) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
        setMaxDamage(maxNeutrons);

        this.name = name;
        this.heatDivisor = heatDivisor;
        this.heatMultiplier = heatMultiplier;
        this.maxNeutrons = maxNeutrons;
    }

    public void register() {
        GameRegistry.registerItem(this, name);
        ComponentRegistry.registerAdapter(this, this);
    }

    @Override
    public int getDamage(ItemStack stack) {
        return HeatUtils.getNBTInt(stack, "neutrons", 0);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        HeatUtils.setNBTInt(stack, "neutrons", damage);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0 - ((double) (stack.getItemDamage())) / ((double) maxNeutrons);
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new BreederRodAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public ItemStack getProduct(@Nonnull ItemStack itemStack) {
        return product;
    }

    @Override
    public int getMaxNeutrons(@Nonnull ItemStack itemStack) {
        return maxNeutrons;
    }

    @Override
    public int getStoredNeutrons(@Nonnull ItemStack itemStack) {
        return HeatUtils.getNBTInt(itemStack, "neutrons", 0);
    }

    @Override
    public void setNeutrons(@Nonnull ItemStack itemStack, int neutrons) {
        HeatUtils.setNBTInt(itemStack, "neutrons", neutrons);
    }

    @Override
    public int getReactorHeatDivisor(@Nonnull ItemStack itemStack) {
        return heatDivisor;
    }

    @Override
    public int getHeatMultiplier(@Nonnull ItemStack itemStack) {
        return heatMultiplier;
    }

    public void setProduct(@Nullable ItemStack product) {
        this.product = product;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc,
        boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        desc.add(I18n.format("nh_tooltip.breeder.stored_neutrons", getStoredNeutrons(itemStack), this.maxNeutrons));

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            var product = this.product;

            if (product != null) {
                desc.add(I18n.format("nh_tooltip.breeder.produces", product.stackSize, product.getDisplayName()));
            }

            desc.add(I18n.format("nh_tooltip.breeder.desc"));

            desc.add(I18n.format("nh_tooltip.breeder.heat_mult", heatDivisor * 100, heatMultiplier));
        } else {
            desc.add(I18n.format("nh_tooltip.more_info"));
        }
    }
}
