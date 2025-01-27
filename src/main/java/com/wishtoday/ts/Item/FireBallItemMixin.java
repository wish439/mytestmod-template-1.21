package com.wishtoday.ts.Item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FireBallItemMixin extends Item implements ProjectileItem {

    public FireBallItemMixin(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            FireballEntity FireBall = new FireballEntity(world, user, user.getPos(),1);
            FireBall.setItem(stack);
            Vec3d vec3d = user.getRotationVec(1.0F);
            FireBall.setPosition(user.getX(), user.getY(), user.getZ() + 0.5);
            FireBall.setVelocity(user, user.getPitch() + 1, user.getYaw() + 1, 0.1F, 0.5F, 1.0F);
            world.spawnEntity(FireBall);
            if (!user.isCreative()) {
                stack.decrement(1);
            } else {
                stack.decrement(0);
            }
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        Random random = world.getRandom();
        double d = random.nextTriangular((double)direction.getOffsetX(), 0.11485000000000001);
        double e = random.nextTriangular((double)direction.getOffsetY(), 0.11485000000000001);
        double f = random.nextTriangular((double)direction.getOffsetZ(), 0.11485000000000001);
        Vec3d vec3d = new Vec3d(d, e, f);
        SmallFireballEntity smallFireballEntity = new SmallFireballEntity(world, pos.getX(), pos.getY(), pos.getZ(), vec3d.normalize());
        smallFireballEntity.setItem(stack);
        return smallFireballEntity;
    }
}
