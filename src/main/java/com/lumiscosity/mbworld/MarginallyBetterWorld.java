package com.lumiscosity.mbworld;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class MarginallyBetterWorld implements ModInitializer {
	public static final String MOD_ID = "mbworld";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void init_config(File config) {
		try {
			FileWriter writer = new FileWriter(config);
			writer.write("disablePatches=false\n");
			writer.write("disableRocks=false\n");
			writer.write("disableBushes=false\n");
			writer.write("disableFallenLogs=false\n");
			writer.write("disableCavePatches=false\n");
			writer.write("disablePoi=false\n");
			writer.close();
		} catch (IOException e) {
			LOGGER.warn("Marginally Better World could not write the config file!");
		}
	}

	@Override
	public void onInitialize() {
		int feature_sets = 0;
		File config = FabricLoader.getInstance().getConfigDir().resolve("mbworld.mconf").toFile();

		if (!config.exists()) {
			init_config(config);
		}

		if (Objects.equals(MconfGetter.get(config, "disablePatches"), "false")) {
			// Patches of gravel, ice, coarse dirt etc.
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "patches/gravel"))),
					GenerationStep.Feature.LOCAL_MODIFICATIONS,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "patches/gravel"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "patches/coarse_dirt"))),
					GenerationStep.Feature.LOCAL_MODIFICATIONS,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "patches/coarse_dirt"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "patches/ice"))),
					GenerationStep.Feature.LOCAL_MODIFICATIONS,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "patches/ice"))
			);
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disableRocks"), "false")) {
			// Rocks
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "rocks/stone"))),
					GenerationStep.Feature.LOCAL_MODIFICATIONS,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "rocks/stone"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "rocks/andesite"))),
					GenerationStep.Feature.LOCAL_MODIFICATIONS,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "rocks/andesite"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "rocks/diorite"))),
					GenerationStep.Feature.LOCAL_MODIFICATIONS,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "rocks/diorite"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "rocks/sandstone"))),
					GenerationStep.Feature.LOCAL_MODIFICATIONS,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "rocks/sandstone"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "rocks/red_sandstone"))),
					GenerationStep.Feature.LOCAL_MODIFICATIONS,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "rocks/red_sandstone"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "rocks/ice"))),
					GenerationStep.Feature.LOCAL_MODIFICATIONS,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "rocks/ice"))
			);
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disableBushes"), "false")) {
			// Bushes
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "bushes/oak_bush"))),
					GenerationStep.Feature.VEGETAL_DECORATION,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "bushes/oak_bush"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "bushes/acacia_bush"))),
					GenerationStep.Feature.VEGETAL_DECORATION,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "bushes/acacia_bush"))
			);
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disableFallenLogs"), "false")) {
			// Fallen logs
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "fallen_logs/oak"))),
					GenerationStep.Feature.VEGETAL_DECORATION,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "fallen_logs/oak"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "fallen_logs/spruce"))),
					GenerationStep.Feature.VEGETAL_DECORATION,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "fallen_logs/spruce"))
			);
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disableCavePatches"), "false")) {
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "cave_patches/sandstone"))),
					GenerationStep.Feature.VEGETAL_DECORATION,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "cave_patches/sandstone"))
			);
			BiomeModifications.addFeature(
					BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "cave_patches/ice"))),
					GenerationStep.Feature.VEGETAL_DECORATION,
					RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "cave_patches/ice"))
			);
			if (FabricLoader.getInstance().isModLoaded("surface")) {
				BiomeModifications.addFeature(
						BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "compat/cave_patches/limestone"))),
						GenerationStep.Feature.VEGETAL_DECORATION,
						RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MOD_ID, "compat/cave_patches/limestone"))
				);
			}
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disablePoi"), "false")) {
			// Various features like terraces, fallen pillars etc.
			feature_sets++;
		}

		LOGGER.info(String.format("Marginally Better World initialized with %d feature sets!", feature_sets));
	}
}