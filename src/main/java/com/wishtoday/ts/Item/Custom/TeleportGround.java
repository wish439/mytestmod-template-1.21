package com.wishtoday.ts.Item.Custom;

import com.wishtoday.ts.Unit.BlockUnit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TeleportGround extends Item {
    public TeleportGround(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        int y = BlockUnit.getNotAirY(user);
        //user.sendMessage(Text.of("" + (y + 1)));
        user.teleport(user.getX(),y + 1 ,user.getZ(),false);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
