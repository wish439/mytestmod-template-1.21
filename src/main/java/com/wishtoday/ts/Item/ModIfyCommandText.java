package com.wishtoday.ts.Item;

import com.wishtoday.ts.Mytestmod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModIfyCommandText extends Item {

    public ModIfyCommandText(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockState block = world.getBlockState(pos);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (block.hasBlockEntity()) {
            NbtCompound nbt = blockEntity.createNbt(world.getRegistryManager());
            String command = nbt.getString("Command");
            if (command.contains("title")) {
                String[] s = command.split(" ");
                for (int i = 0; i < s.length; i++) {
                    if (s[i].matches("\"(?:[^\"\\\\]|\\\\.)*\"")) {
                        player.sendMessage(Text.of(s[i]), false);
                    }
                }
            }

            blockEntity.read(nbt,world.getRegistryManager());
        }
        return super.useOnBlock(context);
    }
}
