package com.recursive_pineapple.nuclear_horizons;

import cpw.mods.fml.relauncher.Side;

public class ServerProxy extends CommonProxy {
    
    @Override
    public Side getSide() {
        return Side.SERVER;
    }
    
}
