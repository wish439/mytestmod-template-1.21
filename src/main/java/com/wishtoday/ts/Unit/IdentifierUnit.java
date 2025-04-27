package com.wishtoday.ts.Unit;

import com.wishtoday.ts.Mytestmod;
import net.minecraft.util.Identifier;

public class IdentifierUnit {
    public static Identifier getIdentifier(String id) {
        return Identifier.of(Mytestmod.MOD_ID, id);
    }

    public static String getModString(String string, boolean hasSemicolon) {
        return hasSemicolon ? Mytestmod.MOD_ID + ":" + string : Mytestmod.MOD_ID + string;
    }
    public static String getModString(String string) {
        return getModString(string,true);
    }
}
