package com.wishtoday.ts.GameRule;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ModGameRules extends GameRules{
    public static GameRules.Key<GameRules.IntRule> TNTEXPLODEPOWER = GameRuleRegistry.register("tntexplodepower", GameRules.Category.MISC, GameRuleFactory.createIntRule(4));
    public static void init() {
    }
}
