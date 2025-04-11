package com.wishtoday.ts.Component;

import com.wishtoday.ts.Component.Custom.AttackerComponent;
import com.wishtoday.ts.Mytestmod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class RegisterDataComponentTypes {
    public static final ComponentType<AttackerComponent> AttackerUUID = register("attacker_uuid", uuidBuilder -> uuidBuilder.codec(AttackerComponent.CODEC).packetCodec(AttackerComponent.PACKET_CODEC).cache());
    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Mytestmod.MOD_ID,id), builderOperator.apply(ComponentType.builder()).build());
    }
    public static void init(){}
}
