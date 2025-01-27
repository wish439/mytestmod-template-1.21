package com.wishtoday.ts;

import com.wishtoday.ts.Event.EventRegisterUnit;
import com.wishtoday.ts.FunctionClass.DeathEntitySummonBaby;
import com.wishtoday.ts.Test.BlocksList;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
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
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        ModItems.init();
        DeathEntitySummonBaby.execute();
        new EventRegisterUnit();

        LOGGER.info("Hello Fabric world!");
    }
}