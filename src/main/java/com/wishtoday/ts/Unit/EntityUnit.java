package com.wishtoday.ts.Unit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityUnit {
    public static void KillCustomEntity(ServerPlayerEntity player, int limit, Class<? extends Entity> cls){
        World world = player.getWorld();
        Vec3d Box3DFirst = new Vec3d(
                player.getX() + limit,
                player.getY() + limit,
                player.getZ() + limit
        );
        Vec3d Box3DSecond = new Vec3d(
                player.getX() - limit,
                player.getY() - limit,
                player.getZ() - limit
        );
        List<? extends Entity> entitiesByType = world.getEntitiesByType(
                TypeFilter.equals(cls),
                new Box(Box3DFirst, Box3DSecond),
                EntityPredicates.EXCEPT_SPECTATOR
        );
        for (Entity entity : entitiesByType) {
            entity.kill();
        }
    }
    public static boolean NoBabyInMobEntity(MobEntity entity){
        return  ((entity instanceof PassiveEntity)
                && !(entity instanceof FrogEntity)
                && !(entity instanceof ParrotEntity))
                || entity instanceof ZombieEntity
                || entity instanceof PiglinEntity
                || entity instanceof HoglinEntity;
    }
}
