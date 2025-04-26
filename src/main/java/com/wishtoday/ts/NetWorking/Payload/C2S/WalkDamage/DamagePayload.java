package com.wishtoday.ts.NetWorking.Payload.C2S.WalkDamage;

import com.wishtoday.ts.Mytestmod;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public record DamagePayload() implements CustomPayload {
    public static final Identifier identifier = Identifier.of(Mytestmod.MOD_ID, "damage_payload");
    public static final Id<DamagePayload> ID = new Id<>(identifier);
    public static final PacketCodec<PacketByteBuf, DamagePayload> CODEC = PacketCodec.unit(new DamagePayload());
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
    public static void receive(DamagePayload payload, ServerPlayNetworking.Context context) {
        ServerPlayerEntity player = context.player();
        context.server().execute(() -> player.damage(player.getDamageSources().wither(),1F));
    }
}
