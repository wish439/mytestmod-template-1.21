package com.wishtoday.ts.Item;

import com.wishtoday.ts.Block.Register.BlockRegister;
import com.wishtoday.ts.Item.Custom.MakeLoveItem;
import com.wishtoday.ts.Item.Custom.RemoveHunger;
import com.wishtoday.ts.Item.Custom.RevengeItem;
import com.wishtoday.ts.Item.Custom.TeleportGround;
import com.wishtoday.ts.Mytestmod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Identifier;

public class ModItems {
    private ModItems() {
    }

    public static final Item REMOVEHUNGER = of("remove_hunger", new RemoveHunger(new Item.Settings()));
    public static final Item TELEPORTGROUND = of("teleport_ground", new TeleportGround(new Item.Settings()));
    public static final Item MiningOreBlock = Registry.register(Registries.ITEM, Identifier.of(Mytestmod.MOD_ID, "mining_ore_block"), new BlockItem(BlockRegister.MINING_ORE_BLOCK, new Item.Settings()));
    public static final Item MAKELOVE = of("make_love", new MakeLoveItem(new Item.Settings()));
    public static final Item REVENGE = of("revenge", new RevengeItem(new Item.Settings()));

    public static void init() {
    }

    private static Item of(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Mytestmod.MOD_ID, id), item);
    }
}
