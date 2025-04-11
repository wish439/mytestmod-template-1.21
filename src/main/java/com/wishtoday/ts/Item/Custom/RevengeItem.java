package com.wishtoday.ts.Item.Custom;

import com.wishtoday.ts.Component.Custom.AttackerComponent;
import com.wishtoday.ts.Component.RegisterDataComponentTypes;
import com.wishtoday.ts.Event.RegisterEvent.PlayerInteractEvents;
import com.wishtoday.ts.Item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.UUID;

public class RevengeItem extends Item {
    public RevengeItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) return super.use(world, user, hand);
        ItemStack stack = user.getStackInHand(hand);
        if (stack.get(RegisterDataComponentTypes.AttackerUUID) == null) return super.use(world, user, hand);
        stack.remove(RegisterDataComponentTypes.AttackerUUID);
        user.setStackInHand(hand, stack);
        return super.use(world, user, hand);
    }

    public static boolean onAttack(PlayerEntity user, MinecraftServer server, Entity target) {
        if (user.getWorld().isClient()) {
            return true;// 仅在服务端执行逻辑
        }
        if (!(target instanceof LivingEntity entity)) return true;
        ItemStack stack = user.getStackInHand(Hand.MAIN_HAND);
        if (!stack.isOf(ModItems.REVENGE)) return true;
        Hand hand = Hand.MAIN_HAND;
        ServerWorld world = user.getServer().getWorld(user.getWorld().getRegistryKey());
        if (stack.get(RegisterDataComponentTypes.AttackerUUID) == null) {
            stack.set(RegisterDataComponentTypes.AttackerUUID, new AttackerComponent(entity.getUuid()));
            user.sendMessage(Text.of("已选中" + entity.getDisplayName().getString() + ",请再选中一个,之后他们会battle"), true);
        } else {
            UUID uuid = stack.get(RegisterDataComponentTypes.AttackerUUID).attacker_uuid();
            Optional.ofNullable(world.getEntity(uuid))
                    .ifPresent(entity1 -> {
                        if (entity1 instanceof LivingEntity livingEntity) {
                            entity.setAttacker(livingEntity);
                            livingEntity.setAttacker(entity);
                        }
                    });
            stack.remove(RegisterDataComponentTypes.AttackerUUID);
        }
        user.setStackInHand(hand, stack);
        return false;
    }
}