package com.wishtoday.ts.NetWorking.ModifyToBedrock;

import com.wishtoday.ts.Mytestmod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record ModifyToBedrockPlayC2SPayload(BlockPos pos) implements CustomPayload {
    public static final Identifier MODIFYTOBEDROCK = Identifier.of(Mytestmod.MOD_ID, "modify_to_bedrock");
    public static final CustomPayload.Id<ModifyToBedrockPlayC2SPayload> ID = new Id<>(MODIFYTOBEDROCK);
    public static final PacketCodec<PacketByteBuf, ModifyToBedrockPlayC2SPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, ModifyToBedrockPlayC2SPayload::pos, ModifyToBedrockPlayC2SPayload::new);
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
