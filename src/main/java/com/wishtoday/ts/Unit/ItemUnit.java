package com.wishtoday.ts.Unit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;

public class ItemUnit {
    private ItemUnit(){}
    public static void QuickRemoveItem(Item item, PlayerEntity player){
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.getStack(i).getItem().equals(item)){
                inventory.removeStack(i);
            }
        }
    }
}
