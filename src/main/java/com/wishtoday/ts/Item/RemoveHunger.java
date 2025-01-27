package com.wishtoday.ts.Item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RemoveHunger extends Item {
    public RemoveHunger(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getHungerManager().setFoodLevel(user.getHungerManager().getFoodLevel() - 2);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
