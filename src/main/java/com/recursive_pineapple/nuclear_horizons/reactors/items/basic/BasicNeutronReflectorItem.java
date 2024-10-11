package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.NeutronReflectorAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.INeutronReflector;

public class BasicNeutronReflectorItem extends ReactorItem implements INeutronReflector {

    @Nullable
    private ItemStack product;

    public BasicNeutronReflectorItem(String name, String textureName) {
        super(name, textureName, "damage", 0);
    }

    public BasicNeutronReflectorItem(String name, String textureName, int maxHealth) {
        super(name, textureName, "damage", maxHealth);
    }

    public void setProduct(@Nullable ItemStack product) {
        this.product = product;
    }

    @Override
    public @Nullable ItemStack getProduct() {
        return product;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new NeutronReflectorAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public boolean canReflectNeutrons(@Nonnull ItemStack itemStack) {
        return this.getRemainingHealth(itemStack) > 0;
    }

    @Override
    public void applyDamage(@Nonnull ItemStack itemStack, int damage) {
        itemStack.setItemDamage(itemStack.getItemDamage() + damage);
    }

    @Override
    protected boolean hasMoreInfo(ItemStack itemStack) {
        return false;
    }

    @Override
    protected String getDurabilityTooltip(ItemStack itemStack) {
        if (itemStack.getMaxDamage() == -1) {
            return I18n.format("nh_tooltip.undestructable");
        } else {
            return I18n.format("nh_tooltip.durability", this.getRemainingHealth(itemStack), itemStack.getMaxDamage());
        }
    }
}
