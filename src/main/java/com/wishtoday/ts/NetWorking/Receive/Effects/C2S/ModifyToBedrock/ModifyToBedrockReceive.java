package com.wishtoday.ts.NetWorking.Receive.Effects.C2S.ModifyToBedrock;

import com.wishtoday.ts.NetWorking.Payload.C2S.ModifyToBedrock.ModifyToBedrockPlayC2SPayload;
import com.wishtoday.ts.NetWorking.Receive.Effects.CustomReceive;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import static com.wishtoday.ts.Command.CommandValue.ModifyToBedrockCommand.playerAndStatePosList;

public class ModifyToBedrockReceive implements CustomReceive {
    public static void receive() {
        PayloadTypeRegistry.playC2S().register(ModifyToBedrockPlayC2SPayload.ID,ModifyToBedrockPlayC2SPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(ModifyToBedrockPlayC2SPayload.ID, (modifyToBedrockPlayC2SPayload, context) -> {
            BlockPos pos = modifyToBedrockPlayC2SPayload.pos();
            ServerPlayerEntity player = context.player();
            context.server().execute(() -> playerAndStatePosList.forEach(playerAndStatePos -> {
                if (playerAndStatePos.getPlayer().equals(player)) {
                    playerAndStatePos.setTargetPos(pos);
                }
            }));
        });
    }
}
