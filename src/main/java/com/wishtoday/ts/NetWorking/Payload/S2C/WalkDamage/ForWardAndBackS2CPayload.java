package com.wishtoday.ts.NetWorking.Payload.S2C.WalkDamage;

import com.wishtoday.ts.Mytestmod;
import com.wishtoday.ts.NetWorking.Payload.C2S.WalkDamage.ForWardAndBackC2SPayload;
import com.wishtoday.ts.NetWorking.Payload.S2CCustomPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ForWardAndBackS2CPayload() implements S2CCustomPayload<ForWardAndBackS2CPayload> {
    private static final Identifier identifier = Identifier.of(Mytestmod.MOD_ID, "forward_and_back_s2c");
    public static final Id<ForWardAndBackS2CPayload> ID = new Id<>(identifier);
    public static final PacketCodec<PacketByteBuf, ForWardAndBackS2CPayload> CODEC = PacketCodec.unit(new ForWardAndBackS2CPayload());

    @Override
    public void receive(ForWardAndBackS2CPayload forWardAndBackS2CPayload, ClientPlayNetworking.Context context) {
        MinecraftClient client = context.client();
        client.execute(() -> {
            //获取走的向量
            float value = client.player.input.movementForward;
            //Mytestmod.LOGGER.info(value + "");
            if (value == 0.0F) return;
            //向服务端发送一个包,其中包含走的向量
            ClientPlayNetworking.send(new ForWardAndBackC2SPayload(value));
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
