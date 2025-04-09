package com.wishtoday.ts.mixin.TNTMixin;

import com.wishtoday.ts.Entity.CanModifyPowerTNTEntity;
import com.wishtoday.ts.GameRule.ModGameRules;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TntBlock.class)
public class TNTBlockMixin {
    @ModifyArg(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)V",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private static Entity create(Entity par1){
        return new CanModifyPowerTNTEntity(par1.getWorld(),par1.getX(),par1.getY(),par1.getZ(), par1.getControllingPassenger(),par1.getWorld().getGameRules().getInt(ModGameRules.TNTEXPLODEPOWER));
    }
    @ModifyArg(method = "onDestroyedByExplosion",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private Entity spawnEntity(Entity par1){
        CanModifyPowerTNTEntity canModifyPowerTNTEntity = new CanModifyPowerTNTEntity(par1.getWorld(), par1.getX(), par1.getY(), par1.getZ(), par1.getControllingPassenger(), par1.getWorld().getGameRules().getInt(ModGameRules.TNTEXPLODEPOWER));
        int i = canModifyPowerTNTEntity.getFuse();
        canModifyPowerTNTEntity.setFuse((short)(par1.getWorld().random.nextInt(i / 4) + i / 8));
        return canModifyPowerTNTEntity;
    }
}
