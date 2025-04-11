package com.wishtoday.ts.Enchant;

import com.mojang.serialization.MapCodec;
import com.wishtoday.ts.Enchant.Custom.SummonStoneMonEnchantmentEffect;
import com.wishtoday.ts.Enchant.Custom.TestEnchantmentEffect;
import com.wishtoday.ts.Mytestmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class RegisterEnchantmentEffects {
    public static MapCodec<TestEnchantmentEffect> TESTENCHANT_EFFECT = register("testenchant_effect", TestEnchantmentEffect.CODEC);
    public static MapCodec<SummonStoneMonEnchantmentEffect> SUMMON_STONE_MON_EFFECT = register("summon_stone_mon_effect", SummonStoneMonEnchantmentEffect.CODEC);

    private static RegistryKey<Enchantment> of(String path) {
        Identifier id = Identifier.of(Mytestmod.MOD_ID, path);
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
    }

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(Mytestmod.MOD_ID, id), codec);
    }
    public static void init(){}

}