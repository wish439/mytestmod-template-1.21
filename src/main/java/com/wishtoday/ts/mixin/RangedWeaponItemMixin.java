package com.wishtoday.ts.mixin;

import com.wishtoday.ts.Entity.ArrowEntityMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RangedWeaponItem.class)
public class RangedWeaponItemMixin {
    @ModifyVariable(method = "createArrowEntity",at = @At(value = "STORE",ordinal = 0))
    private PersistentProjectileEntity createArrowEntity(PersistentProjectileEntity value, World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack) {
        return new ArrowEntityMixin(world,shooter,projectileStack.copyWithCount(1),weaponStack);
    }
}
