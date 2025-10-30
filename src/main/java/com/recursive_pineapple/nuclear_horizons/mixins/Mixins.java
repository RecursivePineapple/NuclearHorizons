package com.recursive_pineapple.nuclear_horizons.mixins;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import com.recursive_pineapple.nuclear_horizons.utils.Mods;

public enum Mixins implements IMixins {

    IC2_REACTOR_IFACE_INJECTION(
        new MixinBuilder("Implements interfaces on IC2 reactors")
            .addCommonMixins("ic2.MixinReactor", "ic2.MixinReactorChamber")
            .addRequiredMod(Mods.IndustrialCraft2)
            .setPhase(Phase.LATE))

    ;

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Override
    public @NotNull MixinBuilder getBuilder() {
        return builder;
    }
}
