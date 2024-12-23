package com.finnmod.entities;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;

public class FollowFoodGoal extends Goal {
    private final Mob mob;
    private final double speedModifier;
    private final double followRange;
    private Player targetPlayer;

    public FollowFoodGoal(Mob mob, double speedModifier, double followRange) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.followRange = followRange;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        TargetingConditions conditions = TargetingConditions.forNonCombat().range(this.followRange)
            .selector(this::isPlayerHoldingFood);

        this.targetPlayer = this.mob.level().getNearestPlayer(conditions, this.mob);
        return this.targetPlayer != null;
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.targetPlayer, this.speedModifier);
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetPlayer != null
            && this.targetPlayer.isAlive()
            && this.mob.distanceToSqr(this.targetPlayer) <= this.followRange * this.followRange
            && isPlayerHoldingFood(this.targetPlayer);
    }

    @Override
    public void stop() {
        this.targetPlayer = null;
        this.mob.getNavigation().stop();
    }

    private boolean isPlayerHoldingFood(Player player) {
        return player.getMainHandItem().getItem().isEdible();
    }
}
