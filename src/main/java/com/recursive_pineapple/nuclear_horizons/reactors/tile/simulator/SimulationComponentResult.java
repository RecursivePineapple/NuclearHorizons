package com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator;

public class SimulationComponentResult {
    // spotless:off
    
    public long
        totalAirHeating,
        totalHullHeating,
        totalHullCooling,
        totalTempSecs,
        totalEUOutput;
        
    public Long
        maxAirHeating,
        maxAirHeatingCapacity,
        maxHullCooling,
        maxHullCoolingCapacity;

    public int
        minTemp = Integer.MAX_VALUE,
        maxTemp = Integer.MIN_VALUE,
        replaceCount;

    public CustomResult[] customResults;

    // spotless:on

    public static class CustomResult {
        public String i18n;
        
        // MUTUALLY EXCLUSIVE!
        public Double double_value;
        public Long long_value;
        public String string_value;

        public void add(double d) {
            if(double_value == null) double_value = 0d;

            double_value += d;
        }

        public void add(long l) {
            if(long_value == null) long_value = 0l;

            long_value += l;
        }
    }
}
