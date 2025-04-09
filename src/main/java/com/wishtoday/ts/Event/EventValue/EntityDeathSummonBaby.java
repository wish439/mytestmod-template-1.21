package com.wishtoday.ts.Event.EventValue;

import com.wishtoday.ts.Unit.EntityUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class EntityDeathSummonBaby {
    public static boolean execute(Entity entity) {
        if (entity instanceof MobEntity mobEntity) {
            //如果死亡实体有Baby形态并且不是Baby形态就继续
            if (EntityUnit.NoBabyInMobEntity(mobEntity) && !mobEntity.isBaby()) {
                World world = mobEntity.getWorld();
                //获取死亡实体的类型
                Entity mob = mobEntity.getType().create(world);
                //如果mob不为空
                if (mob != null) {
                    //设置mob为baby形态
                    ((MobEntity) mob).setBaby(true);
                    //设置mob的血量为mob的最高生命值
                    ((MobEntity) mob).setHealth(((MobEntity) mob).getMaxHealth());
                    //设置mob的位置在死亡实体的位置
                    mob.refreshPositionAndAngles(mobEntity.getX(), mobEntity.getY(), mobEntity.getZ(), mobEntity.getYaw(), mobEntity.getPitch());
                }
                //生成实体
                world.spawnEntity(mob);
                return true;
            }
        }
        return false;
    }
}


