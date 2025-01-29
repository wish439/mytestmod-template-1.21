package com.wishtoday.ts.Event;

import com.wishtoday.ts.Command.CommandValue.InPlayerSetBlockCommand;
import com.wishtoday.ts.Command.CommandValue.RandomSkyBlock;
import com.wishtoday.ts.Death;
import com.wishtoday.ts.Event.EventValue.VillagerInfTrade;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;

public class EventRegisterUnit {
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
        ServerTickEvents.START_SERVER_TICK.register(RandomSkyBlock::tick
                );

        RegistryEntryAddedCallback.
                event(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE).
                register(((i,
                           identifier,
                           componentType) -> {

        }));
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
