package com.wishtoday.ts.Item;

import com.wishtoday.ts.Mytestmod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.networking.v1.S2CPlayChannelEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup MYTESTMODGROUP = register("mytestmodgroup",FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.MiningOreBlock))
            .displayName(Text.translatable("itemgroup.mytestmodgroup"))
            .entries((context,entries) -> {
                entries.add(new ItemStack(ModItems.MiningOreBlock));
                entries.add(new ItemStack(ModItems.MAKELOVE));
                entries.add(new ItemStack(ModItems.REMOVEHUNGER));
                entries.add(new ItemStack(ModItems.REVENGE));
                entries.add(new ItemStack(ModItems.TELEPORTGROUND));
            }).build());
    private static ItemGroup register(String id,ItemGroup itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, Identifier.of(Mytestmod.MOD_ID,id),itemGroup);
    }
    public static void init() {}
}
