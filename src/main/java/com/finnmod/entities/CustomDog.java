package com.finnmod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public class CustomDog extends TamableAnimal {

    // Constructor matching the required signature
    public CustomDog(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    // Additional methods and AI goal registrations can be added here
}
