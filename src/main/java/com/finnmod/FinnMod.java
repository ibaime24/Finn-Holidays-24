package com.finnmod;

import com.finnmod.entities.CustomDog;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod("finn_mod")
public class FinnMod {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, "finn_mod");

    public static final RegistryObject<EntityType<CustomDog>> CUSTOM_DOG = ENTITIES.register("custom_dog",
        () -> EntityType.Builder.of(CustomDog::new, MobCategory.CREATURE)
            .sized(0.6F, 0.85F)
            .build(new ResourceLocation("finn_mod", "custom_dog").toString())
    );

    public FinnMod() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
