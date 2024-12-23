package com.finnmod.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CustomDog extends TamableAnimal {

    public CustomDog(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob partner) {
        return new CustomDog(this.getType(), serverLevel);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem().getFoodProperties() != null;
    }
}
