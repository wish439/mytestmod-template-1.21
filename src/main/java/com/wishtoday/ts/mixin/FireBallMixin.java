package com.wishtoday.ts.mixin;

import net.minecraft.item.FireChargeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireChargeItem.class)
public class FireBallMixin {
    //@Inject(method = "useOnBlock",at = @At(value = "HEAD"),cancellable = true)
    //private void stopFunction(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir){
    //    cir.setReturnValue(ActionResult.SUCCESS);
    //}
}
