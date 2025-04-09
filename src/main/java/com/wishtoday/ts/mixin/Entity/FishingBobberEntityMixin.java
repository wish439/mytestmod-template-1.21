package com.wishtoday.ts.mixin.Entity;

import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {
    @Inject(method = "onEntityHit",at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;updateHookedEntityId(Lnet/minecraft/entity/Entity;)V",shift = At.Shift.BEFORE))
    private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        FishingBobberEntity bobber = (FishingBobberEntity) (Object) this;
        entityHitResult.getEntity().damage(bobber.getDamageSources().thrown(bobber,bobber.getOwner()),0);
    }
}
