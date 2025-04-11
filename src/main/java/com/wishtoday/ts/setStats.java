package com.wishtoday.ts;

import net.minecraft.entity.EntityType;

import java.util.Set;

public class setStats {
    protected static void canSummonMonsters (Set<EntityType<?>> set){
        set.add(EntityType.FALLING_BLOCK);
        set.add(EntityType.ARROW);
    }
    public static void init(){}
}
