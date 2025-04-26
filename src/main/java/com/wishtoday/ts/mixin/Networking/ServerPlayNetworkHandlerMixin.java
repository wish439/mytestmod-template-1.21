package com.wishtoday.ts.mixin.Networking;

import com.wishtoday.ts.Command.CommandValue.WalkDamageCommand;
import com.wishtoday.ts.Mytestmod;
import com.wishtoday.ts.NetWorking.Payload.S2C.WalkDamage.ForWardAndBackS2CPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {

    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onPlayerMove",at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;getServerWorld()Lnet/minecraft/server/world/ServerWorld;",shift = At.Shift.BEFORE))
    private void onPlayerMove(PlayerMoveC2SPacket packet, CallbackInfo ci){
        if (!WalkDamageCommand.walkDamageTarget.contains(player)) return;
        if (!(packet instanceof PlayerMoveC2SPacket.Full || packet instanceof PlayerMoveC2SPacket.PositionAndOnGround)) return;
        //由服务端发送一个S2C包到客户端
        ServerPlayNetworking.send(player,new ForWardAndBackS2CPayload());
    }
}
