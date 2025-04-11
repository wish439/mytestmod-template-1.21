package com.wishtoday.ts;

import com.wishtoday.ts.Block.Register.BlockEntityTypeRegister;
import com.wishtoday.ts.Block.Register.BlockRegister;
import com.wishtoday.ts.Client.KeyBindings.InputKeyBindings;
import com.wishtoday.ts.Command.CommandValue.ModifyToBedrockCommand;
import com.wishtoday.ts.Component.RegisterDataComponentTypes;
import com.wishtoday.ts.Effects.RegisterStatusEffect;
import com.wishtoday.ts.Enchant.RegisterEnchantmentEffects;
import com.wishtoday.ts.Enchant.RegisterEnchantments;
import com.wishtoday.ts.Event.EventRegisterUnit;
import com.wishtoday.ts.Item.ModItems;
import com.wishtoday.ts.NetWorking.Receive.RegisterReceive;
import com.wishtoday.ts.Tags.ModItemTags;
import com.wishtoday.ts.Unit.BlockUnit;
import com.wishtoday.ts.GameRule.ModGameRules;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mytestmod implements ModInitializer {
    public static final String MOD_ID = "mytestmod";
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        InputKeyBindings.register();
        ModItemTags.init();
        RegisterDataComponentTypes.init();
        RegisterStatusEffect.init();
        RegisterEnchantmentEffects.init();
        RegisterEnchantments.register();
        BlockRegister.init();
        BlockEntityTypeRegister.init();
        ModGameRules.init();
        BlockUnit.getAllHasBlockItems();
        stats.initStats();
        ModItems.init();
        new EventRegisterUnit();
        RegisterReceive.register();
        InputKeyBindings.ListeningEvents();

        //LOGGER.info("Hello Fabric world!");
    }
}