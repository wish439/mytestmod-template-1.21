package com.wishtoday.ts.Command.CommandValue;

import com.mojang.brigadier.CommandDispatcher;
import com.wishtoday.ts.Unit.Objs.PlayerAndStatePos;
import com.wishtoday.ts.Unit.Objs.WorldPos;
import com.wishtoday.ts.Unit.Objs.WorldStatePos;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModifyToBedrockCommand {
    public static List<PlayerAndStatePos> playerAndStatePosList = new ArrayList<>();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.
                register(CommandManager.literal("modifytobedrock")
                        .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                        .then(
                                CommandManager.argument("targets", EntityArgumentType.players())
                                        .executes(context -> execute(EntityArgumentType.getPlayer(context, "targets"), context.getSource()))
                        ));
    }



    private static int execute(ServerPlayerEntity player, ServerCommandSource source) {
        Iterator<PlayerAndStatePos> iterator = playerAndStatePosList.iterator();
        while (iterator.hasNext()) {
            PlayerAndStatePos playerAndStatePos = iterator.next();
            if (playerAndStatePos.getPlayer().equals(player)) {
                source.sendFeedback(() -> Text.of("已将" + player.getDisplayName().getString() + "的变基岩关闭"), false);
                undoBlock(player);
                return 1;
            }
        }
        playerAndStatePosList.add(new PlayerAndStatePos(player, new ArrayList<>()));
        source.sendFeedback(() -> Text.of("已将" + player.getDisplayName().getString() + "的变基岩打开"), false);
        return 1;
    }

    //需要用事件结束tick驱动
    public static void ModifyBlock() {
        playerAndStatePosList.forEach(playerAndStatePos -> {
            //获取世界
            World world = playerAndStatePos.getPlayer().getWorld();
            BlockPos targetPos = playerAndStatePos.getTargetPos();
            //如果目标方块位置不为空并且目标方块不为基岩就执行以下操作
            if (targetPos != null &&
                    !playerAndStatePos.targetBlockIsOf(Blocks.BEDROCK)) {
                //将目标方块原本的BlockState保存下来
                playerAndStatePos.getStatePos()
                        .add(new WorldStatePos(new WorldPos(world, targetPos)
                                , world.getBlockState(targetPos)));
                //将目标方块设置为基岩
                playerAndStatePos.targetPosSet(Blocks.BEDROCK.getDefaultState());
            }
        });
    }

    //用于撤销方块更改，可用于移除玩家和玩家退出时
    public static void undoBlock(PlayerEntity player) {
        Iterator<PlayerAndStatePos> iterator = playerAndStatePosList.iterator();
        while (iterator.hasNext()) {
            PlayerAndStatePos playerAndStatePos = iterator.next();
            if (playerAndStatePos.getPlayer().equals(player)) {
                playerAndStatePos.place();
                iterator.remove();
            }
        }
    }
}