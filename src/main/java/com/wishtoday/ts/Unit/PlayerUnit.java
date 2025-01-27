package com.wishtoday.ts.Unit;

import com.google.common.base.Function;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class PlayerUnit {
    public static PlayerEntity getPlayerFormWorld(World world){
        return world.getPlayers().getFirst();
    }
    public static void TitleActionbarForPlayer(ServerPlayerEntity player, String text){
        Function<Text, OverlayMessageS2CPacket> actionbar = OverlayMessageS2CPacket::new;
        player.networkHandler.sendPacket(actionbar.apply(Text.of(text)));
    }
}
