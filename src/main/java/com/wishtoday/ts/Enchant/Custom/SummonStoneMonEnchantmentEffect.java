package com.wishtoday.ts.Enchant.Custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wishtoday.ts.Mytestmod;
import com.wishtoday.ts.Unit.Box;
import com.wishtoday.ts.stats;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class SummonStoneMonEnchantmentEffect implements EnchantmentEntityEffect {
    private static final Block blockType = Blocks.STONE;
    public static final MapCodec<SummonStoneMonEnchantmentEffect> CODEC = MapCodec.unit(SummonStoneMonEnchantmentEffect::new);
    @Override
    public void apply(ServerWorld world,
                      int level,
                      EnchantmentEffectContext context,
                      Entity entity, Vec3d pos) {
        if (canSummonMon(entity)) {
            Random r = new Random();
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
            //Mytestmod.LOGGER.info(box.toString());
            Box.create(blockPos1, blockPos).traverse(blockpos -> setBlock(entity.getWorld(), r, blockpos, blockType,level));
        }
    }
    private static boolean canSummonMon(Entity entity) {
        return !stats.canSummonMonsters.contains(entity.getType());
    }
    private static boolean setBlock(World world, Random random, BlockPos pos, Block block,int level) {
        if (random.nextInt(0, 10) >= level) {
            FallingBlockEntity.spawnFromBlock(world, pos, block.getDefaultState());
            return true;
        }
        return false;
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
