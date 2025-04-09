package com.wishtoday.ts.Enchant.Custom;

import com.mojang.serialization.MapCodec;
import com.wishtoday.ts.Effects.RegisterStatusEffect;
import com.wishtoday.ts.Unit.Objs.EntityAndCool;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.*;

public class TestEnchantmentEffect implements EnchantmentEntityEffect {
    private static List<EntityAndCool> entities = new ArrayList<>();
    public static final int COOLDOWNTICK = 10;
    private static final int TICKTIME = 20;
    public static final MapCodec<TestEnchantmentEffect> CODEC = MapCodec.unit(TestEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity living) {
            if (living.hasStatusEffect(RegisterStatusEffect.SUMMONSTATUSEFFECT)) {
                StatusEffectInstance effect = living.getStatusEffect(RegisterStatusEffect.SUMMONSTATUSEFFECT);
                if (effect != null) {
                    effect.upgrade(new StatusEffectInstance(RegisterStatusEffect.SUMMONSTATUSEFFECT, level * TICKTIME, effect.getAmplifier() + 1, false, false));
                }
                if (!living.addStatusEffect(effect))
                    entities.add(new EntityAndCool(user, level, EntityType.LIGHTNING_BOLT));

            } else {
                if (!living.addStatusEffect(new StatusEffectInstance(RegisterStatusEffect.SUMMONSTATUSEFFECT, level * TICKTIME, 0, false, false, false)))
                    entities.add(new EntityAndCool(user, level, EntityType.LIGHTNING_BOLT));
            }
        } else {
            if (user.isInvulnerableTo(user.getWorld().getDamageSources().create(DamageTypes.LIGHTNING_BOLT)))
                entities.add(new EntityAndCool(user, level, EntityType.LIGHTNING_BOLT));
            else {
                entities.add(new EntityAndCool(user, level, EntityType.CREEPER));
            }
        }
        /*for (int i = 0; i < level; i++) {
            EntityType.LIGHTNING_BOLT.spawn(world, user.getBlockPos(), SpawnReason.EVENT);
        }*/
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }

    public static void EventTick() {
        if (entities.isEmpty()) return;
        Iterator<EntityAndCool> iterator = entities.iterator();
        while (iterator.hasNext()) {
            EntityAndCool entity = iterator.next();
            Entity entity1 = entity.getTargetEntity();
            //Mytestmod.LOGGER.info(entity1.toString());
            if (entity1 instanceof LivingEntity livingEntity) {
                if (!livingEntity.isAlive()) {
                    iterator.remove();
                    continue;
                }
            }
            if (entity.getNumbers() <= 0) {
                iterator.remove();
                continue;
            }
            if (entity.getCoolDown() < COOLDOWNTICK) {
                entity.setCoolDown(entity.getCoolDown() + 1);
            }
            if (entity.getCoolDown() == COOLDOWNTICK) {
                entity.setCoolDown(0);
                spawnEntity(entity, entity1);
                //entity.getEntity().getWorld().spawnEntity(new LightningEntity(EntityType.LIGHTNING_BOLT,entity.getEntity().getWorld()));
                //entity.getEntity().spawn((ServerWorld) entity1.getWorld(), entity1.getBlockPos(), SpawnReason.EVENT);
                //EntityType.LIGHTNING_BOLT.spawn((ServerWorld) entity1.getWorld(), entity.getTargetEntity().getBlockPos(), SpawnReason.EVENT);
                entity.setNumbers(entity.getNumbers() - 1);
            }
        }
    }

    private static void spawnEntity(EntityAndCool entity, Entity entity1) {
        Entity entity2 = entity.getEntity().create(entity1.getWorld());
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putInt("Fuse", 0);
        entity2.readNbt(nbtCompound);
        entity2.setPosition(entity1.getPos());
        entity1.getEntityWorld().spawnEntity(entity2);
    }
}

