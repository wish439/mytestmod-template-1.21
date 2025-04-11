package com.wishtoday.ts.NetWorking.Payload.C2S.Inputs;

import com.wishtoday.ts.Mytestmod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;


public record KeepInventoryPayload() implements CustomPayload {
    private static final Identifier KEEP_INVENTORY = Identifier.of(Mytestmod.MOD_ID, "keep_inventory");
    public static final Id<KeepInventoryPayload> ID = new Id<>(KEEP_INVENTORY);
    public static final PacketCodec<PacketByteBuf,KeepInventoryPayload> CODEC = PacketCodec.unit(new KeepInventoryPayload());
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
