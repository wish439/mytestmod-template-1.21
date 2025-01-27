package com.wishtoday.ts.Unit;

import net.minecraft.nbt.NbtCompound;

public class NBTUnit {
    public static NbtCompound RemoveDeathNbt(NbtCompound nbt){
        nbt.putInt("DeathTime",0);
        nbt.putInt("ForcedAge",0);
        nbt.putInt("HurtByTimestamp",0);
        nbt.putInt("HurtTime",0);
        return nbt;
    }
}
