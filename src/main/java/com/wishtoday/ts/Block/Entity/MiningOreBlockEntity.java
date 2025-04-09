package com.wishtoday.ts.Block.Entity;

import com.wishtoday.ts.Block.Register.BlockEntityTypeRegister;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MiningOreBlockEntity extends BlockEntity {
    private BlockPos PlacePos;
    private BlockPos blockPos;
    private int miningTime = 20;
    public MiningOreBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeRegister.MINING_ORE_BLOCK_ENTITY, pos, state);
        PlacePos = pos.up();
        blockPos = pos;
    }

    public BlockPos getPlacePos() {
        return this.PlacePos;
    }

    public void setPlacePos(BlockPos placePos) {
        this.PlacePos = placePos;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public int getMiningTime() {
        return this.miningTime;
    }

    public void setMiningTime(int miningTime) {
        this.miningTime = miningTime;
    }

    public static void tick(World world,
                            BlockPos pos,
                            BlockState state,
                            MiningOreBlockEntity blockEntity) {
        if (!(blockEntity.getMiningTime() == 0)) {
            blockEntity.setMiningTime(blockEntity.getMiningTime() - 1);
        } else {
            blockEntity.setMiningTime(20);
            BlockPos pos1 = blockEntity.getBlockPos().down();
            blockEntity.setBlockPos(pos1);
            if (pos1.getY() == -64) world.setBlockState(pos, Blocks.AIR.getDefaultState());
            BlockState blockState = world.getBlockState(pos1);
            if (!(blockState.isIn(BlockTags.IRON_ORES)
            || blockState.isIn(BlockTags.GOLD_ORES)
            || blockState.isIn(BlockTags.DIAMOND_ORES)
            || blockState.isIn(BlockTags.EMERALD_ORES)
            || blockState.isIn(BlockTags.COAL_ORES)
            || blockState.isIn(BlockTags.LAPIS_ORES)
            || blockState.isIn(BlockTags.COPPER_ORES)
            || blockState.isIn(BlockTags.REDSTONE_ORES))) {
                world.breakBlock(pos1, true);
            } else {
                world.setBlockState(blockEntity.getPlacePos(), blockState);
                world.breakBlock(pos1, false);
                blockEntity.setPlacePos(blockEntity.getPlacePos().up());
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("MiningTime", this.miningTime);
        nbt.put("PlacePos", NbtHelper.fromBlockPos(this.PlacePos));
        nbt.put("BlockPos", NbtHelper.fromBlockPos(this.blockPos));
        super.writeNbt(nbt, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.miningTime = nbt.getInt("MiningTime");
        this.PlacePos = NbtHelper.toBlockPos(nbt,"PlacePos").orElse(null);
        this.blockPos = NbtHelper.toBlockPos(nbt,"BlockPos").orElse(null);
    }
}
