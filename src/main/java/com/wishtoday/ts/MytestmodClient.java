package com.wishtoday.ts;

import com.wishtoday.ts.Client.Event.RegisterEvent;
import net.fabricmc.api.ClientModInitializer;

public class MytestmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        new RegisterEvent();
    }
}
