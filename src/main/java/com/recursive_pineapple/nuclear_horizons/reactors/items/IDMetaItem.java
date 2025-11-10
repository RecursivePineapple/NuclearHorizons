package com.recursive_pineapple.nuclear_horizons.reactors.items;

import java.util.function.Supplier;

public enum IDMetaItem {

    // Please pretty please, add your entries while conserving the order
    EmptyFuelRod(0, () -> NHItemList.EMPTY_FUEL_ROD_BASIC),
    //
    ;

    public final int ID;
    public final Supplier<NHItemList> container;

    IDMetaItem(int ID, Supplier<NHItemList> container) {
        this.ID = ID;
        this.container = container;
    }
}
