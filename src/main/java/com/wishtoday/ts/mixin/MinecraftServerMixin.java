package com.wishtoday.ts.mixin;

import com.wishtoday.ts.Unit.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
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

    @Shadow
    protected abstract void pushFullTickLog();

    @Shadow
    public abstract void endTickMetrics();


    //private static List<RegistryEntry<Enchantment>> enchantments = new ArrayList<>();

    @Shadow public abstract void executeSync(Runnable runnable);

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickMixin(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        MinecraftServer minecraftServer = ((MinecraftServer) (Object) this);
        long time = 0;
        for (ServerPlayerEntity player : minecraftServer.getPlayerManager().getPlayerList()) {
            World world = player.getWorld();
            time = world.getTime();
            //RegistryEntry<Enchantment> enchantment = ObjectiveUnit.getEnchantmentByName(world, "github");
            //List<RegistryEntry<Enchantment>> enchantmentsList = ObjectiveUnit.getEnchantmentsList(world);
            if (time % 100 == 0) {
                /*MinecraftClient client = MinecraftClient.getInstance();
                Vec3d vec3d = client.player.getEyePos().add(0, 1, 0);
                BlockPos StartBlockPos = BlockUnit.EasyCreateBlockPos(vec3d.x - 2, vec3d.y - 2, vec3d.z - 2);
                BlockPos EndBlockPos = BlockUnit.EasyCreateBlockPos(vec3d.x + 2, vec3d.y + 2, vec3d.z + 2);
                Iterable<BlockPos> iterate = BlockPos.iterate(StartBlockPos, EndBlockPos);
                for (BlockPos pos : iterate) {
                    BlockHitResult blockHitResult = new BlockHitResult(new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5), Direction.DOWN,pos, false);
                    client.interactionManager.interactBlock(client.player, Hand.MAIN_HAND,blockHitResult);
                }*/
                //ServerPlayerEntity player = minecraftServer.getPlayerManager().getPlayerList().getFirst();
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 100 * 20, 2));
                /*for (RegistryEntry<Enchantment> entry : enchantmentsList) {
                    player.getMainHandStack().addEnchantment(entry,entry.value().getMaxLevel());
                }*/
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    EntityUnit.KillCustomEntity(serverPlayerEntity, 30, ItemEntity.class);
                }
                ItemUnit.QuickRemoveItem(Items.DIAMOND, player);
            }
        }
        //RandomSkyBlock.tick(Time,ReFresh,REFRESHVALUE,minecraftServer);
            /*for (ServerPlayerEntity playerEntity : minecraftServer.getPlayerManager().getPlayerList()) {
                PlayerUnit.TitleActionbarForPlayer(playerEntity, "距离每5秒一次还有" + (100 - Time) + "gt");
            }*/


        //if (!player.getWorld().isClient) PlayerUnit.TitleActionbarForPlayer(player, "距离每5秒一次还有" + (100 - Time) + "gt");
    }
}

