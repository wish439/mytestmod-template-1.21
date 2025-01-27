package com.wishtoday.ts.Command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.BlockState;
import net.minecraft.command.CommandRegistryAccess;
import  net.minecraft.command.argument.BlockStateArgument;
import  net.minecraft.command.argument.BlockStateArgumentType;
import  net.minecraft.command.argument.EntityArgumentType;
import  net.minecraft.entity.player.PlayerEntity;
import  net.minecraft.server.command.CommandManager;
import  net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import  net.minecraft.text.Text;
import net.minecraft.util.Hand;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class InPlayerSetBlockCommand {
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(Text.translatable("commands.playsound.failed"));
    private static HashMap<PlayerEntity, BlockState> map = new HashMap<>();
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access) {
                dispatcher
                        .register(CommandManager.literal("inplayersetblock")
                                //.requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(1))
                                .then(
                                        CommandManager.argument("targets", EntityArgumentType.players())
                                                .executes(context -> execute(context.getSource()))
                                                .then(
                                                        CommandManager.argument("block", BlockStateArgumentType.blockState(access))
                                                                .executes(context -> execute(context.getSource(),BlockStateArgumentType.getBlockState(context,"block")))
                                        )
                        ));
    }
    private static int execute(ServerCommandSource serverCommandSource,BlockStateArgument block) {
        ServerWorld world = serverCommandSource.getWorld();
        Collection<String> PlayerNames = serverCommandSource.getPlayerNames();
            for (String PlayerName : PlayerNames) {
                PlayerEntity player = world.getServer().getPlayerManager().getPlayer(PlayerName);
                if (map.containsKey(player)){
                    map.remove(player);
                    serverCommandSource.sendFeedback(() -> Text.of(player.getDisplayName().getString() + "的放置方块已被关闭"),true);
                }
                else {
                    map.put(player,block.getBlockState());
                    serverCommandSource.sendFeedback(() -> Text.of(player.getDisplayName().getString() + "的放置方块已被开启"),true);
                }
            }
            return Command.SINGLE_SUCCESS;
    }
    private static int execute(ServerCommandSource serverCommandSource) throws CommandSyntaxException {
        ServerWorld world = serverCommandSource.getWorld();
        Collection<String> PlayerNames = serverCommandSource.getPlayerNames();
        for (String PlayerName : PlayerNames) {
            PlayerEntity player = world.getServer().getPlayerManager().getPlayer(PlayerName);
            if (map.containsKey(player)) {
                map.remove(player);
                serverCommandSource.sendFeedback(() -> Text.of(player.getDisplayName().getString() + "的放置方块已被关闭"), true);
            }
            return Command.SINGLE_SUCCESS;
        }
        throw FAILED_EXCEPTION.create();
    }
    public static void TickPlaceBlock(){
        for (Map.Entry<PlayerEntity, BlockState> PlayerEntityAndBlockState : map.entrySet()) {
            PlayerEntity player = PlayerEntityAndBlockState.getKey();
            player.getWorld().setBlockState(player.getBlockPos().down(),PlayerEntityAndBlockState.getValue());
        }
    }
}
