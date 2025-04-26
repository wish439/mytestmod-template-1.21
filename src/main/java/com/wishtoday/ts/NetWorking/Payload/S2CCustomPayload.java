package com.wishtoday.ts.NetWorking.Payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.packet.CustomPayload;

public interface S2CCustomPayload<E> extends CustomPayload {
    void receive(E e, ClientPlayNetworking.Context context);
    //PacketCodec<PacketByteBuf,? extends CustomPayload> getCodec();
}
