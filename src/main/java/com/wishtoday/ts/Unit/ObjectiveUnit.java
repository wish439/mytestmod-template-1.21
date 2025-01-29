package com.wishtoday.ts.Unit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ObjectiveUnit {
    public static RegistryEntry<Enchantment> getEnchantment(World world, RegistryKey<Enchantment> enchantment){
        return world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(enchantment).orElseThrow();
    }
    public static List<RegistryEntry<Enchantment>> getAllEnchantment(World world){
        List<RegistryEntry<Enchantment>> list = new ArrayList<>();
        list.add(getEnchantment(world, Enchantments.SHARPNESS));
        list.add(getEnchantment(world, Enchantments.AQUA_AFFINITY));
        list.add(getEnchantment(world, Enchantments.BANE_OF_ARTHROPODS));
        list.add(getEnchantment(world, Enchantments.BINDING_CURSE));
        list.add(getEnchantment(world, Enchantments.BREACH));
        list.add(getEnchantment(world, Enchantments.BLAST_PROTECTION));
        list.add(getEnchantment(world, Enchantments.CHANNELING));
        list.add(getEnchantment(world, Enchantments.DENSITY));
        list.add(getEnchantment(world, Enchantments.DEPTH_STRIDER));
        list.add(getEnchantment(world, Enchantments.EFFICIENCY));
        list.add(getEnchantment(world, Enchantments.FEATHER_FALLING));
        list.add(getEnchantment(world, Enchantments.WIND_BURST));
        list.add(getEnchantment(world, Enchantments.WIND_BURST));
        list.add(getEnchantment(world, Enchantments.VANISHING_CURSE));
        list.add(getEnchantment(world, Enchantments.UNBREAKING));
        list.add(getEnchantment(world, Enchantments.THORNS));
        list.add(getEnchantment(world, Enchantments.SWIFT_SNEAK));
        list.add(getEnchantment(world, Enchantments.SWEEPING_EDGE));
        list.add(getEnchantment(world, Enchantments.SOUL_SPEED));
        list.add(getEnchantment(world, Enchantments.SMITE));
        list.add(getEnchantment(world, Enchantments.SILK_TOUCH));
        list.add(getEnchantment(world, Enchantments.RIPTIDE));
        list.add(getEnchantment(world, Enchantments.RESPIRATION));
        list.add(getEnchantment(world, Enchantments.QUICK_CHARGE));
        list.add(getEnchantment(world, Enchantments.PUNCH));
        list.add(getEnchantment(world, Enchantments.PROTECTION));
        list.add(getEnchantment(world, Enchantments.PROJECTILE_PROTECTION));
        list.add(getEnchantment(world, Enchantments.POWER));
        list.add(getEnchantment(world, Enchantments.PIERCING));
        list.add(getEnchantment(world, Enchantments.MULTISHOT));
        list.add(getEnchantment(world, Enchantments.MENDING));
        list.add(getEnchantment(world, Enchantments.LURE));
        list.add(getEnchantment(world, Enchantments.LUCK_OF_THE_SEA));
        list.add(getEnchantment(world, Enchantments.LOYALTY));
        list.add(getEnchantment(world, Enchantments.LOOTING));
        list.add(getEnchantment(world, Enchantments.KNOCKBACK));
        list.add(getEnchantment(world, Enchantments.INFINITY));
        list.add(getEnchantment(world, Enchantments.IMPALING));
        list.add(getEnchantment(world, Enchantments.FROST_WALKER));
        list.add(getEnchantment(world, Enchantments.FORTUNE));
        list.add(getEnchantment(world, Enchantments.FLAME));
        list.add(getEnchantment(world, Enchantments.FIRE_PROTECTION));
        list.add(getEnchantment(world, Enchantments.FIRE_ASPECT));
        return list;
    }
}
