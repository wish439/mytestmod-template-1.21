package com.wishtoday.ts.Client.KeyBindings;

import com.wishtoday.ts.Mytestmod;
import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.KeepInventoryPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.ToDeadPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.WalkDamage.DamagePayload;
import com.wishtoday.ts.Unit.InventoryUnit;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class InputKeyBindings {
    private static int time = 0;
    public static KeyBinding TestKeyBinding;
    public static KeyBinding KeepInventoryKeyBinding;
    public static KeyBinding ToDeadKeyBinding;
    public static void ListeningEvents(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TestKeyBinding.wasPressed()) {
                //ClientPlayNetworking.send(new TestPayload());
                MinecraftClient mc = MinecraftClient.getInstance();
                ClientPlayerEntity clientPlayerEntity = mc.player;
                InventoryUnit.swapSlots(clientPlayerEntity,40,10);
                //clientPlayerEntity.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(11));
            }
            while (KeepInventoryKeyBinding.wasPressed()) {
                ClientPlayNetworking.send(new KeepInventoryPayload());
            }
            while (ToDeadKeyBinding.wasPressed()) {
                ClientPlayNetworking.send(new ToDeadPayload());
            }
            GameOptions options = client.options;
            while (options.forwardKey.wasPressed() || (options.forwardKey.isPressed() && ++time % 20 == 0)) {
                time = 0;
                //ClientPlayNetworking.send(new DamagePayload());
                //Mytestmod.LOGGER.info("已发包");
            }
        });
    }
    public static void register() {
        TestKeyBinding = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "client.keybindings.test",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_O,
                        "category.keybindings.test"
                )
        );
        KeepInventoryKeyBinding = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("client.keybindings.keep",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_G,
                        "category.keybindings.keep"
                        )
        );
        ToDeadKeyBinding = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "client.keybindings.todead",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_X,
                        "category.keybindings.todead"
                )
        );
    }
}
