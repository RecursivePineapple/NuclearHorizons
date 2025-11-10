package com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import org.jetbrains.annotations.NotNull;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gtnewhorizons.modularui.api.forge.IItemHandlerModifiable;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorCore;

import io.netty.buffer.ByteBuf;

public class SimulationConfig implements IItemHandlerModifiable {

    // spotless:off

    public boolean
        pulsed = false,
        automated = true,
        fluid = false,
        usingReactorCoolantInjectors;

    public int
        initialHeat = 0,
        onPulse = 0,
        offPulse = 0,
        suspendTemp = 100_000,
        resumeTemp = 100_000,
        maxSimulationTicks = 1_000_000;

    public SimComponentConfig[] components = new SimComponentConfig[TileReactorCore.COL_COUNT * TileReactorCore.ROW_COUNT];

    // spotless:on

    @Nullable
    public transient SimulationResult result;

    public SimulationConfig() {

    }

    public String getCode() {
        BigintStorage storage = new BigintStorage();

        storage.store(maxSimulationTicks, 5000000);
        storage.store(usingReactorCoolantInjectors ? 1 : 0, 1);
        storage.store(fluid ? 1 : 0, 1);

        if (pulsed) {
            storage.store(resumeTemp, 120000);
            storage.store(suspendTemp, 120000);
            storage.store(offPulse, 5000000);
            storage.store(onPulse, 5000000);
        }

        storage.store(initialHeat, 120000);

        final int maxComponentHeat = 1000000000;

        for (int i = components.length - 1; i >= 0; i--) {
            var c = components[i];

            if (c == null) {
                storage.store(0, 72);
                continue;
            }

            Integer componentId = SimulationItems.getSimulationItemId(c.stack);

            if (componentId == null) {
                storage.store(0, 72);
                NuclearHorizons.LOG.warn(
                    "Could not save reactor item " + c.stack
                        + " into config code because it does not have a simulation item ID");
                continue;
            }

            if (componentId > 72) {
                storage.store(0, 72);
                NuclearHorizons.LOG.warn(
                    "Could not save reactor item " + c.stack
                        + " into config code because its simulation item ID was greater than 72");
                continue;
            }

            if (c.hasAutomation) {
                if (automated) {
                    storage.store(c.reactorPause, 10000);
                    storage.store(c.replacementThreshold, maxComponentHeat);
                }

                storage.store(c.initialHeat, maxComponentHeat);
            }

            storage.store(c.hasAutomation ? 1 : 0, 1);
            storage.store(componentId, 72);
        }

        storage.store(automated ? 1 : 0, 1);
        storage.store(pulsed ? 1 : 0, 1);

        storage.store(4, 255);

        return "erp=" + storage.outputBase64();
    }

    public static SimulationConfig fromCode(String code) {
        SimulationConfig config = new SimulationConfig();

        if (code == null || code.isEmpty()) {
            return config;
        }

        if (code.startsWith("erp=")) {
            code = code.substring(4);
        }

        BigintStorage storage = null;
        try {
            storage = BigintStorage.inputBase64(code);
        } catch (Throwable t) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI()
                .printChatMessage(
                    new ChatComponentText(
                        EnumChatFormatting.RED.toString() + EnumChatFormatting.ITALIC.toString()
                            + "Could not load invalid reactor code."));
            NuclearHorizons.LOG.error("Could not parse reactor code into BigInteger", t);
            return new SimulationConfig();
        }

        int revision = storage.extract(255);

