package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.annotation.Nullable;

import com.gtnewhorizons.modularui.api.ModularUITextures;
import com.gtnewhorizons.modularui.api.drawable.AdaptableUITexture;
import com.gtnewhorizons.modularui.api.forge.InvWrapper;
import com.gtnewhorizons.modularui.api.math.CrossAxisAlignment;
import com.gtnewhorizons.modularui.api.math.MainAxisAlignment;
import com.gtnewhorizons.modularui.api.math.Size;
import com.gtnewhorizons.modularui.api.screen.ITileWithModularUI;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.screen.UIBuildContext;
import com.gtnewhorizons.modularui.api.widget.Widget;
import com.gtnewhorizons.modularui.common.widget.Column;
import com.gtnewhorizons.modularui.common.widget.FakeSyncWidget;
import com.gtnewhorizons.modularui.common.widget.FluidSlotWidget;
import com.gtnewhorizons.modularui.common.widget.MultiChildWidget;
import com.gtnewhorizons.modularui.common.widget.Row;
import com.gtnewhorizons.modularui.common.widget.SlotGroup;
import com.gtnewhorizons.modularui.common.widget.TextWidget;
import com.recursive_pineapple.nuclear_horizons.reactors.blocks.BlockList;
import com.recursive_pineapple.nuclear_horizons.reactors.components.ComponentRegistry;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.fluids.FluidList;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.IReactorBlock.ReactorEnableState;
import com.recursive_pineapple.nuclear_horizons.utils.DirectionUtil;

