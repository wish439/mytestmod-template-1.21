package com.wishtoday.ts.Unit;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUnit {
    /**
     * 查找从玩家当前 Y 坐标开始，第一个满足从该 Y 坐标到 319 之间所有方块都是空气方块的 Y 坐标
     * @param player 玩家实体
     * @return 符合条件的 Y 坐标，若未找到则返回 -1
     */
    public static int getNotAirY(PlayerEntity player){
        //获得世界
        World world = player.getWorld();
        boolean CanSend;
        //从玩家当前Y坐标开始
        for (int i = (int)Math.round(player.getY()) ; i < 319;i++) {
            CanSend = true;
            //查找是否从i坐标到319之前全部方块都是空气方块
            for (int j = i; j < 320; j++) {
                if (!world.getBlockState(new BlockPos((int) Math.round(player.getX()), j, (int) Math.round(player.getZ()))).isOf(Blocks.AIR)){
                    CanSend = false;
                    break;
                }
            }
            //如果从i坐标到319之前全部方块都是空气方块，返回i，结束方法
            if (CanSend) {
                return i;
            }
        }
        return -1;
    }
    public static BlockPos EasyCreateBlockPos(PlayerEntity player){
        return new BlockPos((int)Math.round(player.getX()), (int)Math.round(player.getY()), (int)Math.round(player.getZ()));
    }
    public static BlockPos EasyCreateBlockPos(double x,double y,double z){
        return new BlockPos((int)Math.round(x), (int)Math.round(y), (int)Math.round(z));
    }
}
