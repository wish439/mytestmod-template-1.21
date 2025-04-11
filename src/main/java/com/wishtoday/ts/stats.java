package com.wishtoday.ts;

import net.minecraft.entity.EntityType;

import java.util.HashSet;
import java.util.Set;

public class stats {
    public static Set<EntityType<?>> canSummonMonsters = new HashSet<>();
    public static void initStats() {
        setStats.canSummonMonsters(canSummonMonsters);
    }
}
