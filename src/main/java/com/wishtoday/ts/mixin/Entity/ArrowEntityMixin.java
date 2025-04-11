package com.wishtoday.ts.mixin.Entity;

import com.wishtoday.ts.Mytestmod;
import com.wishtoday.ts.Unit.Box;
import com.wishtoday.ts.stats;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(ArrowEntity.class)
public class ArrowEntityMixin {
    private static int shouldSet = 5;
    private static Block blockType = Blocks.STONE;
    @Inject(method = "onHit",at = @At("HEAD"))
    protected void onEntityHit(LivingEntity entity, CallbackInfo ci) {
        ArrowEntity arrowEntity = (ArrowEntity) (Object) this;
        if (!(arrowEntity.getOwner() instanceof PlayerEntity)
                && !(entity instanceof PlayerEntity)) {
            Mytestmod.LOGGER.info("取消");
            return;
        }
        Random r = new Random();
        World world = entity.getWorld();
        if (canSummonMon(entity)) {
            BlockPos blockPos = entity.getBlockPos().up(10);
            BlockPos blockPos1 = blockPos.east(10).north(10);
            blockPos = blockPos.south(10).west(10).up(10);
            BlockPos pos1 = blockPos.down(9);
            BlockPos pos2 = blockPos1.down(20);
            Vec3d ved1 = pos1.toCenterPos();
            Vec3d ved2 = pos2.toCenterPos();
            net.minecraft.util.math.Box box = new net.minecraft.util.math.Box(ved1, ved2);
            List<ServerPlayerEntity> Players = world.getEntitiesByType(TypeFilter.equals(ServerPlayerEntity.class), box, EntityPredicates.EXCEPT_SPECTATOR);
            for (PlayerEntity player : Players) {
                player.teleport(player.getX() + 20, player.getY(), player.getZ(), false);
            }
            Box.create(blockPos1, blockPos).traverse(pos -> setBlock(entity.getWorld(), r, pos, blockType));
        }
    }
    private static boolean canSummonMon(Entity entity) {
        return !stats.canSummonMonsters.contains(entity.getType());
    }

    private static boolean setBlock(World world, Random random, BlockPos pos, Block block) {
        if (random.nextInt(0, 10) >= shouldSet) {
            FallingBlockEntity.spawnFromBlock(world, pos, block.getDefaultState());
            return true;
        }
        return false;
    }

}
