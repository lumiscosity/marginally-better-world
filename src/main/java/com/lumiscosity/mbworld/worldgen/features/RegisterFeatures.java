package com.lumiscosity.mbworld.worldgen.features;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.minecraft.world.gen.feature.Feature;

public class RegisterFeatures {

    public static void register_features() {

    }

    public static void register(Feature feature, String id) {
        Version version = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion();
    }

}
