package com.wishtoday.ts.Client.KeyBindings;

import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.KeepInventoryPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.TestPayload;
import com.wishtoday.ts.NetWorking.Payload.C2S.Inputs.ToDeadPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class InputKeyBindings {
    public static KeyBinding TestKeyBinding;
    public static KeyBinding KeepInventoryKeyBinding;
    public static KeyBinding ToDeadKeyBinding;
    public static void ListeningEvents(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TestKeyBinding.wasPressed()) {
                ClientPlayNetworking.send(new TestPayload());
            }
            while (KeepInventoryKeyBinding.wasPressed()) {
                ClientPlayNetworking.send(new KeepInventoryPayload());
            }
            while (ToDeadKeyBinding.wasPressed()) {
                ClientPlayNetworking.send(new ToDeadPayload());
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