        if (revision < 0 || revision > 4) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI()
                .printChatMessage(
                    new ChatComponentText(
                        EnumChatFormatting.RED.toString() + EnumChatFormatting.ITALIC.toString()
                            + "Could not load invalid reactor code."));
            NuclearHorizons.LOG
                .error("Reactor code had incorrect revision (was " + revision + ", should be 4 >= x >= 0)");
            return new SimulationConfig();
        }

        if (revision >= 1) {
            config.pulsed = storage.extract(1) > 0;
            config.automated = storage.extract(1) > 0;
        }

        int maxComponentHeat = switch (revision) {
            case 4 -> 1000000000;
            case 3 -> 1080000;
            default -> 360000;
        };

        for (int row = 0; row < TileReactorCore.ROW_COUNT; row++) {
            for (int col = 0; col < TileReactorCore.COL_COUNT; col++) {
                int componentId = switch (revision) {
                    case 0, 1 -> storage.extract(38);
                    case 2 -> storage.extract(44);
                    case 3 -> storage.extract(58);
                    default -> storage.extract(72);
                };

                if (componentId != 0) {
                    var compConfig = new SimComponentConfig();
                    compConfig.hasAutomation = storage.extract(1) > 0;

                    if (compConfig.hasAutomation) {
                        compConfig.initialHeat = storage.extract(maxComponentHeat);

                        if (revision == 0 || (revision >= 1 && config.automated)) {
                            compConfig.replacementThreshold = storage.extract(maxComponentHeat);
                            compConfig.reactorPause = storage.extract(10000);
                        }
                    }

                    compConfig.stack = SimulationItems.getSimulationItem(componentId);

                    if (compConfig.stack == null) {
                        NuclearHorizons.LOG.warn("Could not find simulation item with component id " + componentId);
                        continue;
                    }

                    config.components[row * TileReactorCore.COL_COUNT + col] = compConfig;
                }
            }
        }

        config.initialHeat = storage.extract(120000);

        if (revision == 0 || (revision >= 1 && config.pulsed)) {
            config.onPulse = storage.extract(5000000);
            config.offPulse = storage.extract(5000000);
            config.suspendTemp = storage.extract(120000);
            config.resumeTemp = storage.extract(120000);
        }

        config.fluid = storage.extract(1) > 0;
        config.usingReactorCoolantInjectors = storage.extract(1) > 0;

        if (revision == 0) {
            config.pulsed = storage.extract(1) > 0;
            config.automated = storage.extract(1) > 0;
        }

        config.maxSimulationTicks = storage.extract(5000000);

        if (!storage.isEmpty()) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI()
                .printChatMessage(
                    new ChatComponentText(
                        EnumChatFormatting.RED.toString() + EnumChatFormatting.ITALIC.toString()
                            + "Could not load invalid reactor code."));
            NuclearHorizons.LOG
                .error("Reactor code had left over data after reading all fields: assuming something has gone wrong.");
            return new SimulationConfig();
        }

        return config;
    }

    public static @Nonnull SimulationConfig read(ByteBuf buffer) {
        byte[] data = new byte[buffer.readInt()];

        if (data.length == 0) {
            return new SimulationConfig();
        }

        buffer.readBytes(data);

        try {
            return SimulationConfig.load(SimulatorProtos.SimulationConfig.parseFrom(data));
        } catch (InvalidProtocolBufferException e) {
            NuclearHorizons.LOG.error("Could not read data for SimulationConfig", e);
            return new SimulationConfig();
        }
    }

    public static void write(ByteBuf buffer, SimulationConfig result) {
        if (result == null) {
            buffer.writeInt(0);
        } else {
            var data = result.save()
                .toByteArray();
            buffer.writeInt(data.length);
            buffer.writeBytes(data);
        }
    }

    public SimulatorProtos.SimulationConfig save() {
        return SimulatorProtos.SimulationConfig.newBuilder()
            .setPulsed(pulsed)
            .setAutomated(automated)
            .setFluid(fluid)
            .setInitialHeat(initialHeat)
            .setOnPulse(onPulse)
            .setOffPulse(offPulse)
            .setSuspendTemp(suspendTemp)
            .setResumeTemp(resumeTemp)
            .setMaxSimulationTicks(maxSimulationTicks)
            .addAllComponents(
                IntStream.range(0, components.length)
                    .mapToObj(i -> {
                        var c = components[i];

                        if (c == null) {
                            return null;
                        }

                        Integer id = SimulationItems.getSimulationItemId(c.stack);

                        if (id == null) {
                            return null;
                        } else {
                            return SimulatorProtos.ComponentConfig.newBuilder()
                                .setIndex(i)
                                .setItem(id)
                                .setHasAutomation(c.hasAutomation)
                                .setInitialHeat(c.initialHeat)
                                .setReplacementThreshold(c.replacementThreshold)
                                .setReactorPause(c.reactorPause)
                                .build();
                        }
                    })
                    .filter(i -> i != null)
                    .collect(Collectors.toList()))
            .build();
    }

    public static SimulationConfig load(SimulatorProtos.SimulationConfig data) {
        SimulationConfig config = new SimulationConfig();

        config.pulsed = data.getPulsed();
        config.automated = data.getAutomated();
        config.fluid = data.getFluid();
        config.initialHeat = data.getInitialHeat();
        config.onPulse = data.getOnPulse();
        config.offPulse = data.getOffPulse();
        config.suspendTemp = data.getSuspendTemp();
        config.resumeTemp = data.getResumeTemp();
        config.maxSimulationTicks = data.getMaxSimulationTicks();

        for (int i = 0; i < data.getComponentsCount(); i++) {
            var c = data.getComponents(i);

            var comp = new SimComponentConfig();

            comp.stack = SimulationItems.getSimulationItem(c.getItem());

            if (comp.stack == null) {
                config.components[c.getIndex()] = null;
                continue;
            }

            comp.hasAutomation = c.getHasAutomation();
            comp.initialHeat = c.getInitialHeat();
            comp.replacementThreshold = c.getReplacementThreshold();
            comp.reactorPause = c.getReactorPause();

            config.components[c.getIndex()] = comp;
        }

        return config;
    }

    public void put(@NotNull SimulationConfig source) {
        this.pulsed = source.pulsed;
        this.automated = source.automated;
        this.fluid = source.fluid;
        this.initialHeat = source.initialHeat;
        this.onPulse = source.onPulse;
        this.offPulse = source.offPulse;
        this.suspendTemp = source.suspendTemp;
        this.resumeTemp = source.resumeTemp;
        this.maxSimulationTicks = source.maxSimulationTicks;

        for (int i = 0; i < source.components.length; i++) {
            this.components[i] = null;

            var c = source.components[i];

            if (c != null) {
                var dest = new SimComponentConfig();

                dest.stack = c.stack;
                dest.hasAutomation = c.hasAutomation;
                dest.initialHeat = c.initialHeat;
                dest.replacementThreshold = c.replacementThreshold;
                dest.reactorPause = c.reactorPause;

                this.components[i] = dest;
            }
        }
    }

    @Override
    @Nullable
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        var item = components[slot];

        if (item == null || amount < 1) {
            return null;
        }

        if (!simulate) {
            components[slot] = null;
        }

        return withResults(item.stack, slot);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }

    @Override
    public int getSlots() {
        return components.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return extractItem(slot, 1, true);
    }

    @Override
    @Nullable
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (components[slot] != null || stack == null || !ComponentRegistry.isReactorItem(stack)) {
            return stack;
        }

        var result = stack.copy();
        var stored = result.splitStack(1);

        if (!simulate) {
            setStackInSlot(slot, stored);
        }

        if (result.stackSize <= 0) {
            return null;
        } else {
            return result;
        }
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        if (stack == null || !ComponentRegistry.isReactorItem(stack)) {
            components[slot] = null;
        } else {
            var config = new SimComponentConfig();
            config.stack = stack.copy();

            components[slot] = config;
        }
    }

    private static void addResultLine(NBTTagList lines, String name, String unit, Object value) {
        var text = I18n.format(
            "nh_tooltip.sim.results." + name,
            unit != null
                ? I18n.format(value == null ? "nh_gui.sim.results.none" : ("nh_gui.sim.results." + unit), value)
                : value);

        for (var line : text.split("\\\\n")) {
            lines.appendTag(
                new NBTTagString(EnumChatFormatting.RESET.toString() + EnumChatFormatting.BLUE.toString() + line));
        }
    }

    private ItemStack withResults(ItemStack itemStack, int slot) {
        boolean hasResults = false;
        String format = EnumChatFormatting.RESET.toString() + EnumChatFormatting.BLUE.toString();

        var res = result;

        if (res != null) {
            var r = res.componentResults[slot];

            if (r != null) {
                var lore = new NBTTagList();

                lore.appendTag(new NBTTagString(""));

                lore.appendTag(new NBTTagString(format + I18n.format("nh_tooltip.results.title")));

                if (r.totalTempSecs > 0) {
                    addResultLine(lore, "avg_temp", "hu_total", r.totalTempSecs / res.simTime);
                    addResultLine(lore, "min_temp", "hu_total", r.minTemp);
                    addResultLine(lore, "max_temp", "hu_total", r.maxTemp);
                }

                if (r.totalHullCooling > 0) {
                    addResultLine(lore, "avg_hull_cooling", "hu_per_sec", r.totalHullCooling / res.simTime);
                }

                if (r.totalHullHeating > 0) {
                    addResultLine(lore, "avg_hull_heating", "hu_per_sec", r.totalHullHeating / res.simTime);
                    addResultLine(lore, "heating_per_item", "hu_total", r.totalHullHeating / (r.replaceCount + 1));
                }

                if (r.totalAirHeating != 0) {
                    addResultLine(lore, "avg_heat_output", "hu_total", r.totalAirHeating / res.simTime);
                }

                addResultLine(lore, "replace_count", null, r.replaceCount);

                if (r.totalEUOutput > 0) {
                    addResultLine(lore, "avg_power", "eu_per_tick", r.totalEUOutput / 20 / res.simTime);
                    addResultLine(lore, "power_per_item", "eu_total", r.totalEUOutput / (r.replaceCount + 1));
                }

                lore.appendTag(new NBTTagString(""));

                var display = new NBTTagCompound();
                display.setTag("Lore", lore);
                itemStack.setTagInfo("display", display);
                hasResults = true;
            }
        }

        if (!hasResults) {
            var lore = new NBTTagList();

            lore.appendTag(new NBTTagString(""));
            lore.appendTag(new NBTTagString(format + "No simulation results available."));
            lore.appendTag(new NBTTagString(""));

            var display = new NBTTagCompound();
            display.setTag("Lore", lore);
            itemStack.setTagInfo("display", display);
        }

        return itemStack;
    }

    public static class SimComponentConfig {

        public ItemStack stack;
        public boolean hasAutomation;
        public int initialHeat;
        public int replacementThreshold;
        public int reactorPause;
    }
}
