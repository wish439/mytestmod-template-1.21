package com.wishtoday.ts.mixin;

import com.wishtoday.ts.Unit.BlockUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireballEntity.class)
public class FireBallEntityMixin {
    @Shadow private int explosionPower;

    @Inject(method = "onEntityHit",at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private void SetHealth(EntityHitResult entityHitResult, CallbackInfo ci){
        Entity var6 = entityHitResult.getEntity();
        if(var6 instanceof PlayerEntity){
            ((PlayerEntity) var6).setHealth(((PlayerEntity) var6).getHealth() + 6);
        }
    }
    }
