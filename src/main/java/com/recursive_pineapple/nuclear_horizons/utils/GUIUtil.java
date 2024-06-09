package com.recursive_pineapple.nuclear_horizons.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

public class GUIUtil {
    public static void showGUI(EntityPlayerMP player, Container container, IInventory inventory) {
        if (player.openContainer != player.inventoryContainer)
        {
            player.closeScreen();
        }

        player.getNextWindowId();
        player.playerNetServerHandler.sendPacket(
            new S2DPacketOpenWindow(
                player.currentWindowId, 0, inventory.getInventoryName(), inventory.getSizeInventory(), inventory.hasCustomInventoryName()
            )
        );
        player.openContainer = container;
        player.openContainer.windowId = player.currentWindowId;
        player.openContainer.addCraftingToCrafters(player);
    }
}
