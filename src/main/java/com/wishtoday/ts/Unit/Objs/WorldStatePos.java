package com.wishtoday.ts.Unit.Objs;

import net.minecraft.block.BlockState;

import java.util.Objects;

public class WorldStatePos {
    private WorldPos worldPos;
    private BlockState blockState;

    public WorldStatePos(WorldPos worldPos, BlockState blockState) {
        this.worldPos = worldPos;
        this.blockState = blockState;
    }
    public WorldPos getWorldPos() {
        return this.worldPos;
    }

    public void refreshPosAndState(WorldPos worldPos) {
        BlockState state = worldPos.getBlockState();
        this.worldPos.refresh(worldPos.getPos(),worldPos.getWorld());
        this.blockState = state;
    }
    public BlockState getBlockState() {
        return this.blockState;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorldStatePos that = (WorldStatePos) o;
        return Objects.equals(worldPos, that.worldPos) && Objects.equals(blockState, that.blockState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worldPos, blockState);
    }

    @Override
    public String toString() {
        return "WorldStatePos{" +
                "worldPos=" + worldPos +
                ", blockState=" + blockState +
                '}';
    }
    public void place(){
        this.getWorldPos().getWorld().setBlockState(worldPos.getPos(),blockState);
    }
}
