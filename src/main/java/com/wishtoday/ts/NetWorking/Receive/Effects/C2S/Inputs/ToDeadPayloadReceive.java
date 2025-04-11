package com.wishtoday.ts.NetWorking.Receive.Effects.C2S.Inputs;

import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.ToDeadPayload;
import com.wishtoday.ts.NetWorking.Receive.Effects.CustomReceive;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ToDeadPayloadReceive implements CustomReceive {
    public static void receive() {
        PayloadTypeRegistry.playC2S().register(ToDeadPayload.ID, ToDeadPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(
                ToDeadPayload.ID,
                (todead, context) -> {
                    ServerPlayerEntity player = context.player();
                    player.getLastDeathPos().ifPresentOrElse(
                            globalPos -> {
                                ServerWorld world = player.getServer().getWorld(globalPos.dimension());
                                BlockPos pos = globalPos.pos();
                                player.teleport(world, pos.getX(), pos.getY(), pos.getZ(), player.getYaw(), player.getPitch());
                            },
                            () -> player.sendMessage(Text.of("您还没有上次死亡点,请死亡后再试"))
                    );
                }
        );
    }
}
