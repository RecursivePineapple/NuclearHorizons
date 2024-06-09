package com.recursive_pineapple.nuclear_horizons;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int ROD_EU_MULTIPLIER = 1;
    public static double MOX_EU_COEFFICIENT = 4;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
