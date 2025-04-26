package com.wishtoday.ts.Command.CommandValue;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WalkDamageCommand {
    public static List<PlayerEntity> walkDamageTarget = new ArrayList<>();
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.
                register(CommandManager.literal("walkdamage")
                        .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                        .then(
                                CommandManager.argument("targets", EntityArgumentType.players())
                                        .executes(context -> execute(EntityArgumentType.getPlayers(context, "targets"), context.getSource()))
                        ));
    }
    private static int execute(Collection<ServerPlayerEntity> players, ServerCommandSource source) {
        for (ServerPlayerEntity player : players) {
            if (walkDamageTarget.contains(player)) {
                walkDamageTarget.remove(player);
                source.sendFeedback(() -> Text.of("已将" + player.getName().getString() + "走路扣血功能开启"), false);
            } else {
                source.sendFeedback(() -> Text.of("已将" + player.getName().getString() + "走路扣血功能开启"), false);
                walkDamageTarget.add(player);
            }
        }
        return 1;
    }
}
