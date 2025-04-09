package com.wishtoday.ts.Client.Event;

import com.wishtoday.ts.Client.Event.EventValue.ModifyToBedrockNetWorking;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class RegisterEvent {
    public RegisterEvent() {
        RegisterTickEvents();
    }
    private void RegisterTickEvents(){
        ClientTickEvents.START_CLIENT_TICK.register(ModifyToBedrockNetWorking::register);
    }
}
