package com.wishtoday.ts.NetWorking.Payload.C2S.Inputs;

import com.wishtoday.ts.Mytestmod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ToDeadPayload() implements CustomPayload {
    private static final Identifier TO_DEAD = Identifier.of(Mytestmod.MOD_ID, "to_dead");
    public static final Id<ToDeadPayload> ID = new Id<>(TO_DEAD);
    public static final PacketCodec<PacketByteBuf,ToDeadPayload> CODEC = PacketCodec.unit(new ToDeadPayload());
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
