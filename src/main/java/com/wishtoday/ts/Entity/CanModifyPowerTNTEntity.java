package com.wishtoday.ts.Entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CanModifyPowerTNTEntity extends TntEntity {
    private static final TrackedData<Integer> POWER = DataTracker.registerData(CanModifyPowerTNTEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final ExplosionBehavior TELEPORTED_EXPLOSION_BEHAVIOR = new ExplosionBehavior() {
        @Override
        public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
            return !state.isOf(Blocks.NETHER_PORTAL) && super.canDestroyBlock(explosion, world, pos, state, power);
        }

        @Override
        public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
            return blockState.isOf(Blocks.NETHER_PORTAL) ? Optional.empty() : super.getBlastResistance(explosion, world, pos, blockState, fluidState);
        }
    };

    public CanModifyPowerTNTEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter,int power) {
        super(world, x, y, z, igniter);
        this.setPower(power);
    }

    public CanModifyPowerTNTEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(POWER,4);
        super.initDataTracker(builder);
    }
    public void setPower(int power) {
        this.dataTracker.set(POWER,power);
    }
    public int getPower() {
        return this.dataTracker.get(POWER);
    }
    @Override
    public void tick() {
        this.tickPortalTeleportation();
        this.applyGravity();
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }
    private void explode() {
        World world = this.getWorld();
        BlockPos yuan = this.getBlockPos();
        for (int x = -100; x <= 100; x++) {
            for (int y = -200 / 2; y <= 100 / 2; y++) { // 控制 Y 轴范围，使坑不太深
                for (int z = -100; z <= 100; z++) {
                    int dx = x, dy = y, dz = z;

                    // 使用球形公式判断是否在圆形坑内
                    if (dx * dx + dy * dy + dz * dz <= 10000) {
                        BlockPos pos = yuan.add(dx, dy, dz);
                        world.setBlockState(pos, Blocks.AIR.getDefaultState()); // 设置为空气，制造坑洞
                    }
                }
            }
        }
        /*this.getWorld()
                .createExplosion(
                        this,
                        Explosion.createDamageSource(this.getWorld(), this),
                        TELEPORTED_EXPLOSION_BEHAVIOR,
                        this.getX(),
                        this.getBodyY(0.0625),
                        this.getZ(),
                        this.getPower(),
                        false,
                        World.ExplosionSourceType.TNT
                );*/
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("power", this.getPower());
       super.writeCustomDataToNbt(nbt);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.setPower(nbt.getInt("power"));
        super.readCustomDataFromNbt(nbt);
    }
}
