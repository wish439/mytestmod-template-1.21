package com.wishtoday.ts.mixin.HelpEvent;

import com.wishtoday.ts.Event.RegisterEvent.TimeEvents;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class TimeEventsMixin {
    @Inject(method = "tick",at = @At("HEAD"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        MinecraftServer server = (MinecraftServer) (Object) this;
        long time = server.getOverworld().getTime();
        if (time % 100 == 0 && time != 0) {
            TimeEvents.EVENT.invoker().interact(server);
        }
    }
}
