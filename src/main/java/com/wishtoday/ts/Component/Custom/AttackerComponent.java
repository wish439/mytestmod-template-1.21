package com.wishtoday.ts.Component.Custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record AttackerComponent(UUID attacker_uuid) {
    public static final Codec<AttackerComponent> CODEC = RecordCodecBuilder.create
            (instance -> instance.group(
                    Uuids.CODEC.optionalFieldOf("attacker_uuid",null)
                            .forGetter(AttackerComponent::attacker_uuid))
                    .apply(instance, AttackerComponent::new)
            );
    public static final PacketCodec<ByteBuf,AttackerComponent> PACKET_CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC,
            AttackerComponent::attacker_uuid,
            AttackerComponent::new
    );
}
