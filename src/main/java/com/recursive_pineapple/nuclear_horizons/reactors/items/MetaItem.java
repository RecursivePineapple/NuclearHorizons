package com.recursive_pineapple.nuclear_horizons.reactors.items;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MetaItem extends NHItem {

    public final IIcon[] icons;
    public final IDMetaItem[] metaItems;

    public MetaItem() {
        super("metaitem");

        setHasSubtypes(true);
        setMaxDamage(0);

        int max = Arrays.stream(IDMetaItem.values()).mapToInt(x -> x.ID).max().getAsInt();

        icons = new IIcon[max + 1];
        metaItems = new IDMetaItem[max + 1];
    }

    @Override
    public void registerInit() {
        super.registerInit();

        for (IDMetaItem id : IDMetaItem.values()) {
            metaItems[id.ID] = id;
            id.container.get().set(new ItemStack(this, 1, id.ID));
        }
    }

    @Override
    public final Item setUnlocalizedName(String aName) {
        return this;
    }

    @Override
    public final String getUnlocalizedName() {
        return name;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + name + "." + getDamage(stack);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> desc, boolean advancedTooltips) {
        super.addInformation(stack, player, desc, advancedTooltips);

        int meta = getDamage(stack);

        String descKey = "item." + name + "." + meta + ".desc";

        if (StatCollector.canTranslate(descKey)) {
            desc.add(StatCollector.translateToLocal(descKey));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        for (IDMetaItem id : IDMetaItem.values()) {
            icons[id.ID] = iconRegister.registerIcon("nuclear_horizons:" + "metaitem/" + id.ID);
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return meta < 0 || meta >= icons.length ? null : icons[meta];
    }

    @Override
    public void getSubItems(Item self, CreativeTabs tab, List<ItemStack> subItems) {
        for (IDMetaItem id : IDMetaItem.values()) {
            subItems.add(new ItemStack(this, 1, id.ID));
        }
    }
}
