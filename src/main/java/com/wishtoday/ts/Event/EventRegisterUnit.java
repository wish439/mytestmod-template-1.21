package com.wishtoday.ts.Event;

import com.wishtoday.ts.Command.CommandValue.InPlayerSetBlockCommand;
import com.wishtoday.ts.Command.CommandValue.RandomSkyBlock;
import com.wishtoday.ts.Death;
import com.wishtoday.ts.Event.EventValue.VillagerInfTrade;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.player.PlayerEntity;

public class EventRegisterUnit {
    private static int Time = 0;
    private static int ReFresh = 0;
    private static final int REFRESHVALUE = 180;
    public EventRegisterUnit() {
        registerCommandEvents();
        registerTickEvents();
        registerServerLivingEvents();
        registerUseEntity();
    }

    //注册命令
    private void registerCommandEvents() {
        CommandRegistrationCallback.EVENT.register((dispatcher,
                                                    access,
                                                    Environment) -> {
            RandomSkyBlock.register(dispatcher,access);
            InPlayerSetBlockCommand.register(dispatcher, access);
        });
    }

    //注册Tick事件
    private void registerTickEvents() {
        ServerTickEvents.START_SERVER_TICK.register((server) ->
        {
            InPlayerSetBlockCommand.TickPlaceBlock();
            //RandomSkyBlock.tick(Time,ReFresh,REFRESHVALUE,server);
        });
        /*ServerTickEvents.END_SERVER_TICK.register(server ->
                RandomSkyBlock.tick(Time,ReFresh,REFRESHVALUE,server));*/
    }

    private void registerServerLivingEvents() {
        ServerLivingEntityEvents.ALLOW_DEATH.register(((entity, damageSource, damageAmount) -> {
            if (entity instanceof PlayerEntity) return !Death.TryUseTotemMod(damageSource, (PlayerEntity) entity);
            return true;
        }));
    }

    private void registerUseEntity() {
        UseEntityCallback.EVENT.register(((player,
                                           world,
                                           hand,
                                           entity,
                                           hitResult) ->
                VillagerInfTrade.ResetVillagerTrade(player, entity, hand, world, hitResult)));
    }
}
