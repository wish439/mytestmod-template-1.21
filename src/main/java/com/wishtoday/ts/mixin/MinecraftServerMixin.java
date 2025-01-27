package com.wishtoday.ts.mixin;

import com.google.common.base.Function;
import com.wishtoday.ts.Unit.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.registry.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {


    @Shadow
    public abstract boolean isUsingNativeTransport();

    @Shadow
    @Final
    private static Logger LOGGER;

    @Shadow
    public abstract DynamicRegistryManager.Immutable getRegistryManager();

    @Shadow
    protected abstract void pushPerformanceLogs();

    @Shadow
    protected abstract void pushTickLog(long tickStartTime);

    private static int Time = 0;

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickMixin(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        Time++;
        MinecraftServer minecraftServer = ((MinecraftServer) (Object) this);
        if (!minecraftServer.getPlayerManager().getPlayerList().isEmpty()) {
            ServerPlayerEntity player = minecraftServer.getPlayerManager().getPlayerList().getFirst();
            World world = player.getWorld();
            for (ServerPlayerEntity playerEntity : minecraftServer.getPlayerManager().getPlayerList()) {
                PlayerUnit.TitleActionbarForPlayer(playerEntity, "距离每5秒一次还有" + (100 - Time) + "gt");
            }


            //if (!player.getWorld().isClient) PlayerUnit.TitleActionbarForPlayer(player, "距离每5秒一次还有" + (100 - Time) + "gt");
            if (Time >= 100) {
                //ServerPlayerEntity player = minecraftServer.getPlayerManager().getPlayerList().getFirst();
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 100 * 20, 2));
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    EntityUnit.KillCustomEntity(serverPlayerEntity, 30, ItemEntity.class);
                }
                ItemUnit.QuickRemoveItem(Items.DIAMOND, player);
                Time = 0;
            }
        }
    }
}

