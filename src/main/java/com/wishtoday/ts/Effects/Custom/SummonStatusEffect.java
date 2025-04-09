package com.wishtoday.ts.Effects.Custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class SummonStatusEffect extends StatusEffect {
    //private static List<EntityAndCool> entities = new ArrayList<>();
    static final int COOLDOWNTICK = 10;
    public SummonStatusEffect() {
        super(
                StatusEffectCategory.HARMFUL, // 药水效果是有益的还是有害的
                0x98D982); // 显示的颜色
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
       return duration % COOLDOWNTICK == 0;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld().isClient) return false;
        for (int i = 0; i < amplifier + 1; i++) {
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) entity.getWorld(),entity.getBlockPos(),SpawnReason.EVENT);
        }
        return true;
        //return entities.add(new EntityAndCool(entity, amplifier));
    }
}