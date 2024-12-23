package com.finnmod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CustomDog extends TamableAnimal {

    public CustomDog(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected boolean isFood(ItemStack stack) {
        // Define which items are food for the CustomDog
        return stack.getItem().isEdible();
    }
}
