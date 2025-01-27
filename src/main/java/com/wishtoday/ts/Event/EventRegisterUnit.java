package com.wishtoday.ts.Event;

import com.wishtoday.ts.Command.InPlayerSetBlockCommand;
import com.wishtoday.ts.Death;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;


public class EventRegisterUnit {
    public EventRegisterUnit() {
        registerCommandEvents();
        registerTickEvents();
        registerServerLivingEvents();
    }

    //注册命令
    private void registerCommandEvents() {
        CommandRegistrationCallback.EVENT.register((dispatcher,
                                                    access,
                                                    Environment) -> {
            InPlayerSetBlockCommand.register(dispatcher, access);
        });
    }

    //注册Tick事件
    private void registerTickEvents() {
        ServerTickEvents.START_SERVER_TICK.register((server) -> InPlayerSetBlockCommand.TickPlaceBlock());
    }

    private void registerServerLivingEvents() {
        ServerLivingEntityEvents.ALLOW_DEATH.register(((entity, damageSource, damageAmount) -> {
            if (entity instanceof PlayerEntity) return !Death.TryUseTotemMod(damageSource, (PlayerEntity) entity);
            return true;
        }));
    }
}
