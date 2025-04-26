package com.wishtoday.ts.Unit;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;

public class InventoryUnit {
    public static void swapSlots(PlayerEntity player, int slotNum, int otherSlot)
    {
        MinecraftClient mc = MinecraftClient.getInstance();
        //player.playerScreenHandler.syncId
        ScreenHandler container = player.playerScreenHandler;
        mc.interactionManager.clickSlot(container.syncId, slotNum, 0, SlotActionType.SWAP, player);
        mc.interactionManager.clickSlot(container.syncId, otherSlot, 0, SlotActionType.SWAP, player);
        mc.interactionManager.clickSlot(container.syncId, slotNum, 0, SlotActionType.SWAP, player);
    }
}
