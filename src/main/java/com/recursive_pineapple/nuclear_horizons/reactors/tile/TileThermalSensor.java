package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import java.util.Arrays;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.gtnewhorizons.modularui.api.ModularUITextures;
import com.gtnewhorizons.modularui.api.drawable.AdaptableUITexture;
import com.gtnewhorizons.modularui.api.math.Color;
import com.gtnewhorizons.modularui.api.math.Size;
import com.gtnewhorizons.modularui.api.screen.ITileWithModularUI;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.screen.UIBuildContext;
import com.gtnewhorizons.modularui.common.widget.TextWidget;
import com.gtnewhorizons.modularui.common.widget.VanillaButtonWidget;
import com.gtnewhorizons.modularui.common.widget.textfield.NumericWidget;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;

public class TileThermalSensor extends TileEntity implements ITileWithModularUI, IReactorBlock {

    public int reactorRelX, reactorRelY, reactorRelZ;

    private int threshold;

    private ThermalSensorOp op = ThermalSensorOp.GTE;

    private Boolean wasActive = null;

    private static enum ThermalSensorOp {

        LT,
        LTE,
        GT,
        GTE;

        public String getDisplayString() {
            return switch (this) {
                case LT -> "<";
                case LTE -> "<=";
                case GT -> ">";
                case GTE -> ">=";
            };
        }

        public boolean test(int threshold, int temp) {
            return switch (this) {
                case GT -> temp > threshold;
                case GTE -> temp >= threshold;
                case LT -> temp < threshold;
                case LTE -> temp <= threshold;
            };
        }
    }

    @Override
    public @Nullable TileReactorCore getReactor() {
        // spotless:off
        if (worldObj.getTileEntity(xCoord + reactorRelX, yCoord + reactorRelY, zCoord + reactorRelZ) instanceof TileReactorCore reactor) {
            return reactor;
        } else {
            return null;
        }
        // spotless:on
    }

    @Override
    public void setReactor(TileReactorCore reactor) {
        if (getReactor() != reactor) {
            this.reactorRelX = reactor == null ? 0 : reactor.xCoord - xCoord;
            this.reactorRelY = reactor == null ? 0 : reactor.yCoord - yCoord;
            this.reactorRelZ = reactor == null ? 0 : reactor.zCoord - zCoord;
            this.markDirty();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public void onHeatTick(IReactorGrid reactor) {
        var shouldBeActive = op.test(threshold, reactor.getHullHeat());

        if (wasActive == null || shouldBeActive != wasActive) {
            wasActive = shouldBeActive;
            worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
        }
    }

    public boolean isActive() {
        return wasActive == null ? false : wasActive;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("version", 1);

        compound.setInteger("reactorRelX", this.reactorRelX);
        compound.setInteger("reactorRelY", this.reactorRelY);
        compound.setInteger("reactorRelZ", this.reactorRelZ);

        compound.setInteger("threshold", this.threshold);
        compound.setInteger("reactorRelZ", this.reactorRelZ);
        compound.setString("op", op.name());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        switch (compound.getInteger("version")) {
            case 1: {
                this.reactorRelX = compound.getInteger("reactorRelX");
                this.reactorRelY = compound.getInteger("reactorRelY");
                this.reactorRelZ = compound.getInteger("reactorRelZ");
                this.threshold = compound.getInteger("threshold");
                this.op = ThermalSensorOp.valueOf(compound.getString("op"));
                break;
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        var data = new NBTTagCompound();

        data.setInteger("reactorRelX", reactorRelX);
        data.setInteger("reactorRelY", reactorRelY);
        data.setInteger("reactorRelZ", reactorRelZ);
        data.setInteger("threshold", threshold);
        data.setString("op", op.name());

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, blockMetadata, data);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        var data = pkt.func_148857_g();

        this.reactorRelX = data.getInteger("reactorRelX");
        this.reactorRelY = data.getInteger("reactorRelY");
        this.reactorRelZ = data.getInteger("reactorRelZ");
        this.threshold = data.getInteger("threshold");
        this.op = ThermalSensorOp.valueOf(data.getString("op"));
    }

    private static final AdaptableUITexture DISPLAY = AdaptableUITexture
        .of("modularui:gui/background/display", 143, 75, 2);

    @Override
    public ModularWindow createWindow(UIBuildContext buildContext) {
        ModularWindow.Builder builder = ModularWindow.builder(new Size(144, 48));

        builder.setBackground(ModularUITextures.VANILLA_BACKGROUND);

        builder.widgets(
            new TextWidget("Reactor Thermal Sensor").setPos(8, 8),
            new VanillaButtonWidget().setDisplayString(this.op.getDisplayString())
                .setOnClick((t, u) -> {
                    this.op = ThermalSensorOp.values()[(this.op.ordinal() + 1) % ThermalSensorOp.values().length];
                    ((VanillaButtonWidget) u).setDisplayString(op.getDisplayString());
                    u.notifyTooltipChange();

                    this.markDirty();
                    this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    this.onHeatTick(getReactor());
                })
                .dynamicTooltipShift(() -> {
                    return Arrays.asList(
                        String
                            .format("Emit a redstone signal when reactor temperature is %s the threshold", switch (op) {
                        case LT -> "less than";
                        case LTE -> "less than or equal to";
                        case GT -> "greater than";
                        case GTE -> "greater than or equal to";
                    }));
                })
                .setSize(24, 16)
                .setPos(8, 24),
            new NumericWidget().setGetter(() -> this.threshold)
                .setSetter(v -> {
                    this.threshold = (int) v;

                    this.markDirty();
                    this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    this.onHeatTick(getReactor());
                })
                .setBounds(0, Double.MAX_VALUE)
                .setTextColor(Color.WHITE.dark(1))
                .setBackground(DISPLAY.withOffset(-2, -2, 4, 4))
                .setSize(96, 16)
                .setPos(40, 24));

        return builder.build();
    }
}
