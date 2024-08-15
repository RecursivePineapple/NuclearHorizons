package com.recursive_pineapple.nuclear_horizons.reactors.items;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.ReactorPlatingAdapter;

public class BasicReactorPlatingItem extends Item implements IReactorPlating, IComponentAdapterFactory {

    private final double explosionRadiusMultiplier;
    private final int maxHeatIncrease;

    public BasicReactorPlatingItem(String name, String textureName, double explosionRadiusMultiplier,
        int maxHeatIncrease) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);

        this.explosionRadiusMultiplier = explosionRadiusMultiplier;
        this.maxHeatIncrease = maxHeatIncrease;
    }

    @Override
    public double getExplosionRadiusMultiplier(@Nonnull ItemStack itemStack) {
        return explosionRadiusMultiplier;
    }

    @Override
    public int getReactorMaxHeatIncrease(@Nonnull ItemStack itemStack) {
        return maxHeatIncrease;
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new ReactorPlatingAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc,
        boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            if (explosionRadiusMultiplier != 1) {
                desc.add(
                    I18n.format(
                        "nh_tooltip.plating_explosion",
                        (int) Math.round((1 - explosionRadiusMultiplier) * 100)));
            }

            if (maxHeatIncrease != 0) {
                desc.add(I18n.format("nh_tooltip.plating_heat", maxHeatIncrease));
            }
        } else {
            desc.add(I18n.format("nh_tooltip.more_info"));
        }
    }
}
