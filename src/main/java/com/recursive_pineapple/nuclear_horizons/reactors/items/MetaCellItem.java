package com.recursive_pineapple.nuclear_horizons.reactors.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.recursive_pineapple.nuclear_horizons.Config;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;

import cpw.mods.fml.common.registry.GameRegistry;

public class MetaCellItem extends Item {

    private IIcon[] icons = new IIcon[5];

    public MetaCellItem() {
        setUnlocalizedName("metacell");
        setHasSubtypes(true);
    }

    public void register() {
        GameRegistry.registerItem(this, "metacell");
    }

    @Override
    public void registerIcons(IIconRegister register) {
        icons[0] = register.registerIcon(NuclearHorizons.MODID + ":cellCoolant");
        icons[1] = register.registerIcon(NuclearHorizons.MODID + ":cellHotCoolant");
        icons[2] = register.registerIcon(NuclearHorizons.MODID + ":cellPseudoLiquidNaquadah");
        icons[3] = register.registerIcon(NuclearHorizons.MODID + ":cellHotPseudoLiquidNaquadah");
        icons[4] = register.registerIcon(NuclearHorizons.MODID + ":cellDistilledWater");
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage < 0 || damage >= icons.length) {
            return icons[0];
        } else {
            return icons[damage];
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return switch (Items.feather.getDamage(stack)) {
            case 0 -> "item.cell_coolant";
            case 1 -> "item.cell_hot_coolant";
            case 2 -> "item.cell_pseudo_liquid_naquadah";
            case 3 -> "item.cell_hot_pseudo_liquid_naquadah";
            case 4 -> "item.cell_distilled_water";
            default -> "item.invalid_cell";
        };
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> lines, boolean advancedTooltips) {
        super.addInformation(stack, player, lines, advancedTooltips);

        lines.add(switch (Items.feather.getDamage(stack)) {
            case 0 -> I18n.format("item.cell_coolant.tooltip", Config.COOLANT_SPECIFIC_HEAT);
            case 1 -> I18n.format("item.cell_hot_coolant.tooltip");
            case 2 -> I18n.format("item.cell_pseudo_liquid_naquadah.tooltip", Config.NAQ_COOLANT_SPECIFIC_HEAT);
            case 3 -> I18n.format("item.cell_hot_pseudo_liquid_naquadah.tooltip");
            case 4 -> I18n.format("item.cell_distilled_water");
            default -> "";
        });
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.add(new ItemStack(this, 1, 0));
        subItems.add(new ItemStack(this, 1, 1));
        subItems.add(new ItemStack(this, 1, 2));
        subItems.add(new ItemStack(this, 1, 3));
        subItems.add(new ItemStack(this, 1, 4));
    }
}
