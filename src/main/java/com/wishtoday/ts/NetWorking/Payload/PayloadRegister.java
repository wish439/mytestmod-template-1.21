package com.wishtoday.ts.NetWorking.Payload;

import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.KeepInventoryPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.TestPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.ToDeadPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.ModifyToBedrock.ModifyToBedrockPlayC2SPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.WalkDamage.DamagePayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.WalkDamage.ForWardAndBackC2SPayload;
import com.wishtoday.ts.NetWorking.Payload.S2C.WalkDamage.ForWardAndBackS2CPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class PayloadRegister {
    public static void registerPayload() {
        registerC2SPacket();
        registerS2CPacket();
    }

    public static void registerC2SPacket() {
        PayloadTypeRegistry.playC2S().register(TestPayload.ID, TestPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(ModifyToBedrockPlayC2SPayload.ID, ModifyToBedrockPlayC2SPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(KeepInventoryPayload.ID, KeepInventoryPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(ToDeadPayload.ID, ToDeadPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(DamagePayload.ID, DamagePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(ForWardAndBackC2SPayload.ID, ForWardAndBackC2SPayload.CODEC);
    }

    public static void registerS2CPacket() {
       PayloadTypeRegistry.playS2C().register(ForWardAndBackS2CPayload.ID, ForWardAndBackS2CPayload.CODEC);
    }
}
