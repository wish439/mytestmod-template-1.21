package com.wishtoday.ts.Test;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;

public class BlocksList {
    public static List<Block> blocks (){
        List<Block> blocks = new ArrayList<Block>();
        for (Block block : Registries.BLOCK) {
            blocks.add(block);
        }
        return blocks;
    }
}
