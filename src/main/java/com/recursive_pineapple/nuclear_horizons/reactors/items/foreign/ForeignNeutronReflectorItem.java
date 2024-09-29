package com.recursive_pineapple.nuclear_horizons.reactors.items.foreign;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.NeutronReflectorAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.INeutronReflector;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ForeignNeutronReflectorItem implements INeutronReflector, IComponentAdapterFactory {
    
    @Nonnull
    private final Item item;

    private final Optional<Integer> maxHealth;

    @Nullable
    private ItemStack product;

    public ForeignNeutronReflectorItem(@Nonnull Item item) {
        this.item = item;
        this.maxHealth = Optional.empty();
    }

    public ForeignNeutronReflectorItem(Item item, int maxHealth) {
        this.item = item;
        this.maxHealth = Optional.of(maxHealth);
    }

    public void setProduct(@Nullable ItemStack product) {
        this.product = product;
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == item;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new NeutronReflectorAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        if(this.maxHealth.isPresent()) {
            return this.maxHealth.get() - itemStack.getItemDamage();
        } else {
            return 1;
        }
    }

    @Override
    public boolean canReflectNeutrons(@Nonnull ItemStack itemStack) {
        return this.getRemainingHealth(itemStack) > 0;
    }

    @Override
    public void applyDamage(@Nonnull ItemStack itemStack, int damage) {
        if (this.maxHealth.isPresent()) {
            itemStack.setItemDamage(itemStack.getItemDamage() + damage);
        }
    }

    @Override
    public @Nullable ItemStack getProduct() {
        return product;
    }
}
