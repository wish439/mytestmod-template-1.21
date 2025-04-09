package com.wishtoday.ts.Item.Custom;

import com.wishtoday.ts.Unit.Translation;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModIfyCommandText extends Item {

    public ModIfyCommandText(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        BlockState block = world.getBlockState(pos);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (block.isOf(Blocks.COMMAND_BLOCK) ||
        block.isOf(Blocks.CHAIN_COMMAND_BLOCK) ||
        block.isOf(Blocks.REPEATING_COMMAND_BLOCK)) {
            NbtCompound nbt = blockEntity.createNbt(world.getRegistryManager());
            String command = nbt.getString("Command");
            StringBuilder stringBuffer = new StringBuilder();
            if (command.contains("title") || command.contains("tell")) {
                Pattern pattern = Pattern.compile("\"([^\"]*)\""); // 匹配双引号内的内容
                Matcher matcher = pattern.matcher(command);
                while (matcher.find()) {
                    String match = matcher.group(1); // 获取双引号中的内容

                    if (match.equals("text")) continue;
                    // 处理翻译
                    String from = "en";
                    String to = "zh-CHS";
                    try {
                        String translate = Translation.translate(match, from, to);
                        //TranslationCommandMod.LOGGER.info("Translated: " + translate);
                        matcher.appendReplacement(stringBuffer, "\"" + translate + "\"");
                    } catch (Exception e) {
                        //TranslationCommandMod.LOGGER.error("翻译失败: " + match, e);
                    }
                    String newCommand = stringBuffer.toString();
                    if (command.contains("tellraw")) newCommand += "}";
                    nbt.putString("Command", newCommand);
                    blockEntity.read(nbt, world.getRegistryManager());
                }
                /*String[] s = command.split(" ");
                for (String string : s) {
                    player.sendMessage(Text.of(string), false);
                    if (string.matches("\"(?:[^\"\\\\]|\\\\.)*\"")) {
                        player.sendMessage(Text.of(string), false);
                        String from = "en";
                        String to = "zh-CHS";
                        try {
                            String translate = Translation.translate(string, from, to);
                            Mytestmod.LOGGER.info("Translated: " + translate);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }*/
            }

            blockEntity.read(nbt, world.getRegistryManager());
        }
        return super.useOnBlock(context);
    }
}
