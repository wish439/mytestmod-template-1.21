package com.wishtoday.ts.NetWorking.Receive;

import com.wishtoday.ts.NetWorking.Receive.Effects.C2S.C2SNetworkingReceive;
import com.wishtoday.ts.NetWorking.Receive.Effects.S2C.S2CNetworkingReceive;

public class RegisterReceive {
    public static void registerC2S(){
        C2SNetworkingReceive C2SNetworking = new C2SNetworkingReceive();
        C2SNetworking.register();
        /*C2SNetworking.TestPayloadReceive();
        C2SNetworking.DamagePayloadReceive();
        C2SNetworking.ModifyToBedrockReceive();
        C2SNetworking.ToDeadPayloadReceive();
        C2SNetworking.keepInventoryReceive();
        C2SNetworking.ForWardAndBackC2SReceive();*/
    }
    public static void registerS2C(){
        S2CNetworkingReceive S2CNetworking = new S2CNetworkingReceive();
        S2CNetworking.ForWardAndBackS2CPayloadReceive();
    }
    public static void register(){
        registerC2S();
        registerS2C();
    }
}
