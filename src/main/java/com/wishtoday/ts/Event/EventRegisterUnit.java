package com.wishtoday.ts.Event;

import com.wishtoday.ts.Client.Event.RegisterEvent;
import com.wishtoday.ts.Command.CommandValue.InPlayerSetBlockCommand;
import com.wishtoday.ts.Command.CommandValue.ModifyToBedrockCommand;
import com.wishtoday.ts.Command.CommandValue.RandomSkyBlock;
import com.wishtoday.ts.Death;
import com.wishtoday.ts.Enchant.Custom.TestEnchantmentEffect;
import com.wishtoday.ts.Event.EventValue.EntityDeathSummonBaby;
import com.wishtoday.ts.Event.EventValue.VillagerInfTrade;
import com.wishtoday.ts.Event.RegisterEvent.PlayerInteractEvents;
import com.wishtoday.ts.Event.RegisterEvent.TimeEvents;
import com.wishtoday.ts.Item.Custom.RevengeItem;
import com.wishtoday.ts.Mytestmod;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class EventRegisterUnit {
    public EventRegisterUnit() {
        registerCommandEvents();
        registerTickEvents();
        registerServerLivingEvents();
        registerUseEntity();
        registerTimeEvent();
        registerPlayerQuitEvents();
        registerPlayerInteractEvents();
    }

    //注册命令
    private void registerCommandEvents() {
        CommandRegistrationCallback.EVENT.register((dispatcher,
                                                    access,
                                                    Environment) -> {
            RandomSkyBlock.register(dispatcher, access);
            InPlayerSetBlockCommand.register(dispatcher, access);
            ModifyToBedrockCommand.register(dispatcher);
        });
    }

    //注册Tick事件
    private void registerTickEvents() {
        ServerTickEvents.START_SERVER_TICK.register((server) ->
        {
            //SummonStatusEffect.EventTick();
            TestEnchantmentEffect.EventTick();
            InPlayerSetBlockCommand.TickPlaceBlock();
            //RandomSkyBlock.tick(Time,ReFresh,REFRESHVALUE,server);
        });
        ServerTickEvents.START_SERVER_TICK.register(RandomSkyBlock::tick
        );
        ServerTickEvents.START_SERVER_TICK.register(server ->
                ModifyToBedrockCommand.ModifyBlock());

        RegistryEntryAddedCallback.
                event(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE).
                register(((i,
                           identifier,
                           componentType) -> {

                }));
    }

    //注册服务器生物事件
    private void registerServerLivingEvents() {
        ServerLivingEntityEvents.ALLOW_DEATH.register(((entity, damageSource, damageAmount) -> {
            if (entity instanceof PlayerEntity) return !Death.TryUseTotemMod(damageSource, (PlayerEntity) entity);
            EntityDeathSummonBaby.execute(entity);
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

    private void registerTimeEvent() {
        TimeEvents.EVENT.register(server -> {
            HitResult target = MinecraftClient.getInstance().crosshairTarget;
            if (target != null && target.getType().equals(HitResult.Type.BLOCK)) {
                Mytestmod.LOGGER.info(((BlockHitResult) target).getBlockPos().toString());
            }
        });
    }
    private void registerPlayerQuitEvents() {
        ServerPlayConnectionEvents.DISCONNECT.register(
                (playNetworkHandler,
                 server) -> {
                    ServerPlayerEntity player = playNetworkHandler.getPlayer();
                    ModifyToBedrockCommand.undoBlock(player);
                });
    }
    private void registerPlayerInteractEvents() {
        PlayerInteractEvents.ATTACK_ENTITY.register(RevengeItem::onAttack);
    }
}
