package com.wishtoday.ts.Event.RegisterEvent;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public interface PlayerInteractEvents {
    Event<AttackEntity> ATTACK_ENTITY = EventFactory.createArrayBacked(AttackEntity.class, (listeners) -> (player, server, target) -> {
        boolean result = true;
        for (AttackEntity listener : listeners) {
            if (!listener.attackEntity(player, server, target)) {
                result = false;
            }
        }
        return result;
    });

    @FunctionalInterface
    public interface AttackEntity {
        boolean attackEntity(PlayerEntity player, MinecraftServer server, Entity target);
    }
    @FunctionalInterface
    public interface Attack {
        boolean attack(PlayerEntity player);
    }
}
