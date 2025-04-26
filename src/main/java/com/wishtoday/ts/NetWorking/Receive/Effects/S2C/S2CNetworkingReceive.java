package com.wishtoday.ts.NetWorking.Receive.Effects.S2C;

import com.wishtoday.ts.NetWorking.Payload.S2C.WalkDamage.ForWardAndBackS2CPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class S2CNetworkingReceive implements S2CPayloadReceives {
    @Override
    public void ForWardAndBackS2CPayloadReceive() {
        ClientPlayNetworking.registerGlobalReceiver(ForWardAndBackS2CPayload.ID,
                (forwardAndBackS2CPayload, client) -> {
                    forwardAndBackS2CPayload.receive(forwardAndBackS2CPayload, client);
                });
    }
}
