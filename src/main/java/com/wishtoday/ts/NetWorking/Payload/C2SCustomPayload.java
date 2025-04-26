package com.wishtoday.ts.NetWorking.Payload;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.packet.CustomPayload;

public interface C2SCustomPayload<T> extends CustomPayload {
    void receive(T e, ServerPlayNetworking.Context context);
    //PacketCodec<PacketByteBuf,T> getCodec();
}