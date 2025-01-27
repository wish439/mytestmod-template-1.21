package com.wishtoday.ts.Test;


import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3d;

public class BlockPosXZ extends PlayerEntity {
    public BlockPosXZ(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    public static BlockPos getBlockPosInFront(PlayerEntity player, int distance) {
        Vector3d lookVec = getPlayerLookVector(player);
        double xOffset = lookVec.x * distance;
        double yOffset = lookVec.y * distance;
        double zOffset = lookVec.z * distance;
        return player.getBlockPos().add((int) xOffset, (int) yOffset, (int) zOffset);
    }
    public static Vector3d getPlayerLookVector(PlayerEntity player) {
        float yaw = (float) Math.toRadians(player.getYaw(1.0F));
        float pitch = (float) Math.toRadians(player.getPitch(1.0F));
        double cosPitch = Math.cos(pitch);
        return new Vector3d(-Math.sin(yaw) * cosPitch, -Math.sin(pitch), Math.cos(yaw) * cosPitch);
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }
}
