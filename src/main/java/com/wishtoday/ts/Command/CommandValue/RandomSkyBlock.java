package com.wishtoday.ts.Command.CommandValue;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.sun.jdi.connect.Connector;
import com.wishtoday.ts.Unit.BlockUnit;
import com.wishtoday.ts.Unit.PlayerUnit;
import net.minecraft.block.Block;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class RandomSkyBlock {
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
                        .then(argument("pos1", BlockStateArgumentType.blockState(access))
                                .then(argument("pos2", BlockStateArgumentType.blockState(access))
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
        return Command.SINGLE_SUCCESS;
    }

    private static int executeRemove(CommandContext<ServerCommandSource> context) {
        map.clear();
        return Command.SINGLE_SUCCESS;
    }

    public static void tick(int Time, int ReFresh, int ReFreshValue, MinecraftServer server) {
        Time++;
        if (Time % 20 == 0) {
            Time = 0;
            ReFresh++;
        }
        if (!server.getPlayerManager().getPlayerList().isEmpty()) {
            ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayerList().getFirst();
            PlayerUnit.TitleActionbarForPlayer(playerEntity,Time + "gt," + ReFresh + "gt2," + ReFreshValue + "gt3");
        }
        for (Map.Entry<String, Iterable<BlockPos>> IterableEntry : map.entrySet()) {
            Iterable<BlockPos> iterate = IterableEntry.getValue();
            if (ReFresh == ReFreshValue) {
                Random r = new Random();
                Block block = BlockUnit.getAllBlocks().get(r.nextInt(0, BlockUnit.getAllBlocks().size()));
                World world = server.getPlayerManager().getPlayerList().getFirst().getWorld();
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

}
