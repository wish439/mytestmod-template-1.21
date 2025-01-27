package com.wishtoday.ts.Unit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

public class ExplodeUnit {

    /**
     此方法用于创造一个简单的爆炸，使用会在player的位置产生一个power威力的爆炸，这个爆炸CreateFire产生火焰
     */
    public static void CreateEasyExplode(PlayerEntity player,float power,boolean CreateFire,boolean DamageToPlayer,float DamageValue){
        World world = player.getWorld();
        Explosion explosion = player.getWorld().createExplosion(
                player,
                world.getDamageSources().fall(),
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                power,
                CreateFire,
                World.ExplosionSourceType.TNT
        );
        if (DamageToPlayer){
            player.damage(world.getDamageSources().explosion(explosion),DamageValue);
        }
    }
}
