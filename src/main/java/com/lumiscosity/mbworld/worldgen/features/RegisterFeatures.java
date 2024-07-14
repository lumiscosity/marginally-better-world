package com.lumiscosity.mbworld.worldgen.features;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import static com.lumiscosity.mbworld.MarginallyBetterWorld.MOD_ID;

public class RegisterFeatures {
    // the only reason as to why this mod isn't a single binary from 1.17 onwards...
    // i continue to dream of a unified multiloader multiversion stable api library

    public static final Feature<FallenLogFeatureConfig> FALLEN_LOG = register_feature(
            "fallen_log", new FallenLogFeature(FallenLogFeatureConfig.CODEC)
    );

    private static <C extends FeatureConfig, F extends Feature<C>> F register_feature(String name, F feature) {
        return Registry.register(Registries.FEATURE, Identifier.of(MOD_ID, name), feature);
    }

}
