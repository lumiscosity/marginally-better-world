package com.lumiscosity.mbworld;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
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
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disableRocks"), "false")) {
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disableBushes"), "false")) {
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disableFallenLogs"), "false")) {
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disableCavePatches"), "false")) {
			feature_sets++;
		}

		if (Objects.equals(MconfGetter.get(config, "disablePoi"), "false")) {
			feature_sets++;
		}

		LOGGER.info(String.format("Marginally Better World initialized with %d feature sets!", feature_sets));
	}
}