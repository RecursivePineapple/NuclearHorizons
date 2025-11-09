package com.recursive_pineapple.nuclear_horizons.recipes;

import static gregtech.api.util.GTUtility.getIntegratedCircuit;

import net.minecraft.item.ItemStack;

import com.recursive_pineapple.nuclear_horizons.reactors.items.NHItemList;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;

public class MiscRecipes {

    public static void registerRecipes() {
        registerCraftingIngredientRecipes();
    }

    private static void registerCraftingIngredientRecipes() {
        GTValues.RA.stdBuilder()
            .itemInputs(GTOreDictUnificator.get(OrePrefixes.itemCasing, Materials.Iron, 2), getIntegratedCircuit(1))
            .itemOutputs(new ItemStack(NHItemList.))
    }
}
