package com.wishtoday.ts;

import com.wishtoday.ts.Test.BlockBuild;
import com.wishtoday.ts.Test.BlockPosXZ;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.joml.Vector3d;

public class Death {
    public static boolean TryUseTotemMod(DamageSource source, PlayerEntity player){
        if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        } else
        {
            ItemStack itemStack = null;
            PlayerInventory Inventory = player.getInventory();
            for (int i = 0;i < Inventory.size(); ++i) {
                ItemStack itemStack2 = (Inventory.getStack(i));
                if (itemStack2.isOf(Items.TOTEM_OF_UNDYING)) {
                    itemStack = itemStack2.copy();
                    itemStack2.decrement(1);
                    break;
                }
            }

            if (itemStack != null) {
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
                    Criteria.USED_TOTEM.trigger(serverPlayerEntity, itemStack);
                    player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
                }
                player.setHealth(1.0F);
                player.clearStatusEffects();
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 800, 2));
                if (player.getHungerManager().getFoodLevel() <= 6) {
                    player.getHungerManager().setFoodLevel(20);
                }
                player.getWorld().sendEntityStatus(player, EntityStatuses.USE_TOTEM_OF_UNDYING);
            }

            return itemStack != null;
        }
    }
}
