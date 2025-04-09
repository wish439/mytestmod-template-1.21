package com.wishtoday.ts;

import net.minecraft.entity.EntityType;

import java.util.Set;

public class setStats {
    protected static Set<EntityType<?>> canSummonMonsters (Set<EntityType<?>> set){
        set.add(EntityType.PLAYER);
        set.add(EntityType.FALLING_BLOCK);
        set.add(EntityType.ARROW);
        return set;
    }
}
