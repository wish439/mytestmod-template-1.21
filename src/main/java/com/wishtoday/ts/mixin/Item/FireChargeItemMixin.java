package com.wishtoday.ts.mixin.Item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireChargeItem.class)
public class FireChargeItemMixin extends Item {
    public FireChargeItemMixin(Item.Settings settings) {
        super(settings);
    }

    @Override
    @Unique
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        //Vec3d direction = user.getPos().subtract(1,2,3);
        //user.addVelocity(direction.x, direction.y, direction.z);
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            FireballEntity FireBall = new FireballEntity(world, user, user.getRotationVector(),3);
            FireBall.setItem(stack);
            //Vec3d vec3d = user.getRotationVec(1.0F);
            //FireBall.setPosition(user.getX(), user.getY() + 1, user.getZ() + 0.5);
            FireBall.setVelocity(user,user.getPitch() , user.getHeadYaw(), 0.0F, 1.1F, 1.0F);
            Vec3d rotationVector = user.getEyePos();
            Vec3d multiply = rotationVector.add(user.getRotationVector());
            FireBall.setPosition(multiply);
            world.spawnEntity(FireBall);
            stack.decrementUnlessCreative(1,user);
        }
        return TypedActionResult.success(stack);
    }
    @Inject(method = "useOnBlock",at = @At("HEAD"),cancellable = true)
    private void onUse(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        this.use(context.getWorld(), context.getPlayer(), context.getHand());
        cir.setReturnValue(ActionResult.SUCCESS);
    }
}
