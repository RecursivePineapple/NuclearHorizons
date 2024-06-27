package com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator;

public class SimulationComponentResult {
    public long
        totalAirHeating,
        totalHullHeating,
        totalHullCooling,
        totalTempSecs,
        totalEUOutput;
        
    public Long
        maxAirHeating,
        maxHullCooling;

    public int
        minTemp = Integer.MAX_VALUE, maxTemp,
        replaceCount;
}