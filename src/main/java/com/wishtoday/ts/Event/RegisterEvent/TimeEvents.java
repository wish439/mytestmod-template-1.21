package com.wishtoday.ts.Event.RegisterEvent;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;

public interface TimeEvents {
    Event<TimeEvents> EVENT =
            EventFactory.createArrayBacked
                    (TimeEvents.class, listeners -> (server) -> {
                        for (TimeEvents listener : listeners) {
                            listener.interact(server);
                        }
                    });

    void interact(MinecraftServer server);
}
