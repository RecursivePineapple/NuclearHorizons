package com.recursive_pineapple.nuclear_horizons.reactors.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.recursive_pineapple.nuclear_horizons.reactors.blocks.BlockList;

public class FluidList {

    public static final String COOLANT_NAME = "nh_coolant";
    public static final String HOT_COOLANT_NAME = "nh_hot_coolant";
    public static final String HOT_SUPER_COOLANT_NAME = "nh_hot_super_coolant";

    public static Fluid COOLANT;
    public static Fluid HOT_COOLANT;
    public static Fluid HOT_SUPER_COOLANT;

    public static void registerFluids() {
        COOLANT = new Fluid(COOLANT_NAME) {

            public net.minecraft.util.IIcon getStillIcon() {
                return BlockList.COOLANT_BLOCK.stillIcon;
            };

            public net.minecraft.util.IIcon getFlowingIcon() {
                return BlockList.COOLANT_BLOCK.flowingIcon;
            };
        };

        HOT_COOLANT = new Fluid(HOT_COOLANT_NAME) {

            public net.minecraft.util.IIcon getStillIcon() {
                return BlockList.HOT_COOLANT_BLOCK.stillIcon;
            };

            public net.minecraft.util.IIcon getFlowingIcon() {
                return BlockList.HOT_COOLANT_BLOCK.flowingIcon;
            };
        };

        // Temporary, will probably be removed
        HOT_SUPER_COOLANT = new Fluid(HOT_SUPER_COOLANT_NAME) {

            public net.minecraft.util.IIcon getStillIcon() {
                return BlockList.COOLANT_BLOCK.stillIcon;
            };

            public net.minecraft.util.IIcon getFlowingIcon() {
                return BlockList.COOLANT_BLOCK.flowingIcon;
            };
        };

        FluidRegistry.registerFluid(COOLANT);

        HOT_COOLANT.setTemperature(273 + 500);
        FluidRegistry.registerFluid(HOT_COOLANT);

        HOT_SUPER_COOLANT.setTemperature(273 + 1000);
        FluidRegistry.registerFluid(HOT_SUPER_COOLANT);
    }

    public static void registerCoolants() {
        CoolantRegistry.registerCoolant(COOLANT, HOT_COOLANT, 1);

        // CoolantRegistry.registerCoolant(Materials.Lead.mStandardMoltenFluid, HOT_SUPER_COOLANT, 10);
    }
}
