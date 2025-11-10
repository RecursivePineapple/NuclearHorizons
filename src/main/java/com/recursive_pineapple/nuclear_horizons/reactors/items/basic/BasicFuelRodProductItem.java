package com.recursive_pineapple.nuclear_horizons.reactors.items.basic;

import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.items.NHItem;

public class BasicFuelRodProductItem extends NHItem {

    public BasicFuelRodProductItem(String name, String textureName) {
        super(name);
        setTextureName(NuclearHorizons.MODID + ":" + textureName);
    }
}
