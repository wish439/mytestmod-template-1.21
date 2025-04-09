package com.wishtoday.ts.Command.CommandValue;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.wishtoday.ts.Mytestmod;
import com.wishtoday.ts.Unit.BlockUnit;
import com.wishtoday.ts.Unit.PlayerUnit;
import net.minecraft.block.Block;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class RandomSkyBlock {
    private static int Time = 0;
    private static int ReFresh = 0;
    private static int REFRESHVALUE = 180;
    private static final Random RANDOM = new Random();
    private static final Logger log = LoggerFactory.getLogger(RandomSkyBlock.class);
    private static HashMap<String, Iterable<BlockPos>> map = new HashMap<>();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access) {
        dispatcher.register(literal("randomskyblock")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .then(literal("singlepos")
                        .then(argument("pos", BlockPosArgumentType.blockPos())
                                .then(argument("redis", IntegerArgumentType.integer(0))
                                        .then(argument("name1", StringArgumentType.string())
                                                .executes(context ->
                                                        executeAddOneBlockPosRegion(context,
                                                                BlockPosArgumentType.getBlockPos(context, "pos"),
                                                                IntegerArgumentType.getInteger(context, "redis"),
                                                                StringArgumentType.getString(context, "name1"))
                                                )
                                        )
                                )
                        )
                )
                .then(literal("twopos")
                        .then(argument("pos1", BlockPosArgumentType.blockPos())
                                .then(argument("pos2", BlockPosArgumentType.blockPos())
                                        .then(argument("name2", StringArgumentType.string())
                                                .executes(context ->
                                                        executeAddTwoBlockPosRegion(context,
                                                                BlockPosArgumentType.getBlockPos(context, "pos1"),
                                                                BlockPosArgumentType.getBlockPos(context, "pos2"),
                                                                StringArgumentType.getString(context, "name2")))
                                        )
                                )
                        )
                )
                .then(literal("remove")
                        .then(argument("regionname", StringArgumentType.string())
                                .executes(context ->
                                        executeRemove(context, StringArgumentType.getString(context, "regionname")
                                        )
                                )
                        )
                )
                .then(literal("removeall")
                        .executes(context ->
                                executeRemove(context))
                )
                .then(literal("setrefreshtime")
                        .then(argument("refreshtime", IntegerArgumentType.integer(0))
                                .executes(context -> executeSetReFresh(context, IntegerArgumentType.getInteger(context, "refreshtime"))
                                )
                        )
                )
                .then(literal("list")
                        .executes(RandomSkyBlock::executeGetList)
                )
        );
    }

    private static int executeAddOneBlockPosRegion(CommandContext<ServerCommandSource> context,
                                                   BlockPos pos,
                                                   int redis,
                                                   String name) {
        Iterable<BlockPos> iterate = BlockPos.iterate(new BlockPos(pos.getX() - redis, pos.getY() - redis, pos.getZ() - redis),
                new BlockPos(pos.getX() + redis, pos.getY() + redis, pos.getZ() + redis));
        return CheckAndAddmap(name, iterate, context);
    }

    private static int executeAddTwoBlockPosRegion(CommandContext<ServerCommandSource> context,
                                                   BlockPos pos1,
                                                   BlockPos pos2,
                                                   String name) {
        Iterable<BlockPos> iterate = BlockPos.iterate(pos1, pos2);
        return CheckAndAddmap(name, iterate, context);
    }

    private static int executeRemove(CommandContext<ServerCommandSource> context, String name) {
        map.remove(name);
        context.getSource().sendFeedback(() -> Text.of("Removed " + name), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int executeRemove(CommandContext<ServerCommandSource> context) {
        map.clear();
        return Command.SINGLE_SUCCESS;
    }

    public static void tick(MinecraftServer server) {
        if (!map.isEmpty()) {
            Time++;
            if (Time % 20 == 0) {
                Time = 0;
                ReFresh++;
            }
        } else {
            Time = 0;
            ReFresh = 0;
        }
        if (!server.getPlayerManager().getPlayerList().isEmpty() && !map.isEmpty()) {
            ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayerList().getFirst();
            PlayerUnit.TitleActionbarForPlayer(playerEntity, "距离下次刷新还有" + (REFRESHVALUE - ReFresh) + "秒");
        }
        if (ReFresh == REFRESHVALUE) {
            ReFresh = 0;
            World world = server.getPlayerManager().getPlayerList().getFirst().getWorld();
            for (Map.Entry<String, Iterable<BlockPos>> IterableEntry : map.entrySet()) {
                Iterable<BlockPos> iterate = IterableEntry.getValue();
                Block block = BlockUnit.getAllBlocks().get(RANDOM.nextInt(0, BlockUnit.getAllBlocks().size()));
                for (BlockPos pos : iterate) {
                    world.setBlockState(pos, block.getDefaultState());
                }
            }
        }
    }

    private static int CheckAndAddmap(String name, Iterable<BlockPos> iterate, CommandContext<ServerCommandSource> context) {
        if (!map.containsKey(name)) {
            map.put(name, iterate);
            System.out.println(map);
            context.getSource().sendFeedback(() -> Text.of("已将所选区域设置到" + name + "名字"), true);
            return Command.SINGLE_SUCCESS;
        }
        return -1;
    }

    private static int executeSetReFresh(CommandContext<ServerCommandSource> context, int value) {
        REFRESHVALUE = value;
        context.getSource().sendFeedback(() -> Text.of("刷新时间已设置为 : " + REFRESHVALUE), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int executeGetList(CommandContext<ServerCommandSource> context) {
        for (Map.Entry<String, Iterable<BlockPos>> IterableEntry : map.entrySet()) {
            String key = IterableEntry.getKey();
            context.getSource().sendFeedback(() -> Text.of(key), true);
        }
        return -1;
    }

}
