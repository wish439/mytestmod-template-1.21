package com.wishtoday.ts.Unit.Objs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Objects;

public class PlayerAndStatePos {
    private PlayerEntity player;
    private List<WorldStatePos> statePos;
    private BlockPos targetPos;
    public PlayerAndStatePos(PlayerEntity player, List<WorldStatePos> statePos) {
        this.player = player;
        this.statePos = statePos;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public List<WorldStatePos> getStatePos() {
        return this.statePos;
    }
    public void setTargetPos(BlockPos targetPos) {
        this.targetPos = targetPos;
    }
    public BlockPos getTargetPos() {
        return this.targetPos;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerAndStatePos that = (PlayerAndStatePos) o;
        return Objects.equals(player, that.player)
                && Objects.equals(statePos, that.statePos)
                && Objects.equals(targetPos, that.targetPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, statePos, targetPos);
    }

    @Override
    public String toString() {
        return "PlayerAndStatePos{" +
                "player=" + player +
                ", statePos=" + statePos +
                ", targetPos=" + targetPos +
                '}';
    }

    public void place() {
        statePos.forEach(WorldStatePos::place);
    }
    public void targetPosSet(BlockState state) {
        player.getWorld().setBlockState(this.targetPos, state);
    }
    public boolean targetBlockIsOf(Block block) {
        return player.getWorld().getBlockState(targetPos).isOf(block);
    }
}
