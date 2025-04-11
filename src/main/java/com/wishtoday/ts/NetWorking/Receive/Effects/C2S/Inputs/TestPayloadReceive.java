package com.wishtoday.ts.NetWorking.Receive.Effects.C2S.Inputs;

import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.TestPayload;
import com.wishtoday.ts.NetWorking.Receive.Effects.CustomReceive;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;

public class TestPayloadReceive implements CustomReceive {
    public static void receive(){
        PayloadTypeRegistry.playC2S().register(TestPayload.ID,TestPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(
                TestPayload.ID,
                (testPayload,context) -> EntityType.CHICKEN.spawn((ServerWorld) context.player().getWorld(),context.player().getBlockPos(), SpawnReason.EVENT)
        );
    }
}
