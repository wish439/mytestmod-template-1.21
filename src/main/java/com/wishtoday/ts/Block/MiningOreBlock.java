package com.wishtoday.ts.Block;

import com.mojang.serialization.MapCodec;
import com.wishtoday.ts.Block.Entity.MiningOreBlockEntity;
import com.wishtoday.ts.Block.Register.BlockEntityTypeRegister;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MiningOreBlock extends BlockWithEntity {
    public MiningOreBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(MiningOreBlock::new);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new  MiningOreBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type,BlockEntityTypeRegister.MINING_ORE_BLOCK_ENTITY,MiningOreBlockEntity::tick);
    }
}
