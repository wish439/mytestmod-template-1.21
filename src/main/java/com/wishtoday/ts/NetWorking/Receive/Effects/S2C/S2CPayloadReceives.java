package com.wishtoday.ts.NetWorking.Receive.Effects.S2C;

public interface S2CPayloadReceives {
    void ForWardAndBackS2CPayloadReceive();
    default void register(){
        ForWardAndBackS2CPayloadReceive();
    }
}