import cofh.api.energy.IEnergyReceiver;
import gregtech.api.GregTech_API;
import gregtech.api.interfaces.tileentity.IEnergyConnected;
import gregtech.api.logic.PowerLogic;
import gregtech.api.logic.interfaces.PowerLogicHost;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileReactorCore extends TileEntity implements IInventory, IReactorGrid, ITileWithModularUI, IEnergyConnected {
    
    private static final int ROW_COUNT = 6;
    private static final int COL_COUNT = 9;

    private static final int REACTOR_TICK_SPEED = 20;
    private static final int REACTOR_STRUCTURE_CHECK_PERIOD = 10 * 20;

    private final ArrayList<EntityPlayer> viewingPlayers = new ArrayList<>();

    private int chambers = 0;
    private ItemStack[] contents = new ItemStack[ROW_COUNT * COL_COUNT];
    private IComponentAdapter[] components = new IComponentAdapter[ROW_COUNT * COL_COUNT];

    private int tickCounter = 0;

    boolean isActive = false;
    boolean isFluid = false;

    int storedHeat = 0;
    int addedHeat = 0;

    int voltage = 0;
    int maxStoredEU = 4_194_304; // 2 ^ 22
    int storedEU = 0;
    int addedEU = 0;

    int storedCoolant = 0;
    int maxCoolant = 10_000;
    
    int storedHotCoolant = 0;
    int maxHotCoolant = 10_000;

    private ArrayList<IReactorBlock> reactorBlocks = new ArrayList<>();

    public TileReactorCore() {
        
    }

    //#region Fluid Tanks

    IFluidTank coolantTank = new IFluidTank() {
        @Override
        public FluidStack getFluid() {
            return new FluidStack(FluidList.COOLANT, storedCoolant);
        }

        @Override
        public int getFluidAmount() {
            return storedCoolant;
        }

        @Override
        public int getCapacity() {
            return maxCoolant;
        }

        @Override
        public FluidTankInfo getInfo() {
            return new FluidTankInfo(getFluid(), getCapacity());
        }

        @Override
        public int fill(FluidStack resource, boolean doFill) {
            if(resource.getFluid() == FluidList.COOLANT) {
                int remaining = maxCoolant - storedCoolant;
    
                int consumed = Math.min(remaining, resource.amount);
    
                if(doFill) {
                    storedCoolant += consumed;
                }
    
                return consumed;
            } else {
                return 0;
            }
        }

        @Override
        public FluidStack drain(int maxDrain, boolean doDrain) {
            int consumed = Math.min(storedCoolant, maxDrain);
    
            if(doDrain) {
                storedCoolant -= consumed;
            }
    
            return new FluidStack(FluidList.COOLANT, consumed);
        }
    };

    IFluidTank hotCoolantTank = new IFluidTank() {
        @Override
        public FluidStack getFluid() {
            return new FluidStack(FluidList.HOT_COOLANT, storedHotCoolant);
        }

        @Override
        public int getFluidAmount() {
            return storedHotCoolant;
        }

        @Override
        public int getCapacity() {
            return maxHotCoolant;
        }

        @Override
        public FluidTankInfo getInfo() {
            return new FluidTankInfo(getFluid(), getCapacity());
        }

        @Override
        public int fill(FluidStack resource, boolean doFill) {
            if(resource.getFluid() == FluidList.HOT_COOLANT) {
                int remaining = getCapacity() - getFluidAmount();
    
                int consumed = Math.min(remaining, resource.amount);
    
                if(doFill) {
                    storedHotCoolant += consumed;
                }
    
                return consumed;
            } else {
                return 0;
            }
        }

        @Override
        public FluidStack drain(int maxDrain, boolean doDrain) {
            int consumed = Math.min(getFluidAmount(), maxDrain);
    
            if(doDrain) {
                storedHotCoolant -= consumed;
            }
    
            return new FluidStack(FluidList.HOT_COOLANT, consumed);
        }
    };

    //#endregion

    //#region UI

    private static Widget padding(int width, int height) {
        return new TextWidget().setSize(width, height);
    }

    private static final AdaptableUITexture MISSING_CHAMBER =
        AdaptableUITexture.of("nuclear_horizons:textures/gui/reactor_missing_chamber.png", 18, 108, 0);

    @Override
    public ModularWindow createWindow(UIBuildContext buildContext) {
        ModularWindow.Builder builder = ModularWindow.builder(new Size(212, 234));
        
        builder.setBackground(ModularUITextures.VANILLA_BACKGROUND).bindPlayerInventory(buildContext.getPlayer());

        builder.widgets(
            new Column()
                .setAlignment(CrossAxisAlignment.CENTER)
                .widgets(
                    padding(7, 7),
                    new TextWidget("Nuclear Reactor").setSize(150, 16),
                    new Row()
                        .setAlignment(MainAxisAlignment.CENTER, CrossAxisAlignment.CENTER)
                        .widgets(
                            new FluidSlotWidget(coolantTank).setEnabled(w -> isFluid),
                            padding(2, 0),
                            SlotGroup.ofItemHandler(new InvWrapper(this), getColumnCount()).build(),
                            new Row()
                                .setAlignment(MainAxisAlignment.START, CrossAxisAlignment.CENTER)
                                .consume(w -> {
                                    for(int i = getColumnCount(); i < COL_COUNT; i++) {
                                        ((Row)w).widget(MISSING_CHAMBER.withFixedSize(18, 108).asWidget().setSize(18, 108));
                                    }
                                }),
                            padding(2, 0),
                            new FluidSlotWidget(hotCoolantTank).setEnabled(w -> isFluid)
                        ),
                    new MultiChildWidget()
                        .addChild(
                            new Row()
                                .setAlignment(MainAxisAlignment.START)
                                .widgets(
                                    padding(7 + 16, 16),
                                    new TextWidget("Inventory").setSize(50, 16)
                                )
                        )
                        .addChild(
                            new Row()
                                .setAlignment(MainAxisAlignment.END)
                                .widgets(
                                    TextWidget.dynamicString(() -> String.format("HU: %,d", this.storedHeat))
                                        .setSize(50, 16),
                                    TextWidget.dynamicString(() -> String.format("EU/t: %,d", this.addedEU / 20))
                                        .setSizeProvider((s, w, p) -> isFluid ? new Size(0, 0) : new Size(50, 16))
                                        .setEnabled(w -> !isFluid),
                                    TextWidget.dynamicString(() -> String.format("HU/s: %,d", this.addedHeat))
                                        .setSizeProvider((s, w, p) -> !isFluid ? new Size(0, 0) : new Size(50, 16))
                                        .setEnabled(w -> isFluid),
                                    padding(7 + 16, 16)
                                )
                        )
                )
        );

        builder.widgets(
            new FakeSyncWidget.BooleanSyncer(() -> this.isActive, v -> this.isActive = v),
            new FakeSyncWidget.IntegerSyncer(() -> this.storedHeat, v -> this.storedHeat = v),
            new FakeSyncWidget.IntegerSyncer(() -> this.addedEU, v -> this.addedEU = v),
            new FakeSyncWidget.BooleanSyncer(() -> this.isFluid, v -> this.isFluid = v),
            new FakeSyncWidget.IntegerSyncer(() -> this.storedCoolant, v -> this.storedCoolant = v),
            new FakeSyncWidget.IntegerSyncer(() -> this.storedHotCoolant, v -> this.storedHotCoolant = v),
            new FakeSyncWidget.IntegerSyncer(() -> this.maxCoolant, v -> this.maxCoolant = v),
            new FakeSyncWidget.IntegerSyncer(() -> this.maxHotCoolant, v -> this.maxHotCoolant = v)
        );

        return builder.build();
    }

    //#endregion

    //#region Tile Entity Logic

    public int getColumnCount() {
        return chambers + 3;
    }

    public void setChamberCount(int chambers) {
        if(chambers == this.chambers) {
            return;
        }

        this.chambers = chambers;

        for(var player : viewingPlayers.toArray(new EntityPlayer[viewingPlayers.size()])) {
            player.closeScreen();
        }

        viewingPlayers.clear();

        for(int row = 0; row < ROW_COUNT; row++) {
            for(int col = 0; col < COL_COUNT; col++) {
                if(col >= this.getColumnCount()) {
                    var item = contents[row * COL_COUNT + col];
                    contents[row * COL_COUNT + col] = null;
                    if(item != null && !worldObj.isRemote) {
                        worldObj.spawnEntityInWorld(new EntityItem(worldObj, this.xCoord, this.yCoord, this.zCoord, item));
                    }
                }
            }
        }
    }

    public void dropInventory() {
        for(int row = 0; row < ROW_COUNT; row++) {
            for(int col = 0; col < COL_COUNT; col++) {
                var item = contents[row * COL_COUNT + col];
                contents[row * COL_COUNT + col] = null;
                if(item != null && !worldObj.isRemote) {
                    worldObj.spawnEntityInWorld(new EntityItem(worldObj, this.xCoord, this.yCoord, this.zCoord, item));
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("version", 1);

        compound.setInteger("heat", this.storedHeat);
        compound.setInteger("storedEU", this.storedEU);
        compound.setInteger("tickCooldown", this.tickCounter);
        compound.setBoolean("isActive", this.isActive);

        compound.setBoolean("isFluid", this.isFluid);
        compound.setInteger("storedCoolant", this.storedCoolant);
        compound.setInteger("storedHotCoolant", this.storedHotCoolant);
        compound.setInteger("maxCoolant", this.maxCoolant);
        compound.setInteger("maxHotCoolant", this.maxHotCoolant);

        var grid = new NBTTagCompound();

        for(int i = 0; i < contents.length; i++) {
            var item = contents[i];
            if(item != null) {
                grid.setTag(Integer.toString(i), item.writeToNBT(new NBTTagCompound()));
            }
        }

        compound.setTag("grid", grid);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        int version = compound.getInteger("version");

        switch(version) {
            case 1: {
                this.storedHeat = compound.getInteger("heat");
                this.storedEU = compound.getInteger("storedEU");
                this.tickCounter = compound.getInteger("tickCooldown");
                this.isActive = compound.getBoolean("isActive");

                this.isFluid = compound.getBoolean("isFluid");
                this.storedCoolant = compound.getInteger("storedCoolant");
                this.storedHotCoolant = compound.getInteger("storedHotCoolant");
                this.maxCoolant = compound.getInteger("maxCoolant");
                this.maxHotCoolant = compound.getInteger("maxHotCoolant");
        
                var grid = compound.getCompoundTag("grid");

                Arrays.fill(this.contents, null);
                Arrays.fill(this.components, null);

                for(int i = 0; i < contents.length; i++) {
                    if(grid.hasKey(Integer.toString(i))) {
                        contents[i] = new ItemStack(Blocks.air);
                        contents[i].readFromNBT(grid.getCompoundTag(Integer.toString(i)));

                        if(contents[i].getItem() == null) {
                            contents[i] = null;
                        }
                    }
                }

                break;
            }
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        this.setChamberCount(getAttachedChambers(worldObj, xCoord, yCoord, zCoord));

        if(!this.worldObj.isRemote) {
            this.tickCounter++;

            if(this.tickCounter % REACTOR_TICK_SPEED == 0) {
                this.isActive = worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0;
    
                for(var dir : DirectionUtil.values()) {
                    this.isActive |= worldObj.getBlockPowerInput(
                        dir.offsetX + xCoord,
                        dir.offsetY + yCoord,
                        dir.offsetZ + zCoord
                    ) > 0;
                }

                if(this.isFluid) {
                    boolean anyActive = false, anyInhibiting = false;

                    for(var block : reactorBlocks) {
                        var state = block.getEnableState();
                        anyActive |= state == ReactorEnableState.Active;
                        anyInhibiting |= state == ReactorEnableState.Inhibiting;
                    }

                    isActive |= anyActive;

                    if(anyInhibiting) {
                        isActive = false;
                    }
                }

                if((this.tickCounter / REACTOR_TICK_SPEED) % 2 == 0) {
                    this.doHeatTick();
                } else {
                    this.doEUTick();
                }

                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }

            if(this.tickCounter % REACTOR_STRUCTURE_CHECK_PERIOD == 0) {
                doStructureCheck();
            }

            this.emitEnergy();
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        var data = new NBTTagCompound();

        data.setInteger("chambers", chambers);
        data.setBoolean("isActive", isActive);
        data.setInteger("storedEU", storedEU);
        data.setInteger("storedHeat", storedHeat);
        data.setInteger("addedHeat", addedHeat);
        data.setInteger("addedEU", addedEU);
        data.setBoolean("isFluid", isFluid);
        data.setInteger("storedCoolant", storedCoolant);
        data.setInteger("storedHotCoolant", storedHotCoolant);
        data.setInteger("maxCoolant", maxCoolant);
        data.setInteger("maxHotCoolant", maxHotCoolant);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, blockMetadata, data);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        var data = pkt.func_148857_g();

        this.chambers = data.getInteger("chambers");
        this.isActive = data.getBoolean("isActive");
        this.storedEU = data.getInteger("storedEU");
        this.storedHeat = data.getInteger("storedHeat");
        this.addedHeat = data.getInteger("addedHeat");
        this.addedEU = data.getInteger("addedEU");
        this.isFluid = data.getBoolean("isFluid");
        this.storedCoolant = data.getInteger("storedCoolant");
        this.storedHotCoolant = data.getInteger("storedHotCoolant");
        this.maxCoolant = data.getInteger("maxCoolant");
        this.maxHotCoolant = data.getInteger("maxHotCoolant");
    }

    public static int getAttachedChambers(World worldIn, int x, int y, int z) {
        int chamberCount = 0;

        for(var d : DirectionUtil.values()) {
            if(d.getBlock(worldIn, x, y, z) == BlockList.REACTOR_CHAMBER) {
                chamberCount++;
            }
        }

        return chamberCount;
    }

    public void addViewingPlayer(EntityPlayer player) {
        this.viewingPlayers.add(player);
    }

    public void removeViewingPlayer(EntityPlayer player) {
        this.viewingPlayers.remove(player);
    }

    //#endregion

    //#region Energy Logic

    @Override
    public byte getColorization() {
        return -1;
    }

    @Override
    public byte setColorization(byte arg0) {
        return -1;
    }

    @Override
    public long injectEnergyUnits(ForgeDirection arg0, long arg1, long arg2) {
        return 0;
    }

    @Override
    public boolean inputEnergyFrom(ForgeDirection arg0) {
        return false;
    }

    @Override
    public boolean outputsEnergyTo(ForgeDirection arg0) {
        return true;
    }

    private void emitEnergy() {
        if(this.voltage == 0) this.voltage = 32;

        int availableAmps = this.storedEU / this.voltage;

        if(availableAmps > 0) {
            long consumedAmps = emitEnergyToNetwork(this.voltage, availableAmps, this);

            for(var dir : DirectionUtil.values()) {
                if((availableAmps - consumedAmps) <= 0) {
                    break;
                }

                if(dir.getTileEntity(worldObj, xCoord, yCoord, zCoord) instanceof TileReactorChamber chamber) {
                    consumedAmps += emitEnergyToNetwork(this.voltage, availableAmps - consumedAmps, chamber);
                }
            }
    
            this.storedEU -= consumedAmps * this.voltage;
        }
    }

    private static long emitEnergyToNetwork(long voltage, long amperage, TileEntity emitter) {
        long usedAmperes = 0;

        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if (usedAmperes > amperage) break;

            ForgeDirection oppositeSide = Objects.requireNonNull(side.getOpposite());
            TileEntity tTileEntity = emitter.getWorldObj().getTileEntity(emitter.xCoord + side.offsetX, emitter.yCoord + side.offsetY, emitter.zCoord + side.offsetZ);
            if (tTileEntity instanceof PowerLogicHost host) {

                PowerLogic logic = host.getPowerLogic(oppositeSide);
                if (logic == null || logic.isEnergyReceiver()) {
                    continue;
                }

                usedAmperes += logic.injectEnergy(voltage, amperage - usedAmperes);
            } else if (tTileEntity instanceof IEnergyConnected energyConnected) {
                usedAmperes += energyConnected.injectEnergyUnits(oppositeSide, voltage, amperage - usedAmperes);

            } else if (tTileEntity instanceof ic2.api.energy.tile.IEnergySink sink) {
                if (sink.acceptsEnergyFrom(emitter, oppositeSide)) {
                    while (amperage > usedAmperes && sink.getDemandedEnergy() > 0
                        && sink.injectEnergy(oppositeSide, voltage, voltage) < voltage) usedAmperes++;
                }
            } else if (GregTech_API.mOutputRF && tTileEntity instanceof IEnergyReceiver receiver) {
                final int rfOut = GT_Utility.safeInt(voltage * GregTech_API.mEUtoRF / 100);
                if (receiver.receiveEnergy(oppositeSide, rfOut, true) == rfOut) {
                    receiver.receiveEnergy(oppositeSide, rfOut, false);
                    usedAmperes++;
                }
            }
        }
        return usedAmperes;
    }

    //#endregion

    //#region Inventory Logic

    private int transformSlotIndex(int invSlot) {
        int cols = this.getColumnCount();

        int col = invSlot % cols;
        int row = invSlot / cols;

        return row * COL_COUNT + col;
    }

    @Override
    public int getSizeInventory() {
        return this.getColumnCount() * ROW_COUNT;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        index = transformSlotIndex(index);

        return this.contents[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        index = transformSlotIndex(index);

        var item = this.contents[index];

        if(item == null) {
            return null;
        }

        this.components[index] = null;

        this.markDirty();

        int consumed = Math.min(item.stackSize, count);

        var out = item.splitStack(consumed);

        if(item.stackSize <= 0) {
            this.contents[index] = null;
        }

        return out;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        index = transformSlotIndex(index);

        var item = this.contents[index];
        
        if(item == null) {
            return null;
        }

        this.contents[index] = null;
        this.components[index] = null;

        this.markDirty();

        return item;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        index = transformSlotIndex(index);

        if(stack != null && stack.stackSize <= 0) {
            stack = null;
        }

        this.contents[index] = stack;
        this.components[index] = null;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    @Override
    public String getInventoryName() {
        return "gui.reactor.name";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {
        
    }

    @Override
    public void closeInventory() {
        
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return ComponentRegistry.isReactorItem(stack);
    }

    //#endregion

    //#region Reactor Grid Logic

    private void doHeatTick() {
        this.storedCoolant = 10000;
        this.storedHotCoolant = 0;
        this.addedHeat = 0;

        for(int row = 0; row < ROW_COUNT; row++) {
            for(int col = 0; col < COL_COUNT; col++) {
                var component = getComponent(col, row);
                if(component != null) {
                    component.onHeatTick();
                }
            }
        }

        for(var reactorBlock : reactorBlocks) {
            reactorBlock.onHeatTick(this);
        }
    }

    private void doEUTick() {
        this.addedEU = 0;

        for(int row = 0; row < ROW_COUNT; row++) {
            for(int col = 0; col < COL_COUNT; col++) {
                var component = getComponent(col, row);
                if(component != null) {
                    component.onEnergyTick();
                }
            }
        }

        for(var reactorBlock : reactorBlocks) {
            reactorBlock.onEnergyTick(this);
        }

        if(this.storedEU > this.maxStoredEU) {
            this.storedEU = this.maxStoredEU;
        }

        int perTick = this.addedEU / 20;

        int voltageTier = (int) (Math.ceil(Math.log(perTick / 8) / Math.log(4)));

        this.voltage = (int) (Math.pow(4, voltageTier) * 8);
    }

    @Override
    public int getWidth() {
        return COL_COUNT;
    }

    @Override
    public int getHeight() {
        return ROW_COUNT;
    }

    @Override
    public @Nullable IComponentAdapter getComponent(int x, int y) {
        if(x < 0 || x >= COL_COUNT) {
            throw new IllegalArgumentException(String.format("Illegal value for x: %d, must conform to x >= 0, x < %d", x, COL_COUNT));
        }

        if(y < 0 || y >= ROW_COUNT) {
            throw new IllegalArgumentException(String.format("Illegal value for y: %d, must conform to y >= 0, y < %d", y, ROW_COUNT));
        }

        int index = y * COL_COUNT + x;

        var adapter = this.components[index];
        if(adapter != null) {
            return adapter;
        }

        var item = this.contents[index];
        if(item != null) {
            adapter = ComponentRegistry.getAdapter(item, this, x, y);
            this.components[index] = adapter;
        }

        return adapter;
    }

    @Override
    public @Nullable ItemStack getItem(int x, int y) {
        if(x < 0 || x >= COL_COUNT) {
            throw new IllegalArgumentException(String.format("Illegal value for x: %d, must conform to x >= 0, x < %d", x, COL_COUNT));
        }

        if(y < 0 || y >= ROW_COUNT) {
            throw new IllegalArgumentException(String.format("Illegal value for y: %d, must conform to y >= 0, y < %d", y, ROW_COUNT));
        }

        int index = y * COL_COUNT + x;

        return this.contents[index];
    }

    @Override
    public void setItem(int x, int y, @Nullable ItemStack item) {
        if(x < 0 || x >= COL_COUNT) {
            throw new IllegalArgumentException(String.format("Illegal value for x: %d, must conform to x >= 0, x < %d", x, COL_COUNT));
        }

        if(y < 0 || y >= ROW_COUNT) {
            throw new IllegalArgumentException(String.format("Illegal value for y: %d, must conform to y >= 0, y < %d", y, ROW_COUNT));
        }

        int index = y * COL_COUNT + x;

        this.contents[index] = item;
        this.components[index] = item != null && ComponentRegistry.isReactorItem(item)
            ? ComponentRegistry.getAdapter(item, this, x, y)
            : null;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public int getHullHeat() {
        return storedHeat;
    }

    @Override
    public int getMaxHullHeat() {
        int maxHeat = 5000;

        for(int row = 0; row < ROW_COUNT; row++) {
            for(int col = 0; col < COL_COUNT; col++) {
                var component = getComponent(col, row);
                if(component != null) {
                    maxHeat += component.getReactorMaxHeatIncrease();
                }
            }
        }

        return maxHeat;
    }

    @Override
    public void setHullHeat(int newHeat) {
        this.storedHeat = newHeat;
    }

    @Override
    public void addHullHeat(int delta) {
        this.storedHeat += delta;
    }

    @Override
    public int addAirHeat(int delta) {
        if(this.isFluid) {
            int emptyHotCoolant = this.maxHotCoolant - this.storedHotCoolant;
            int consumed = Math.min(Math.min(delta, this.storedCoolant / 2), emptyHotCoolant / 2);

            this.storedCoolant -= consumed * 2;
            this.storedHotCoolant += consumed * 2;
            this.addedHeat += consumed * 2;

            return delta - consumed;
        } else {
            return 0;
        }
    }

    @Override
    public void addEU(double eu) {
        this.storedEU += eu;
        
        if(eu > 0) {
            this.addedEU += eu;
        }
    }

    @Override
    public boolean isFluid() {
        return isFluid;
    }

    //#endregion

    //#region Reactor Structure Logic

    private static boolean isInsideCube(int coord) {
        return coord == -1 || coord == 0 || coord == 1;
    }

    private void doStructureCheck() {
        this.isFluid = true;
        this.reactorBlocks.clear();

        for(int relX = -2; relX < 3; relX++) {
            for(int relY = -2; relY < 3; relY++) {
                for(int relZ = -2; relZ < 3; relZ++) {
                    if(relX == 0 && relY == 0 && relZ == 0) {
                        continue;
                    }

                    int insideCubeCount = 0;

                    if(isInsideCube(relX)) insideCubeCount++;
                    if(isInsideCube(relY)) insideCubeCount++;
                    if(isInsideCube(relZ)) insideCubeCount++;

                    boolean isInternal = insideCubeCount == 3;
                    // boolean isFace = insideCubeCount == 2;
                    boolean isEdge = insideCubeCount == 1;
                    boolean isCorner = insideCubeCount == 0;

                    // within the center 3x3x3
                    if(isInternal) {
                        continue;
                    }

                    int x = xCoord + relX, y = yCoord + relY, z = zCoord + relZ;

                    if(!worldObj.blockExists(x, y, z)) {
                        this.isFluid = false;
                        continue;
                    }

                    Block block = worldObj.getBlock(x, y, z);

                    // valid in any spot on the surface
                    if(block == BlockList.PRESSURE_VESSEL) {
                        continue;
                    }

                    // edges & corners must be pressure vessels
                    if(isEdge || isCorner) {
                        if(block != BlockList.PRESSURE_VESSEL) {
                            this.isFluid = false;
                        }
                        continue;
                    }

                    // a face can be any IReactorBlock
                    if(block.hasTileEntity(worldObj.getBlockMetadata(x, y, z))) {
                        if(worldObj.getTileEntity(x, y, z) instanceof IReactorBlock reactorBlock) {
                            reactorBlock.setReactor(this);
                            reactorBlocks.add(reactorBlock);
                            continue;
                        }
                    }

                    this.isFluid = false;
                }
            }
        }
    }

    //#endregion
}
