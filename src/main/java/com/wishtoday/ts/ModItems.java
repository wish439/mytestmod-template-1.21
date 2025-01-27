package com.wishtoday.ts;

import com.wishtoday.ts.Item.RemoveHunger;
import com.wishtoday.ts.Item.TeleportGround;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems{
    public ModItems(Item.Settings settings) {
        super();
    }
    public static final Item RemoveHunger = Registry.register(Registries.ITEM, Identifier.of("my-test_mod","removehunger"),new RemoveHunger(new Item.Settings()));
    public static final Item TELEPORTGROUND = Registry.register(Registries.ITEM,Identifier.of("my-test_mod","teleportground"),new TeleportGround(new Item.Settings()));
    public static void init() {}
}
