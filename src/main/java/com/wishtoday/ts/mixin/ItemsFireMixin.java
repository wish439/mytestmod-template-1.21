package com.wishtoday.ts.mixin;

import com.wishtoday.ts.Item.Custom.FireBallItemMixin;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.item.Items.register;

// 混合Items类
@Mixin(Items.class)
public class ItemsFireMixin {
    // 声明一个final的、可变的、被阴影化的Item类型的FIRE_CHARGE变量
    @Final
    @Shadow
    @Mutable
    public static final Item FIRE_CHARGE = register("fire_charge", new FireBallItemMixin(new Item.Settings()));
}
