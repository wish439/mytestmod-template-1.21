package com.wishtoday.ts.Test;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3d;
import org.joml.Vector3i;

public class BlockBuild {
    public static Vector3i getDirectionVector(BlockPos pos1, BlockPos pos2) {
        int xDiff = pos2.getX() - pos1.getX();
        int yDiff = pos2.getY() - pos1.getY();
        int zDiff = pos2.getZ() - pos1.getZ();
        return new Vector3i(xDiff, yDiff, zDiff);
    }
    public static Vector3d getUnitDirectionVector(BlockPos pos1, BlockPos pos2) {
        Vector3i direction = getDirectionVector(pos1, pos2);
        double length = Math.sqrt(direction.x() * direction.x() + direction.y() * direction.y() + direction.z() * direction.z());
        return new Vector3d(direction.x() / length, direction.y() / length, direction.z() / length);
    }
    public static void buildBlockLine(BlockPos pos1, BlockPos pos2, World world) {
        Vector3d unitDirection = getUnitDirectionVector(pos1, pos2);
        int maxDistance = (int) Math.round(getDirectionVector(pos1, pos2).length());
        for (int i = 0; i <= maxDistance; i++) {
            int x = (int) Math.round(pos1.getX() + unitDirection.x * i);
            int y = (int) Math.round(pos1.getY() + unitDirection.y * i);
            int z = (int) Math.round(pos1.getZ() + unitDirection.z * i);
            BlockPos currentPos = new BlockPos(x, y, z);
            world.setBlockState(currentPos, Blocks.DIAMOND_BLOCK.getDefaultState(), 3);
        }
    }
    public static void buildCone(BlockPos pos1, BlockPos pos2, World world, int height, int baseRadius, int angleStep) {
        // 构建圆锥底面
        buildConeBase(pos1, world, baseRadius, angleStep);

        Vector3d unitAxisDirection = getUnitDirectionVector(pos1, pos2);
        for (int h = 0; h < height; h++) {
            double currentRadius = baseRadius * (1.0 - (double) h / height);  // 根据高度计算当前层的半径，越往上半径越小
            buildConeLayerAtHeight(pos1, world, currentRadius, h, unitAxisDirection, angleStep);
        }
    }

    // 构建圆锥底面（以给定BlockPos为中心，按半径和角度间隔放置方块形成圆形底面）
    private static void buildConeBase(BlockPos centerPos, World world, int radius, int angleStep) {
        for (int angle = 0; angle < 360; angle += angleStep) {
            double radians = Math.toRadians(angle);
            int x = (int) (radius * Math.cos(radians)) + centerPos.getX();
            int z = (int) (radius * Math.sin(radians)) + centerPos.getZ();
            BlockPos blockPos = new BlockPos(x, centerPos.getY(), z);
            world.setBlockState(blockPos, Blocks.COBBLESTONE.getDefaultState(), 3);
        }
    }

    // 在指定高度沿着圆锥轴方向构建一圈方块形成圆锥侧面的一层
    private static void buildConeLayerAtHeight(BlockPos basePos, World world, double radius, int height, Vector3d unitAxisDirection, int angleStep) {
        BlockPos currentPosOnAxis = basePos.add(0, height, 0);  // 获取当前高度在圆锥轴上的位置
        for (int angle = 0; angle < 360; angle += angleStep) {
            double radians = Math.toRadians(angle);
            int x = (int) (radius * Math.cos(radians)) + currentPosOnAxis.getX();
            int z = (int) (radius * Math.sin(radians)) + currentPosOnAxis.getZ();
            BlockPos blockPos = new BlockPos(x, currentPosOnAxis.getY(), z);
            world.setBlockState(blockPos, Blocks.COBBLESTONE.getDefaultState(), 3);
        }
    }
}
