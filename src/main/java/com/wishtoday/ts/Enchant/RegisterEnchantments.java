package com.wishtoday.ts.Enchant;

import com.wishtoday.ts.Enchant.Custom.TestEnchantmentEffect;
import com.wishtoday.ts.Mytestmod;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class RegisterEnchantments {
    public static final RegistryKey<Enchantment> TESTENCHANT = of("testenchant");
    public static void bootstrap(Registerable<Enchantment> registry){
        RegistryEntryLookup<Enchantment> registryEntryLookup2 = registry.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<Item> registryEntryLookup3 = registry.getRegistryLookup(RegistryKeys.ITEM);
        register(
                registry,
                TESTENCHANT,
                Enchantment.builder(
                                Enchantment.definition(
                                        registryEntryLookup3.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                        10,
                                        255,
                                        Enchantment.leveledCost(1, 11),
                                        Enchantment.leveledCost(12, 11),
                                        1,
                                        AttributeModifierSlot.MAINHAND
                                )
                        )
                        .exclusiveSet(registryEntryLookup2.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                        .addEffect(
                                EnchantmentEffectComponentTypes.POST_ATTACK,
                                EnchantmentEffectTarget.ATTACKER,
                                EnchantmentEffectTarget.VICTIM,
                                new TestEnchantmentEffect()
                        )
        );
    }
    public static void register(){}
    private static RegistryKey<Enchantment> of(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Mytestmod.MOD_ID, id));
    }
    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
