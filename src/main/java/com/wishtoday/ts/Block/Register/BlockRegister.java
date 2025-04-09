package com.wishtoday.ts.Block.Register;

import com.wishtoday.ts.Block.MiningOreBlock;
import com.wishtoday.ts.Mytestmod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockRegister {
    public static final Block MINING_ORE_BLOCK = register("mining_ore_block",new MiningOreBlock(AbstractBlock.Settings.create()));
    public static void init(){}
    public static Block register(String id, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Mytestmod.MOD_ID,id), block);
    }
}
