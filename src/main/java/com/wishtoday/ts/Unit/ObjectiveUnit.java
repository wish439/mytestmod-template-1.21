package com.wishtoday.ts.Unit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectiveUnit {
    private ObjectiveUnit() {
    }
    public static Map<String,RegistryEntry<Enchantment>> getEnchantmentsMap(World world) {
        Map<String,RegistryEntry<Enchantment>> enchantments = new HashMap<>();
        enchantments.put("sharpness",getEnchantment(world, Enchantments.SHARPNESS));
        enchantments.put("protection",getEnchantment(world, Enchantments.PROTECTION));
        enchantments.put("fire_protection",getEnchantment(world, Enchantments.FIRE_PROTECTION));
        enchantments.put("feather_falling",getEnchantment(world, Enchantments.FEATHER_FALLING));
        enchantments.put("blast_protection",getEnchantment(world, Enchantments.BLAST_PROTECTION));
        enchantments.put("projectile_protection",getEnchantment(world, Enchantments.PROJECTILE_PROTECTION));
        enchantments.put("respiration",getEnchantment(world, Enchantments.RESPIRATION));
        enchantments.put("aqua_affinity",getEnchantment(world, Enchantments.AQUA_AFFINITY));
        enchantments.put("thorns",getEnchantment(world, Enchantments.THORNS));
        enchantments.put("depth_strider",getEnchantment(world, Enchantments.DEPTH_STRIDER));
        enchantments.put("frost_walker",getEnchantment(world, Enchantments.FROST_WALKER));
        enchantments.put("binding_curse",getEnchantment(world, Enchantments.BINDING_CURSE));
        enchantments.put("soul_speed",getEnchantment(world, Enchantments.SOUL_SPEED));
        enchantments.put("swift_sneak",getEnchantment(world, Enchantments.SWIFT_SNEAK));
        enchantments.put("smite",getEnchantment(world, Enchantments.SMITE));
        enchantments.put("bane_of_arthropods",getEnchantment(world, Enchantments.BANE_OF_ARTHROPODS));
        enchantments.put("knockback",getEnchantment(world, Enchantments.KNOCKBACK));
        enchantments.put("fire_aspect",getEnchantment(world, Enchantments.FIRE_ASPECT));
        enchantments.put("looting",getEnchantment(world, Enchantments.LOOTING));
        enchantments.put("sweeping_edge",getEnchantment(world, Enchantments.SWEEPING_EDGE));
        enchantments.put("efficiency",getEnchantment(world, Enchantments.EFFICIENCY));
        enchantments.put("silk_touch",getEnchantment(world, Enchantments.SILK_TOUCH));
        enchantments.put("unbreaking",getEnchantment(world, Enchantments.UNBREAKING));
        enchantments.put("fortune",getEnchantment(world, Enchantments.FORTUNE));
        enchantments.put("power",getEnchantment(world, Enchantments.POWER));
        enchantments.put("punch",getEnchantment(world, Enchantments.PUNCH));
        enchantments.put("flame",getEnchantment(world, Enchantments.FLAME));
        enchantments.put("infinity",getEnchantment(world, Enchantments.INFINITY));
        enchantments.put("luck_of_the_sea",getEnchantment(world, Enchantments.LUCK_OF_THE_SEA));
        enchantments.put("lure",getEnchantment(world, Enchantments.LURE));
        enchantments.put("loyalty",getEnchantment(world, Enchantments.LOYALTY));
        enchantments.put("impaling",getEnchantment(world, Enchantments.IMPALING));
        enchantments.put("riptide",getEnchantment(world, Enchantments.RIPTIDE));
        enchantments.put("channeling",getEnchantment(world, Enchantments.CHANNELING));
        enchantments.put("multishot",getEnchantment(world, Enchantments.MULTISHOT));
        enchantments.put("quick_charge",getEnchantment(world, Enchantments.QUICK_CHARGE));
        enchantments.put("piercing",getEnchantment(world, Enchantments.PIERCING));
        enchantments.put("density",getEnchantment(world, Enchantments.DENSITY));
        enchantments.put("breach",getEnchantment(world, Enchantments.BREACH));
        enchantments.put("wind_burst",getEnchantment(world, Enchantments.WIND_BURST));
        enchantments.put("mending",getEnchantment(world, Enchantments.MENDING));
        enchantments.put("vanishing_curse",getEnchantment(world, Enchantments.VANISHING_CURSE));
        return enchantments;
    }
    public static List<RegistryEntry<Enchantment>> getEnchantmentsList(World world) {
        List<RegistryEntry<Enchantment>> enchantments = new ArrayList<>();
        enchantments.add(getEnchantment(world, Enchantments.SHARPNESS));
        enchantments.add(getEnchantment(world, Enchantments.PROTECTION));
        enchantments.add(getEnchantment(world, Enchantments.FIRE_PROTECTION));
        enchantments.add(getEnchantment(world, Enchantments.FEATHER_FALLING));
        enchantments.add(getEnchantment(world, Enchantments.BLAST_PROTECTION));
        enchantments.add(getEnchantment(world, Enchantments.PROJECTILE_PROTECTION));
        enchantments.add(getEnchantment(world, Enchantments.RESPIRATION));
        enchantments.add(getEnchantment(world, Enchantments.AQUA_AFFINITY));
        enchantments.add(getEnchantment(world, Enchantments.THORNS));
        enchantments.add(getEnchantment(world, Enchantments.DEPTH_STRIDER));
        enchantments.add(getEnchantment(world, Enchantments.FROST_WALKER));
        enchantments.add(getEnchantment(world, Enchantments.BINDING_CURSE));
        enchantments.add(getEnchantment(world, Enchantments.SOUL_SPEED));
        enchantments.add(getEnchantment(world, Enchantments.SWIFT_SNEAK));
        enchantments.add(getEnchantment(world, Enchantments.SMITE));
        enchantments.add(getEnchantment(world, Enchantments.BANE_OF_ARTHROPODS));
        enchantments.add(getEnchantment(world, Enchantments.KNOCKBACK));
        enchantments.add(getEnchantment(world, Enchantments.FIRE_ASPECT));
        enchantments.add(getEnchantment(world, Enchantments.LOOTING));
        enchantments.add(getEnchantment(world, Enchantments.SWEEPING_EDGE));
        enchantments.add(getEnchantment(world, Enchantments.EFFICIENCY));
        enchantments.add(getEnchantment(world, Enchantments.SILK_TOUCH));
        enchantments.add(getEnchantment(world, Enchantments.UNBREAKING));
        enchantments.add(getEnchantment(world, Enchantments.FORTUNE));
        enchantments.add(getEnchantment(world, Enchantments.POWER));
        enchantments.add(getEnchantment(world, Enchantments.PUNCH));
        enchantments.add(getEnchantment(world, Enchantments.FLAME));
        enchantments.add(getEnchantment(world, Enchantments.INFINITY));
        enchantments.add(getEnchantment(world, Enchantments.LUCK_OF_THE_SEA));
        enchantments.add(getEnchantment(world, Enchantments.LURE));
        enchantments.add(getEnchantment(world, Enchantments.LOYALTY));
        enchantments.add(getEnchantment(world, Enchantments.IMPALING));
        enchantments.add(getEnchantment(world, Enchantments.RIPTIDE));
        enchantments.add(getEnchantment(world, Enchantments.CHANNELING));
        enchantments.add(getEnchantment(world, Enchantments.MULTISHOT));
        enchantments.add(getEnchantment(world, Enchantments.QUICK_CHARGE));
        enchantments.add(getEnchantment(world, Enchantments.PIERCING));
        enchantments.add(getEnchantment(world, Enchantments.DENSITY));
        enchantments.add(getEnchantment(world, Enchantments.BREACH));
        enchantments.add(getEnchantment(world, Enchantments.WIND_BURST));
        enchantments.add(getEnchantment(world, Enchantments.MENDING));
        enchantments.add(getEnchantment(world, Enchantments.VANISHING_CURSE));
        return enchantments;
    }
    public static RegistryEntry<Enchantment> getEnchantment(World world, RegistryKey<Enchantment> enchantmentRegistryKey) {
       return world.getRegistryManager().
               get(RegistryKeys.ENCHANTMENT).
        getEntry(enchantmentRegistryKey).orElseThrow();
    }
    public static RegistryEntry<Enchantment> getEnchantment(World world, String enchantmentName) {
        Map<String, RegistryEntry<Enchantment>> map = getEnchantmentsMap(world);
        if (!map.containsKey(enchantmentName)) throw new RuntimeException("This is ObjectiveUnit/getEnchantmentByName throw,主要因为调用此方法传送的名无法在列表中找到");
        else return map.get(enchantmentName);
    }
}
