package com.wishtoday.ts.NetWorking.Payload.C2S.Inputs;

import com.wishtoday.ts.Mytestmod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record TestPayload() implements CustomPayload {
    private static final Identifier TESTIDEN = Identifier.of(Mytestmod.MOD_ID,"test_payload");
    public static final Id<TestPayload> ID = new Id<>(TESTIDEN);
    public static final PacketCodec<PacketByteBuf,TestPayload> CODEC = PacketCodec.unit(new TestPayload());
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
