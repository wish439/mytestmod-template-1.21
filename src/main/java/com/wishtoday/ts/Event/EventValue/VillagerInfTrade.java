package com.wishtoday.ts.Event.EventValue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.world.World;

public class VillagerInfTrade {
    public static ActionResult ResetVillagerTrade(PlayerEntity player, Entity target, Hand hand, World world, EntityHitResult hitResult) {
        if (world.isClient()){
            return ActionResult.PASS;
        }
        if (target instanceof VillagerEntity villager) {
            TradeOfferList offers = villager.getOffers();
            if (offers.isEmpty()) {
                return ActionResult.SUCCESS;
            }else {
                for (TradeOffer offer : offers) {
                    offer.resetUses();
                }
            }
        }
        return ActionResult.PASS;
    }
}
