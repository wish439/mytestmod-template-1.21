package com.wishtoday.ts.mixin.Entity;

import com.wishtoday.ts.Mytestmod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireballEntity.class)
public class FireballEntityMixin {
    @Inject(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/hit/EntityHitResult;getEntity()Lnet/minecraft/entity/Entity;", shift = At.Shift.BEFORE), cancellable = true)
    public void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        this.onCollision(entityHitResult,ci);
        ci.cancel();
    }

    @Inject(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z", shift = At.Shift.AFTER), cancellable = true)
    public void onCollision(HitResult hitResult, CallbackInfo ci) {
        Vec3d pos = hitResult.getPos();
        Box box = Box.from(pos).expand(3, 3, 3);
        FireballEntity fireballEntity = (FireballEntity) (Object) this;
        World world = fireballEntity.getWorld();
        List<Entity> otherEntities = world.getOtherEntities(fireballEntity, box, entity -> entity instanceof LivingEntity);
        //Mytestmod.LOGGER.info(otherEntities.toString());
        otherEntities.forEach(entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                /*double v = Math.sqrt(entity.squaredDistanceTo(fireballEntity));
                double w = entity.getX() - fireballEntity.getX();
                double x = entity.getEyeY() - fireballEntity.getY();
                double y = entity.getZ() - fireballEntity.getZ();
                double z = Math.sqrt(w * w + x * x + y * y);
                if (z != 0.0){
                    w /= z;
                    x /= z;
                    y /= z;
                    double aa = (1.0 - v);
                    double ab = aa * (1.0 - livingEntity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                    w *= ab;
                    x *= ab;
                    y *= ab;
                    Vec3d vec3d2 = new Vec3d(w, x, y);
                    livingEntity.setVelocity(livingEntity.getVelocity().add(-vec3d2.x, vec3d2.y, -vec3d2.z));*/
                double x = (fireballEntity.getX() - livingEntity.getX()) * 1.5;
                double z = (fireballEntity.getZ() - livingEntity.getZ()) * 1.5;
                livingEntity.takeKnockback(1, x, z);
                if (livingEntity instanceof PlayerEntity player) {
                    ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                    serverPlayer.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entity));
                }

            }
        });
        fireballEntity.discard();
        //ci.cancel();
    }
}
