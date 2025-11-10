package com.recursive_pineapple.nuclear_horizons.reactors.fluids;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizons.modularui.api.drawable.UITexture;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.blocks.BlockList;

import gregtech.api.enums.GTValues;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapBackend;
import gregtech.api.recipe.RecipeMapBuilder;
import gregtech.api.recipe.RecipeMetadataKey;
import gregtech.api.registries.LHECoolantRegistry;
import gregtech.api.util.GTRecipe;
import gregtech.nei.RecipeDisplayInfo;

public class CoolantRegistry {

    public static final UITexture REACTOR_PROGRESS = UITexture.fullImage(NuclearHorizons.MODID, "gui/reactor_progress");

    public static class CoolantSpecificHeat extends RecipeMetadataKey<Integer> {

        public static final CoolantSpecificHeat INSTANCE = new CoolantSpecificHeat();

        private CoolantSpecificHeat() {
            super(Integer.class, "coolant_specific_heat");
        }

        @Override
        public void drawInfo(RecipeDisplayInfo recipeInfo, @Nullable Object value) {
            int specific_heat = cast(value, 1);

            recipeInfo.drawText("Specific Heat: " + specific_heat + "HU/L");
        }
    }

    public static final RecipeMap<RecipeMapBackend> reactorCoolant = RecipeMapBuilder.of("nh.recipe.coolant")
        .maxIO(0, 0, 1, 1)
        .progressBar(REACTOR_PROGRESS)
        .neiHandlerInfo(builder -> builder.setDisplayStack(new ItemStack(BlockList.REACTOR_CHAMBER)))
        .neiRecipeComparator(
            Comparator
                .<GTRecipe, Integer>comparing(recipe -> recipe.getMetadataOrDefault(CoolantSpecificHeat.INSTANCE, 1))
                .thenComparing(GTRecipe::compareTo))
        .build();

    private static final HashMap<Fluid, Coolant> coolantsByColdFluid = new HashMap<>();
    private static final HashMap<Fluid, Coolant> coolantsByHotFluid = new HashMap<>();
    private static final HashMap<Fluid, Coolant> coolantsByFluid = new HashMap<>();

    private CoolantRegistry() {

    }

    /**
     * Registers a coolant that can be used to cool a nuclear reactor
     *
     * @param cold                 The cold input coolant
     * @param hot                  The heated output coolant
     * @param specificHeatCapacity The amount of HU that can be stored in one mB of coolant
     */
    public static void registerCoolant(FluidStack cold, FluidStack hot, int specificHeatCapacity, int outputTankMult,
        boolean registerLHE) {
        Objects.requireNonNull(cold);
        Objects.requireNonNull(hot);
        if (specificHeatCapacity <= 0) throw new IllegalArgumentException("specificHeatCapacity");

        Coolant coolant = new Coolant(cold, hot, specificHeatCapacity, outputTankMult);

        coolantsByColdFluid.put(cold.getFluid(), coolant);
        coolantsByHotFluid.put(hot.getFluid(), coolant);
        coolantsByFluid.put(cold.getFluid(), coolant);
        coolantsByFluid.put(hot.getFluid(), coolant);

        GTValues.RA.stdBuilder()
            .fluidInputs(cold)
            .fluidOutputs(hot)
            .metadata(CoolantSpecificHeat.INSTANCE, specificHeatCapacity)
            .duration(0)
            .eut(0)
            .addTo(reactorCoolant);

        if (registerLHE) {
            double baseMultiplier = 0.5;
            double baseThreshold = 0.2;

            LHECoolantRegistry.registerCoolant(
                cold.getFluid()
                    .getName(),
                hot.getFluid()
                    .getName(),
                baseMultiplier * specificHeatCapacity,
                baseThreshold / specificHeatCapacity);
        }
    }

    public static boolean isColdCoolant(Fluid fluid) {
        return coolantsByColdFluid.containsKey(fluid);
    }

    public static boolean isHotCoolant(Fluid fluid) {
        return coolantsByHotFluid.containsKey(fluid);
    }

    public static Coolant getCoolantInfo(Fluid fluid) {
        return coolantsByFluid.get(fluid);
    }

    public static class Coolant {

        public final FluidStack cold, hot;
        public final int specificHeatCapacity;
        public final int outputTankMult;

        public Coolant(FluidStack cold, FluidStack hot, int specificHeatCapacity, int outputTankMult) {
            this.cold = cold;
            this.hot = hot;
            this.specificHeatCapacity = specificHeatCapacity;
            this.outputTankMult = outputTankMult;
        }
    }
}
