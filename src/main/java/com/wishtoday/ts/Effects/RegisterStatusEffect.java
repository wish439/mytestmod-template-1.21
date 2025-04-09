package com.wishtoday.ts.Effects;

import com.wishtoday.ts.Effects.Custom.SummonStatusEffect;
import com.wishtoday.ts.Mytestmod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class RegisterStatusEffect {
    public static final RegistryEntry<StatusEffect> SUMMONSTATUSEFFECT = of("summon_status_effect",new SummonStatusEffect());
    private static RegistryEntry<StatusEffect> of(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Mytestmod.MOD_ID, id), statusEffect);
    }
    public static void init() {}
}
