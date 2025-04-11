package com.wishtoday.ts.Client.Event.EventValue;

import com.wishtoday.ts.NetWorking.Payload.C2S.ModifyToBedrock.ModifyToBedrockPlayC2SPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class ModifyToBedrockNetWorking {
    public static void register(MinecraftClient client) {
        if (client.player != null && client.crosshairTarget != null && client.crosshairTarget.getType() == HitResult.Type.BLOCK) {
            ClientPlayNetworking.
                    send(new ModifyToBedrockPlayC2SPayload(
                            ((BlockHitResult) client.crosshairTarget)
                                    .getBlockPos()));
        }
    }
}
