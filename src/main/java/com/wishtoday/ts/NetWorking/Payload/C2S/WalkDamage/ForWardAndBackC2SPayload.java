package com.wishtoday.ts.NetWorking.Payload.C2S.WalkDamage;

import com.wishtoday.ts.Mytestmod;
import com.wishtoday.ts.NetWorking.Payload.C2SCustomPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.RaycastContext;

public record ForWardAndBackC2SPayload(float value) implements C2SCustomPayload<ForWardAndBackC2SPayload> {
    private static final Identifier identifier = Identifier.of(Mytestmod.MOD_ID,"for_ward_and_back_c2s_payload");
    public static final Id<ForWardAndBackC2SPayload> ID = new Id<>(identifier);
    public static final PacketCodec<PacketByteBuf,ForWardAndBackC2SPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT,
            ForWardAndBackC2SPayload::value,
            ForWardAndBackC2SPayload::new);
    @Override
    public void receive(ForWardAndBackC2SPayload forWardAndBackC2SPayload, ServerPlayNetworking.Context context) {
        ServerPlayerEntity player = context.player();
        DamageSources damageSources = player.getDamageSources();
        context.server().execute(() -> {
            if (value == 0.0F) return;
            //如果是1就证明是向前走的
            if (value == 1.0F) player.damage(damageSources.wither(),4);
            //如果是-1就证明是向后走的
            if (value == -1.0F) player.setHealth(player.getHealth() + 2);
        });
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
