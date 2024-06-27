package com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nullable;

import com.google.protobuf.InvalidProtocolBufferException;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorCore;

import io.netty.buffer.ByteBuf;

public class SimulationResult {
    public SimulationConfig config;

    public long
        start = System.nanoTime(),
        end = System.nanoTime(),
        totalEU,
        totalHU,
        totalTempSecs,
        totalHullHeating,
        totalHullCooling;

    public Long
        maxHullCooling;

    public int
        minEUpT = Integer.MAX_VALUE, maxEUpT,
        minHUpS = Integer.MAX_VALUE, maxHUpS,
        minTemp = Integer.MAX_VALUE, maxTemp,
        simTime, activeTime, pausedTime;
    
    public Integer
        timeToNormal,
        timeToBurn,
        timeToEvaporate,
        timeToHurt,
        timeToLava,
        timeToExplode;

    public SimulationComponentResult[] componentResults = new SimulationComponentResult[TileReactorCore.COL_COUNT * TileReactorCore.ROW_COUNT];

    public static @Nullable SimulationResult read(ByteBuf buffer) {
        byte[] data = new byte[buffer.readInt()];

        if(data.length == 0) {
            return null;
        }

        buffer.readBytes(data);

        try {
            return SimulationResult.load(SimulatorProtos.SimulationResult.parseFrom(data));
        } catch (InvalidProtocolBufferException e) {
            NuclearHorizons.LOG.error("Could not read data for SimulationResult", e);
            return null;
        }
    }

    public static void write(ByteBuf buffer, SimulationResult result) {
        if(result == null) {
            buffer.writeInt(0);
        } else {
            var data = result.save().toByteArray();
            buffer.writeInt(data.length);
            buffer.writeBytes(data);
        }
    }

    public SimulatorProtos.SimulationResult save() {
        var builder = SimulatorProtos.SimulationResult.newBuilder()
            .setStart(start)
            .setEnd(end)
            .setTotalEU(totalEU)
            .setMinEUpT(minEUpT)
            .setMaxEUpT(maxEUpT)
            .setTotalHU(totalHU)
            .setMinHUpS(minHUpS)
            .setMaxHUpS(maxHUpS)
            .setTotalTempSecs(totalTempSecs)
            .setMinTemp(minTemp)
            .setMaxTemp(maxTemp)
            .setTotalHullHeating(totalHullHeating)
            .setTotalHullCooling(totalHullCooling)
            .setSimTime(simTime)
            .setActiveTime(activeTime)
            .setPausedTime(pausedTime)
            .addAllComponents(
                IntStream.range(0, componentResults.length)
                    .mapToObj(i -> {
                        var c = componentResults[i];

                        if(c == null) {
                            return null;
                        } else {
                            return SimulatorProtos.ComponentResult.newBuilder()
                                .setIndex(i)
                                .setTotalAirHeating(c.totalAirHeating)
                                .setTotalHullHeating(c.totalHullHeating)
                                .setTotalHullCooling(c.totalHullCooling)
                                .setTotalTempSecs(c.totalTempSecs)
                                .setTotalEUOutput(c.totalEUOutput)
                                .setMinTemp(c.minTemp)
                                .setMaxTemp(c.maxTemp)
                                .setReplaceCount(c.replaceCount)
                                .build();
                        }
                    })
                    .filter(i -> i != null)
                    .collect(Collectors.toList())
            );

        if(timeToNormal != null) builder.setTimeToNormal(timeToNormal);
        if(timeToBurn != null) builder.setTimeToBurn(timeToBurn);
        if(timeToEvaporate != null) builder.setTimeToEvaporate(timeToEvaporate);
        if(timeToHurt != null) builder.setTimeToHurt(timeToHurt);
        if(timeToLava != null) builder.setTimeToLava(timeToLava);
        if(timeToExplode != null) builder.setTimeToExplode(timeToExplode);

        return builder.build();
    }

    public static SimulationResult load(SimulatorProtos.SimulationResult data) {
        SimulationResult result = new SimulationResult();

        result.start = data.getStart();
        result.end = data.getEnd();
        result.totalEU = data.getTotalEU();
        result.minEUpT = data.getMinEUpT();
        result.maxEUpT = data.getMaxEUpT();
        result.totalHU = data.getTotalHU();
        result.minHUpS = data.getMinHUpS();
        result.maxHUpS = data.getMaxHUpS();
        result.totalTempSecs = data.getTotalTempSecs();
        result.minTemp = data.getMinTemp();
        result.maxTemp = data.getMaxTemp();
        result.totalHullHeating = data.getTotalHullHeating();
        result.totalHullCooling = data.getTotalHullCooling();
        result.simTime = data.getSimTime();
        result.activeTime = data.getActiveTime();
        result.pausedTime = data.getPausedTime();

        for(int i = 0; i < data.getComponentsCount(); i++) {
            var c = data.getComponents(i);

            var comp = new SimulationComponentResult();

            comp.totalAirHeating = c.getTotalAirHeating();
            comp.totalHullHeating = c.getTotalHullHeating();
            comp.totalHullCooling = c.getTotalHullCooling();
            comp.totalTempSecs = c.getTotalTempSecs();
            comp.totalEUOutput = c.getTotalEUOutput();
            comp.minTemp = c.getMinTemp();
            comp.maxTemp = c.getMaxTemp();
            comp.replaceCount = c.getReplaceCount();

            result.componentResults[c.getIndex()] = comp;
        }

        if(data.hasTimeToNormal()) result.timeToNormal = data.getTimeToNormal();
        if(data.hasTimeToBurn()) result.timeToBurn = data.getTimeToBurn();
        if(data.hasTimeToEvaporate()) result.timeToEvaporate = data.getTimeToEvaporate();
        if(data.hasTimeToHurt()) result.timeToHurt = data.getTimeToHurt();
        if(data.hasTimeToLava()) result.timeToLava = data.getTimeToLava();
        if(data.hasTimeToExplode()) result.timeToExplode = data.getTimeToExplode();

        return result;
    }

    public double duration() {
        return (end - start) / 1e9d;
    }
}
