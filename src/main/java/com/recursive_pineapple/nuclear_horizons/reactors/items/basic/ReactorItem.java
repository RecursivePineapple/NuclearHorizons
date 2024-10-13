package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

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
import com.recursive_pineapple.nuclear_horizons.reactors.items.HeatUtils;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ReactorItem extends Item implements IComponentAdapterFactory {

    protected final String name;
    protected final String damageField;

    public ReactorItem(String name, String textureName, String damageField, int maxHealth) {
        setUnlocalizedName(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
        setMaxDamage(maxHealth);

        this.name = name;
        this.damageField = damageField;
    }

    public void register() {
        GameRegistry.registerItem(this, name);
        ComponentRegistry.registerAdapter(this, this);
    }

    @Override
    public int getDamage(ItemStack stack) {
        if (!stack.isItemStackDamageable()) {
            return 0;
        }

        return HeatUtils.getNBTInt(stack, damageField, 0);
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        HeatUtils.setNBTInt(stack, damageField, damage);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return ((double) stack.getItemDamage()) / ((double) stack.getMaxDamage());
    }

    @Override
    public int getDisplayDamage(ItemStack stack) {
        return stack.getItemDamage();
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return stack.getItemDamage() > 0;
    }

    public int getRemainingHealth(@Nonnull ItemStack itemStack) {
        if (!itemStack.isItemStackDamageable()) {
            return 1;
        }

        return itemStack.getMaxDamage() - itemStack.getItemDamage();
    }

    @Override
    public boolean canAdaptItem(@Nonnull ItemStack itemStack) {
        return itemStack.getItem() == this;
    }

    @Override
    public abstract @Nonnull IComponentAdapter getAdapter(@Nonnull ItemStack itemStack, @Nonnull IReactorGrid reactor,
        int x, int y);

    @Override
    public final void addInformation(ItemStack itemStack, EntityPlayer player, List<String> desc,
        boolean advancedItemTooltips) {
        super.addInformation(itemStack, player, desc, advancedItemTooltips);

        ArrayList<String> chunks = new ArrayList<>();

        if (itemStack.isItemStackDamageable()) {
            chunks.add(getDurabilityTooltip(itemStack));
        } else if (itemStack.getMaxDamage() == -1) {
            chunks.add(I18n.format("nh_tooltip.undestructable"));
        }

        if (hasMoreInfo(itemStack)) {
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                addReactorItemInfo(itemStack, player, chunks);
            } else {
                chunks.add(I18n.format("nh_tooltip.more_info"));
            }
        }

        for (String chunk : chunks) {
            desc.addAll(Arrays.asList(chunk.split("\\\\n")));
        }
    }

    protected String getDurabilityTooltip(ItemStack itemStack) {
        return I18n.format("nh_tooltip.durability", this.getRemainingHealth(itemStack), itemStack.getMaxDamage());
    }

    protected boolean hasMoreInfo(ItemStack itemStack) {
        return true;
    }

    protected void addReactorItemInfo(ItemStack itemStack, EntityPlayer player, List<String> chunks) {

    }
}
