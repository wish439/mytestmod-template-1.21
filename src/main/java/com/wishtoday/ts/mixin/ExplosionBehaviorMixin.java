package com.wishtoday.ts.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ExplosionBehavior.class)
public class ExplosionBehaviorMixin {
    @Inject(method = "getKnockbackModifier",at = @At("TAIL"), cancellable = true)
    private void MakeReturn(Entity entity, CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(1.1F);
    }

}
