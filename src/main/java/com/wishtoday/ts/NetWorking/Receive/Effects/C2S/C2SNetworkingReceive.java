package com.wishtoday.ts.NetWorking.Receive.Effects.C2S;

import com.wishtoday.ts.Mytestmod;
import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.KeepInventoryPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.TestPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.ToDeadPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.ModifyToBedrock.ModifyToBedrockPlayC2SPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.WalkDamage.DamagePayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.WalkDamage.ForWardAndBackC2SPayload;
import com.wishtoday.ts.NetWorking.Receive.Effects.AutoRegister;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.wishtoday.ts.Command.CommandValue.ModifyToBedrockCommand.playerAndStatePosList;

public class C2SNetworkingReceive implements C2SPayloadReceives {
    @AutoRegister
    @Override
    public void keepInventoryReceive() {
        ServerPlayNetworking.registerGlobalReceiver(KeepInventoryPayload.ID,
                (keepInventory, context) -> {
                    ServerPlayerEntity player = context.player();
                    if (!player.getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
                        player.getWorld().getGameRules().get(GameRules.KEEP_INVENTORY).set(true, player.getServer());
                        player.sendMessage(Text.of("已将死亡不掉落设置为开"));
                    } else {
                        player.sendMessage(Text.of("死亡不掉落已为true,无需更改"));
                    }
                }
        );
    }
    @AutoRegister
    @Override
    public void TestPayloadReceive() {
        ServerPlayNetworking.registerGlobalReceiver(
                TestPayload.ID,
                (testPayload,context) -> EntityType.CHICKEN.spawn((ServerWorld) context.player().getWorld(),context.player().getBlockPos(), SpawnReason.EVENT)
        );
    }
    @AutoRegister
    @Override
    public void ToDeadPayloadReceive() {
        ServerPlayNetworking.registerGlobalReceiver(
                ToDeadPayload.ID,
                (todead, context) -> {
                    ServerPlayerEntity player = context.player();
                    player.getLastDeathPos().ifPresentOrElse(
                            globalPos -> {
                                ServerWorld world = player.getServer().getWorld(globalPos.dimension());
                                BlockPos pos = globalPos.pos();
                                player.teleport(world, pos.getX(), pos.getY(), pos.getZ(), player.getYaw(), player.getPitch());
                            },
                            () -> player.sendMessage(Text.of("您还没有上次死亡点,请死亡后再试"))
                    );
                }
        );
    }
    @AutoRegister
    @Override
    public void ModifyToBedrockReceive() {
        ServerPlayNetworking.registerGlobalReceiver(ModifyToBedrockPlayC2SPayload.ID, (modifyToBedrockPlayC2SPayload, context) -> {
            BlockPos pos = modifyToBedrockPlayC2SPayload.pos();
            ServerPlayerEntity player = context.player();
            context.server().execute(() -> playerAndStatePosList.forEach(playerAndStatePos -> {
                if (playerAndStatePos.getPlayer().equals(player)) {
                    playerAndStatePos.setTargetPos(pos);
                }
            }));
        });
    }
    @AutoRegister
    @Override
    public void DamagePayloadReceive() {
        ServerPlayNetworking.registerGlobalReceiver(
                DamagePayload.ID,
                DamagePayload::receive
        );
    }
    @AutoRegister
    @Override
    public void ForWardAndBackC2SReceive() {
        ServerPlayNetworking.registerGlobalReceiver(ForWardAndBackC2SPayload.ID
        ,(forWardAndBackC2SPayload, context) ->
                        forWardAndBackC2SPayload.receive(forWardAndBackC2SPayload, context));
    }
}
