package com.wishtoday.ts.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ExplosiveProjectileEntity.class)
public class ExplosiveProjectileEntityMixin {

    @ModifyArg(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/world/World;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/ExplosiveProjectileEntity;<init>(Lnet/minecraft/entity/EntityType;DDDLnet/minecraft/util/math/Vec3d;Lnet/minecraft/world/World;)V"),index = 2)
    private static double modifyY(double y, @Local(argsOnly = true) LivingEntity owner) {
        return owner.getEyeY() - 0.1;
    }
}
