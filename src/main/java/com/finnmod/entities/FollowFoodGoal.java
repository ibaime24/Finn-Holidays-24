package com.finnmod.entities;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class FollowFoodGoal extends Goal {

    private final CustomDog dog;

    public FollowFoodGoal(CustomDog dog) {
        this.dog = dog;
    }

    @Override
    public boolean canUse() {
        return this.dog.getOwner() instanceof Player && isPlayerHoldingFood((Player) this.dog.getOwner());
    }

    public boolean isPlayerHoldingFood(Player player) {
        return player.getMainHandItem().getItem().getFoodProperties() != null;
    }
}
