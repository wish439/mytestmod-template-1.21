package com.wishtoday.ts.mixin.Entity;

import com.wishtoday.ts.Mytestmod;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "sendMovementPackets",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket$Full;<init>(DDDFFZ)V")
            , slice = @Slice(
            to = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket$PositionAndOnGround;<init>(DDDZ)V", shift = At.Shift.BEFORE),
            from = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getVelocity()Lnet/minecraft/util/math/Vec3d;")
    )
    )
    private void sendPacket(CallbackInfo ci) {
        //Mytestmod.LOGGER.info("角度位置都变了,要么是骑马要么是人");
    }
}
