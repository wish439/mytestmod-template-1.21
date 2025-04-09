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
    private ExplodeUnit(){}

    /**
     此方法用于创造一个简单的爆炸，使用会在player的位置产生一个power威力的爆炸，这个爆炸CreateFire产生火焰,如果DamageToPlayer为真将给予Player DamageValue点伤害
     */
    public static void CreateEasyExplode(Entity entity,float power,boolean CreateFire,boolean Damage,float DamageValue){
        World world = entity.getWorld();
        Explosion explosion = entity.getWorld().createExplosion(
                entity,
                world.getDamageSources().fall(),
                null,
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                power,
                CreateFire,
                World.ExplosionSourceType.TNT
        );
        if (Damage){
            entity.damage(world.getDamageSources().explosion(explosion),DamageValue);
        }
    }
}
