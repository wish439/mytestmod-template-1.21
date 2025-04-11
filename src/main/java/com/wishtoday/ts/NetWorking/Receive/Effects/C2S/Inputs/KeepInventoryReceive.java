package com.wishtoday.ts.NetWorking.Receive.Effects.C2S.Inputs;

import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.KeepInventoryPayload;
import com.wishtoday.ts.NetWorking.Receive.Effects.CustomReceive;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;

public class KeepInventoryReceive implements CustomReceive {
    public static void receive() {
        PayloadTypeRegistry.playC2S().register(KeepInventoryPayload.ID, KeepInventoryPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(KeepInventoryPayload.ID,
                (keepInventory, context) -> {
                    ServerPlayerEntity player = context.player();
                    if (!player.getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
                        player.getWorld().getGameRules().get(GameRules.KEEP_INVENTORY).set(true, player.getServer());
                        player.sendMessage(Text.of("已将死亡不掉落设置为开"));
                    }else {
                        player.sendMessage(Text.of("死亡不掉落已为true,无需更改"));
                    }
                }
        );
    }
}
