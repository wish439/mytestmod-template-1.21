package com.wishtoday.ts.Unit.Objs;

import com.wishtoday.ts.Enchant.Custom.TestEnchantmentEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.Objects;

public class EntityAndCool {
    private Entity TargetEntity;
    private int coolDown;
    private int numbers;
    private EntityType<? extends Entity> entity;

    public int getNumbers() {
        return this.numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public EntityType<? extends Entity> getEntity() {
        return this.entity;
    }

    public void setEntity(EntityType<? extends Entity> entity) {
        this.entity = entity;
    }

    public EntityAndCool(Entity TargetEntity, int numbers, EntityType<? extends Entity> entity) {
        this.TargetEntity = TargetEntity;
        this.numbers = numbers;
        this.coolDown = TestEnchantmentEffect.COOLDOWNTICK - 2;
        this.entity = entity;
    }

    public Entity getTargetEntity() {
        return this.TargetEntity;
    }

    public int getCoolDown() {
        return this.coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    @Override
    public String toString() {
        return "EntityAndCool{" +
                "entity=" + entity +
                ", coolDown=" + coolDown +
                ", numbers=" + numbers +
                ", TargetEntity=" + TargetEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EntityAndCool that = (EntityAndCool) o;
        return coolDown == that.coolDown && Objects.equals(entity, that.entity) && Objects.equals(numbers, that.numbers) && Objects.equals(TargetEntity, that.TargetEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, coolDown, numbers, TargetEntity);
    }
}
