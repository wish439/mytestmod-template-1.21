package com.wishtoday.ts.Tags;

import com.wishtoday.ts.Mytestmod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> BOW_WEAPONS = of("enchantable/bow_weapons");
    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(Mytestmod.MOD_ID,id));
    }
    public static void init() {}
}
