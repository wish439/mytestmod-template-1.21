package com.wishtoday.ts.Block.Register;

import com.wishtoday.ts.Block.Entity.MiningOreBlockEntity;
import com.wishtoday.ts.Mytestmod;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntityTypeRegister {
    public static final BlockEntityType<MiningOreBlockEntity> MINING_ORE_BLOCK_ENTITY = create(
            "mining_ore_block",
            BlockEntityType.Builder.create(
                    MiningOreBlockEntity::new,
                    BlockRegister.MINING_ORE_BLOCK).build());
    public static void init(){}
    private static <T extends BlockEntityType<?>> T create(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Mytestmod.MOD_ID, path), blockEntityType);
    }
}
