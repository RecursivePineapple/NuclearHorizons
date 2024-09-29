package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.NeutronReflectorAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.items.HeatUtils;
import com.recursive_pineapple.nuclear_horizons.reactors.items.interfaces.INeutronReflector;

import cpw.mods.fml.common.registry.GameRegistry;

public class BasicNeutronReflectorItem extends Item implements INeutronReflector, IComponentAdapterFactory {

    private final String name;
    private final Optional<Integer> maxHealth;
    
    @Nullable
    private ItemStack product;

    public BasicNeutronReflectorItem(String name, String textureName) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);

        this.name = name;
        this.maxHealth = Optional.empty();
    }

    public BasicNeutronReflectorItem(String name, String textureName, int maxHealth) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
        setMaxDamage(maxHealth);

        this.name = name;
        this.maxHealth = Optional.of(maxHealth);
    }

    public void register() {
        GameRegistry.registerItem(this, name);
        ComponentRegistry.registerAdapter(this, this);
    }

    @Override
    public int getDamage(ItemStack stack) {
        if (!maxHealth.isPresent()) {
            return 0;
        }

        return HeatUtils.getNBTInt(stack, "damage", 0);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        HeatUtils.setNBTInt(stack, "damage", maxHealth.isPresent() ? damage : 0);
    }

    public void setProduct(@Nullable ItemStack product) {
        this.product = product;
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x,
        int y) {
        return new NeutronReflectorAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        if (this.maxHealth.isPresent()) {
            return 1;
        } else {
            return this.maxHealth.get() - itemStack.getItemDamage();
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

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc,
        boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        if (this.maxHealth.isPresent()) {
            if (!advancedItemTooltips || itemStack.getItemDamage() == 0) {
                desc.add(I18n.format("nh_tooltip.durability", this.getRemainingHealth(itemStack), this.maxHealth));
            }
        } else {
            desc.add(I18n.format("nh_tooltip.undestructable"));
        }
    }
}
