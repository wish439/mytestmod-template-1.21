package com.wishtoday.ts.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class WorldDatage extends FabricDynamicRegistryProvider {
    public WorldDatage(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        //RegistryEntryLookup<Enchantment> registryEntryLookup2 = wrapperLookup.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        //RegistryEntryLookup<Item> registryEntryLookup3 = registry.getRegistryLookup(RegistryKeys.ITEM);
        /*register(
                entries,
                RegisterEnchantments.TESTENCHANT,
                Enchantment.builder(
                                Enchantment.definition(
                                        registries.getWrapperOrThrow(RegistryKeys.ITEM).getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                        10,
                                        4,
                                        Enchantment.leveledCost(1, 11),
                                        Enchantment.leveledCost(12, 11),
                                        1,
                                        AttributeModifierSlot.MAINHAND
                                )
                        )
                        //.exclusiveSet(registryEntryLookup2.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                        .addEffect(
                                EnchantmentEffectComponentTypes.POST_ATTACK,
                                EnchantmentEffectTarget.ATTACKER,
                                EnchantmentEffectTarget.VICTIM,
                                new TestEnchantmentEffect()
                        )
        );*/
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT));
    }
    private void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }

    @Override
    public String getName() {
        return "WorldDatage";
    }
}
