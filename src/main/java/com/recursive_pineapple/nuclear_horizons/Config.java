package com.recursive_pineapple.nuclear_horizons;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static double ROD_EU_MULTIPLIER = 1;
    public static double ROD_HU_MULTIPLIER = 1;
    public static double MOX_EU_COEFFICIENT = 4;
    public static int REACTOR_EU_MULTIPLIER = 100;
    public static int FLUID_NUKE_HU_MULTIPLIER = 2;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
