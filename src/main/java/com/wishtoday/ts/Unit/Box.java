package com.wishtoday.ts.Unit;

import net.minecraft.util.math.BlockPos;

import java.util.Objects;
import java.util.function.Consumer;
//6
public class Box {
    private int x, y, z;
    private int x2, y2, z2;

    private Box(BlockPos pos1, BlockPos pos2) {
        x = pos1.getX();
        y = pos1.getY();
        z = pos1.getZ();
        x2 = pos2.getX();
        y2 = pos2.getY();
        z2 = pos2.getZ();
    }

    private Box(int x, int y, int z, int x2, int y2, int z2) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public static Box create(BlockPos pos1, BlockPos pos2) {
        return new Box(pos1, pos2);
    }

    public static Box create(int x, int y, int z, int x2, int y2, int z2) {
        return new Box(x, y, z, x2, y2, z2);
    }

    public void traverse(Consumer<BlockPos> action) {
        Objects.requireNonNull(action);
        for (BlockPos blockPos : BlockPos.iterate(this.getStartPos(), this.getEndPos())) {
            action.accept(blockPos);
        }
    }

    public BlockPos getStartPos() {
        return new BlockPos(x, y, z);
    }

    public BlockPos getEndPos() {
        return new BlockPos(x2, y2, z2);
    }
}
