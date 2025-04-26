package com.wishtoday.ts.mixin.Server.Command;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.command.EnchantCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantCommand.class)
public class EnchantMixin {
    //修改enchantment2.getMaxLevel()的值为int的最大值
    @ModifyExpressionValue(method = "execute",at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private static int onExecute(int original) {
        return Integer.MAX_VALUE;
    }
}
