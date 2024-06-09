package com.recursive_pineapple.nuclear_horizons.reactors.items;

import java.util.List;

import javax.annotation.Nonnull;

import org.lwjgl.input.Keyboard;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapterFactory;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.adapters.NeutronReflectorAdapter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BasicNeutronReflectorItem extends Item implements INeutronReflector, IComponentAdapterFactory {
    
    private final int maxHealth;

    public BasicNeutronReflectorItem(String name, String textureName) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);

        this.maxHealth = 0;
    }

    public BasicNeutronReflectorItem(String name, String textureName, int maxHealth) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
        setMaxDamage(maxHealth);

        this.maxHealth = maxHealth;
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor, int x, int y) {
        return new NeutronReflectorAdapter(reactor, x, y, itemStack, this);
    }

    @Override
    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        if(this.maxHealth == 0) {
            return 1;
        } else {
            return this.maxHealth - itemStack.getItemDamage();
        }
    }

    @Override
    public boolean canReflectNeutrons(@Nonnull ItemStack itemStack) {
        return this.getRemainingHealth(itemStack) > 0;
    }

    @Override
    public void applyDamage(@Nonnull ItemStack itemStack, int damage) {
        if(this.maxHealth > 0) {
            itemStack.setItemDamage(itemStack.getItemDamage() + damage);
        }
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc, boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        if(!advancedItemTooltips) {
            if(this.maxHealth > 0) {
                desc.add(String.format("Durability: %,d / %,d", this.getRemainingHealth(itemStack), this.maxHealth));
            } else {
                desc.add("Invulnerable");
            }
        }
    }
}
