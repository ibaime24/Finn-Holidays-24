package com.finnmod.entities;

import net.minecraft.world.entity.Mob; // Base class for mobs
import net.minecraft.world.entity.ai.goal.Goal; // Base class for AI goals
import net.minecraft.world.entity.player.Player; // Represents a player in the game
import net.minecraft.world.phys.AABB; // Axis-Aligned Bounding Box for range checks

import java.util.EnumSet;

public class FollowFoodGoal extends Goal {
    private final Mob mob; // The mob executing this goal
    private final double speedModifier; // Speed at which the mob moves
    private final double followRange; // Detection range for players with food
    private Player targetPlayer; // The player the mob will follow

    public FollowFoodGoal(Mob mob, double speedModifier, double followRange) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.followRange = followRange;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE)); // This goal involves movement
    }

    @Override
    public boolean canUse() {
        // Create an area around the mob for detecting players
        AABB detectionArea = this.mob.getBoundingBox().inflate(this.followRange);
        this.targetPlayer = this.mob.level.getNearestPlayer(
            player -> player.isAlive() && isPlayerHoldingFood(player), // Filter for players holding food
            this.mob
        );
        // Return true if a valid player is found in the detection area
        return this.targetPlayer != null && detectionArea.contains(this.targetPlayer.position());
    }

    @Override
    public void start() {
        // Start moving toward the target player
        this.mob.getNavigation().moveTo(this.targetPlayer, this.speedModifier);
    }

    @Override
    public boolean canContinueToUse() {
        // Check if the goal can continue running
        return this.targetPlayer != null
            && this.targetPlayer.isAlive() // Player must still be alive
            && this.mob.distanceToSqr(this.targetPlayer) <= this.followRange * this.followRange // Player must still be within range
            && isPlayerHoldingFood(this.targetPlayer); // Player must still be holding food
    }

    @Override
    public void stop() {
        // Stop the goal and reset the target player
        this.targetPlayer = null;
        this.mob.getNavigation().stop();
    }

    private boolean isPlayerHoldingFood(Player player) {
        // Check if the player is holding an edible item
        return player.getMainHandItem().isEdible();
    }
}