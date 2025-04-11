package com.wishtoday.ts.mixin.HelpEvent;

import com.wishtoday.ts.Event.RegisterEvent.PlayerInteractEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerInteractEventsMixin {
    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void onAttack(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!PlayerInteractEvents.ATTACK_ENTITY.invoker().attackEntity(player, player.getServer(), target)) {
            ci.cancel();
        }
    }
}
