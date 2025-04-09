package com.wishtoday.ts.mixin.TNTMixin;

import com.wishtoday.ts.Entity.CanModifyPowerTNTEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.TntEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.*;

@Mixin(EntityType.class)
public class EntityTypeMixin {
    @Final
    @Mutable
    @Shadow
    public static final EntityType<TntEntity> TNT = register(
            "tnt",
            EntityType.Builder.<TntEntity>create(CanModifyPowerTNTEntity::new, SpawnGroup.MISC)
                    .makeFireImmune()
                    .dimensions(0.98F, 0.98F)
                    .eyeHeight(0.15F)
                    .maxTrackingRange(10)
                    .trackingTickInterval(10)
    );
    @Unique
    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, id, type.build(id));
    }
}
