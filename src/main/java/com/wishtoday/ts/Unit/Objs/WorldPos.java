package com.wishtoday.ts.Unit.Objs;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class WorldPos {
    private BlockPos pos;
    private World world;

    public WorldPos(World world, BlockPos pos) {
        this.world = world;
        this.pos = pos;
    }
    public void refresh(BlockPos pos, World world) {
        this.pos = pos;
        this.world = world;
    }
    public BlockPos getPos() {
        return this.pos;
    }

    public World getWorld() {
        return this.world;
    }

    @Override
    public String toString() {
        return "WorldPos{" +
                "pos=" + pos +
                ", world=" + world +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorldPos worldPos = (WorldPos) o;
        return Objects.equals(pos, worldPos.pos) && Objects.equals(world, worldPos.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, world);
    }
    public BlockState getBlockState() {
        return world.getBlockState(pos);
    }
}
