package com.recursive_pineapple.nuclear_horizons.reactors.tile;

import static com.recursive_pineapple.nuclear_horizons.reactors.tile.TileReactorCore.COL_COUNT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gtnewhorizons.modularui.api.ModularUITextures;
import com.gtnewhorizons.modularui.api.drawable.AdaptableUITexture;
import com.gtnewhorizons.modularui.api.math.Alignment;
import com.gtnewhorizons.modularui.api.math.Color;
import com.gtnewhorizons.modularui.api.math.CrossAxisAlignment;
import com.gtnewhorizons.modularui.api.math.MainAxisAlignment;
import com.gtnewhorizons.modularui.api.math.Size;
import com.gtnewhorizons.modularui.api.screen.ITileWithModularUI;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.screen.UIBuildContext;
import com.gtnewhorizons.modularui.api.widget.Widget;
import com.gtnewhorizons.modularui.common.internal.network.NetworkUtils;
import com.gtnewhorizons.modularui.common.internal.wrapper.BaseSlot;
import com.gtnewhorizons.modularui.common.widget.Column;
import com.gtnewhorizons.modularui.common.widget.FakeSyncWidget;
import com.gtnewhorizons.modularui.common.widget.MultiChildWidget;
import com.gtnewhorizons.modularui.common.widget.Row;
import com.gtnewhorizons.modularui.common.widget.SlotGroup;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;
import com.gtnewhorizons.modularui.common.widget.SyncedWidget;
import com.gtnewhorizons.modularui.common.widget.TabButton;
import com.gtnewhorizons.modularui.common.widget.TabContainer;
import com.gtnewhorizons.modularui.common.widget.TextWidget;
import com.gtnewhorizons.modularui.common.widget.VanillaButtonWidget;
import com.gtnewhorizons.modularui.common.widget.textfield.NumericWidget;
import com.gtnewhorizons.modularui.common.widget.textfield.TextFieldWidget;
import com.recursive_pineapple.nuclear_horizons.NuclearHorizons;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator.SimulationConfig;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator.SimulationResult;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator.Simulator;
import com.recursive_pineapple.nuclear_horizons.reactors.tile.simulator.SimulatorProtos;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileReactorSimulator extends TileEntity implements ITileWithModularUI {

    @SideOnly(Side.CLIENT)
    private final Simulator simulator = new Simulator(this);

    @NotNull
    private String configCode = "";

    /**
     * Must be final since the SlotGroup keeps a reference to this specific config.
     */
    private final SimulationConfig config = new SimulationConfig();

    @Nullable
    private SimulationResult latestSimulation;

    @SideOnly(Side.CLIENT)
    private SlotGroup slots;

    @SideOnly(Side.CLIENT)
    private TabContainer tabContainer;

    @SideOnly(Side.CLIENT)
    private int activeComponentX = -1, activeComponentY = -1;

    public TileReactorSimulator() {

    }

    @Override
    public void updateEntity() {
        simulator.pollFinished();
    }

    public void setSimulationResult(SimulationResult result) {
        latestSimulation = result;

        config.result = result;

        if (result != null) result.config = config;

        if (slots != null) {
            for (var child : slots.getChildren()) {
                if (child instanceof SlotWidget slot) {
                    slot.getMcSlot()
                        .onSlotChanged();
                }
            }
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public void setConfigCode(String code) {
        if (!Objects.equals(code, this.configCode)) {
            this.configCode = code;
            this.config.put(SimulationConfig.fromCode(code));

            if (slots != null) {
                for (var child : slots.getChildren()) {
                    if (child instanceof SlotWidget slot) {
                        slot.getMcSlot()
                            .onSlotChanged();
                    }
                }
            }

            if (worldObj != null) worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            markDirty();
        }
    }

    public void onConfigChanged() {
        var code = this.config.getCode();

        if (!Objects.equals(code, configCode)) {
            this.configCode = code;
            if (worldObj != null) worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            markDirty();
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        var nbt = new NBTTagCompound();

        if (this.latestSimulation != null) {
            nbt.setByteArray(
                "history",
                this.latestSimulation.save()
                    .toByteArray());
        }

        nbt.setString("code", configCode);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, blockMetadata, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        var nbt = pkt.func_148857_g();

        setConfigCode(nbt.getString("code"));

        var data = nbt.getByteArray("history");
        if (data.length > 0) {
            try {
                setSimulationResult(SimulationResult.load(SimulatorProtos.SimulationResult.parseFrom(data)));
            } catch (InvalidProtocolBufferException e) {
                NuclearHorizons.LOG.error("Received invalid simulation result", e);
                setSimulationResult(null);
            }
        } else {
            setSimulationResult(null);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("version", 1);
        if (config != null) {
            compound.setString("config", configCode);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        switch (compound.getInteger("version")) {
            case 1: {
                setConfigCode(compound.getString("config"));
                break;
            }
        }
    }

    private static Widget padding(int width, int height) {
        return new TextWidget().setSize(width, height);
    }

    @Override
    public ModularWindow createWindow(UIBuildContext buildContext) {
        ModularWindow.Builder builder = ModularWindow.builder(new Size(212, 234));

        builder.setBackground(ModularUITextures.VANILLA_BACKGROUND);

        builder.widgets(
            new TabContainer().setButtonSize(new Size(28, 32))
                .addTabButton(
                    new TabButton(0)
                        .setBackground(false, ModularUITextures.VANILLA_TAB_TOP_START.getSubArea(0, 0, 1f, 0.5f))
                        .setBackground(true, ModularUITextures.VANILLA_TAB_TOP_START.getSubArea(0, 0.5f, 1f, 1f))
                        .setPos(0, -28))
                .addTabButton(
                    new TabButton(1)
                        .setBackground(false, ModularUITextures.VANILLA_TAB_TOP_MIDDLE.getSubArea(0, 0, 1f, 0.5f))
                        .setBackground(true, ModularUITextures.VANILLA_TAB_TOP_MIDDLE.getSubArea(0, 0.5f, 1f, 1f))
                        .setPos(28, -28))
                .addTabButton(
                    new TabButton(2)
                        .setBackground(false, ModularUITextures.VANILLA_TAB_TOP_END.getSubArea(0, 0, 1f, 0.5f))
                        .setBackground(true, ModularUITextures.VANILLA_TAB_TOP_END.getSubArea(0, 0.5f, 1f, 1f))
                        .setPos(56, -28))
                .addPage(gridPage(buildContext.getPlayer()))
                .addPage(resultsPage())
                .addPage(reactorSettingsPage())
                .consume(w -> { tabContainer = (TabContainer) w; }));

        builder.widgets(
            new FakeSyncWidget.StringSyncer(() -> this.configCode, this::setConfigCode),
            new FakeSyncWidget<SimulationResult>(
                () -> this.latestSimulation,
                this::setSimulationResult,
                SimulationResult::write,
                SimulationResult::read));

        return builder.build();
    }

    private Column gridPage(EntityPlayer player) {
        return new Column().setAlignment(MainAxisAlignment.START, CrossAxisAlignment.CENTER)
            .widgets(
                padding(7, 7),
                new TextWidget(I18n.format("nh_gui.sim.title")).setSize(150, 16),
                new Row().setAlignment(MainAxisAlignment.CENTER, CrossAxisAlignment.CENTER)
                    .widgets(
                        SlotGroup.ofItemHandler(config, COL_COUNT)
                            .phantom(true)
                            .applyForWidget(slot -> { slot.setChangeListener(this::onConfigChanged); })
                            .widgetCreator(slot -> new SimulatorSlotWidget(slot, () -> {
                                this.activeComponentX = slot.getSlotIndex() % COL_COUNT;
                                this.activeComponentY = slot.getSlotIndex() / COL_COUNT;
                                tabContainer.setActivePage(5);
                            }))
                            .build()
                            .consume(w -> { slots = (SlotGroup) w; })),
                padding(2, 2),
                new Row().setAlignment(MainAxisAlignment.START, CrossAxisAlignment.CENTER)
                    .widgets(
                        new VanillaButtonWidget().setDisplayString(I18n.format("nh_gui.sim.actions.start"))
                            .setOnClick((d, w) -> {
                                if (NetworkUtils.isClient()) {
                                    simulator.start(config);
                                }
                            })
                            .setSize(48, 16),
                        padding(2, 2),
                        new VanillaButtonWidget().setDisplayString(I18n.format("nh_gui.sim.actions.cancel"))
                            .setOnClick((d, w) -> {
                                if (NetworkUtils.isClient()) {
                                    simulator.cancel();
                                }
                            })
                            .setSize(48, 16)),
                padding(2, 2),
                SlotGroup.playerInventoryGroup(player, null));
    }

    private static void addResultLine(ArrayList<String> lines, String name, String unit, Object value) {
        var text = I18n.format(
            "nh_gui.sim.results." + name,
            I18n.format(value == null ? "nh_gui.sim.results.none" : ("nh_gui.sim.results." + unit), value));

        lines.addAll(Arrays.asList(text.split("\\\\n")));
    }

    private Widget resultsPage() {
        return new Column().setAlignment(MainAxisAlignment.START, CrossAxisAlignment.CENTER)
            .widgets(
                padding(7, 7),
                new TextWidget(I18n.format("nh_gui.sim.results.title")).setSize(150, 16),
                padding(7, 7),
                TextWidget.dynamicString(() -> {
                    var r = latestSimulation;

                    ArrayList<String> lines = new ArrayList<>();

                    if (r == null) {
                        lines.add(I18n.format("nh_gui.sim.results.no_results"));
                    } else if (r.timeToExplode == null) {
                        addResultLine(lines, "runtime", "seconds", r.simTime);
                    }

                    if (r != null) {
                        if (r.timeToNormal != null) {
                            addResultLine(lines, "time_to_normal", "seconds", r.timeToNormal);
                            lines.add(I18n.format("nh_gui.sim.results.time_to_normal", r.timeToNormal));
                        }

                        if (r.timeToBurn != null) {
                            addResultLine(lines, "time_to_burn", "seconds", r.timeToBurn);
                        }

                        if (r.timeToEvaporate != null) {
                            addResultLine(lines, "time_to_evaporate", "seconds", r.timeToEvaporate);
                        }

                        if (r.timeToHurt != null) {
                            addResultLine(lines, "time_to_hurt", "seconds", r.timeToHurt);
                        }

                        if (r.timeToLava != null) {
                            addResultLine(lines, "time_to_lava", "seconds", r.timeToLava);
                        }

                        if (r.timeToExplode != null) {
                            addResultLine(lines, "time_to_explosion", "seconds", r.timeToExplode);
                        }
                    }

                    addResultLine(lines, "active_time", "seconds", r == null ? null : r.activeTime);
                    addResultLine(lines, "inactive_time", "seconds", r == null ? null : r.pausedTime);

                    addResultLine(lines, "avg_power", "eu_per_tick", r == null ? null : (r.totalEU / 20 / r.simTime));
                    addResultLine(lines, "min_power", "eu_per_tick", r == null ? null : r.minEUpT);
                    addResultLine(lines, "max_power", "eu_per_tick", r == null ? null : r.maxEUpT);

                    addResultLine(lines, "avg_vent_cooling", "hu_per_sec", r == null ? null : (r.totalHU / r.simTime));
                    addResultLine(lines, "min_vent_cooling", "hu_per_sec", r == null ? null : r.minHUpS);
                    addResultLine(lines, "max_vent_cooling", "hu_per_sec", r == null ? null : r.maxHUpS);

                    addResultLine(lines, "avg_hull_temp", "hu_total", r == null ? null : (r.totalTempSecs / r.simTime));
                    addResultLine(lines, "min_hull_temp", "hu_total", r == null ? null : r.minTemp);
                    addResultLine(lines, "max_hull_temp", "hu_total", r == null ? null : r.maxTemp);

                    return String.join("\n", lines);
                })
                    .setTextAlignment(Alignment.TopLeft));
    }

    private static final AdaptableUITexture DISPLAY = AdaptableUITexture
        .of("modularui:gui/background/display", 143, 75, 2);

    private Widget textSetting(String name, Supplier<String> getter, Consumer<String> setter) {
        return new MultiChildWidget().addChild(
            new TextWidget(name).setTextAlignment(Alignment.CenterRight)
                .setPos(-50, 0)
                .setSize(150, 16))
            .addChild(
                new TextFieldWidget().setGetter(getter)
                    .setSetter(v -> {
                        setter.accept(v);

                        this.markDirty();
                        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    })
                    .setScrollBar()
                    .setTextColor(Color.WHITE.dark(1))
                    .setTextAlignment(Alignment.CenterLeft)
                    .setBackground(DISPLAY.withOffset(-2, -2, 4, 4))
                    .setSize(96, 16)
                    .setPos(100, 0));
    }

    private Widget numericSetting(String name, DoubleSupplier getter, DoubleConsumer setter) {
        return new MultiChildWidget().addChild(
            new TextWidget(name).setTextAlignment(Alignment.CenterRight)
                .setPos(-50, 0)
                .setSize(150, 16))
            .addChild(
                new NumericWidget().setGetter(getter)
                    .setSetter(v -> {
                        setter.accept(v);

                        this.markDirty();
                        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    })
                    .setBounds(0, Double.MAX_VALUE)
                    .setTextColor(Color.WHITE.dark(1))
                    .setBackground(DISPLAY.withOffset(-2, -2, 4, 4))
                    .setSize(96, 16)
                    .setPos(100, 0));
    }

    private Widget booleanSetting(String name, BooleanSupplier getter, Consumer<Boolean> setter) {
        String trueText = I18n.format("nh_gui.sim.settings.true"), falseText = I18n.format("nh_gui.sim.settings.false");

        return new MultiChildWidget().addChild(
            new TextWidget(name).setTextAlignment(Alignment.CenterRight)
                .setPos(-50, 0)
                .setSize(150, 16))
            .addChild(
                new VanillaButtonWidget().setDisplayString(getter.getAsBoolean() ? trueText : falseText)
                    .setOnClick((t, u) -> {
                        var val = !getter.getAsBoolean();
                        setter.accept(val);

                        ((VanillaButtonWidget) u).setDisplayString(val ? trueText : falseText);
                        u.notifyTooltipChange();

                        this.markDirty();
                        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    })
                    .setTicker(
                        w -> ((VanillaButtonWidget) w).setDisplayString(getter.getAsBoolean() ? trueText : falseText))
                    .setSize(40, 16)
                    .setPos(98, 0));
    }

    private Widget reactorSettingsPage() {
        return new Row().widgets(
            padding(7, 7),
            new Column().setAlignment(MainAxisAlignment.START, CrossAxisAlignment.START)
                .widgets(
                    padding(7, 7),
                    new Row().setAlignment(MainAxisAlignment.CENTER)
                        .widget(new TextWidget(I18n.format("nh_gui.sim.settings.title")).setSize(150, 16)),
                    padding(7, 7),
                    textSetting(
                        I18n.format("nh_gui.sim.settings.planner_code"),
                        () -> this.configCode,
                        this::setConfigCode),
                    padding(7, 6),
                    numericSetting(
                        I18n.format("nh_gui.sim.settings.max_simulation_ticks"),
                        () -> this.config.maxSimulationTicks,
                        v -> {
                            this.config.maxSimulationTicks = (int) v;
                            onConfigChanged();
                        }),
                    padding(4, 4),
                    booleanSetting(I18n.format("nh_gui.sim.settings.fluid"), () -> this.config.fluid, b -> {
                        this.config.fluid = b;
                        onConfigChanged();
                    }),
                    padding(4, 4),
                    booleanSetting(I18n.format("nh_gui.sim.settings.pulsed"), () -> this.config.pulsed, b -> {
                        this.config.pulsed = b;
                        onConfigChanged();
                    }),
                    padding(4, 4),
                    numericSetting(I18n.format("nh_gui.sim.settings.on_pulse"), () -> this.config.onPulse, v -> {
                        this.config.onPulse = (int) v;
                        onConfigChanged();
                    }),
                    padding(4, 6),
                    numericSetting(I18n.format("nh_gui.sim.settings.off_pulse"), () -> this.config.offPulse, v -> {
                        this.config.offPulse = (int) v;
                        onConfigChanged();
                    }),
                    padding(4, 6),
                    numericSetting(
                        I18n.format("nh_gui.sim.settings.suspend_temp"),
                        () -> this.config.suspendTemp,
                        v -> {
                            this.config.suspendTemp = (int) v;
                            onConfigChanged();
                        }),
                    padding(4, 6),
                    numericSetting(I18n.format("nh_gui.sim.settings.resume_temp"), () -> this.config.resumeTemp, v -> {
                        this.config.resumeTemp = (int) v;
                        onConfigChanged();
                    }),
                    padding(4, 6),
                    numericSetting(
                        I18n.format("nh_gui.sim.settings.initial_reactor_heat"),
                        () -> this.config.initialHeat,
                        v -> {
                            this.config.initialHeat = (int) v;
                            onConfigChanged();
                        })),
            padding(7, 7));
    }

    private Widget componentAutomationPage() {
        return new Row().widgets(
            padding(7, 7),
            new Column().setAlignment(MainAxisAlignment.START, CrossAxisAlignment.START)
                .widgets(
                    padding(7, 7),
                    new Row().setAlignment(MainAxisAlignment.CENTER)
                        .widget(new TextWidget(I18n.format("nh_gui.sim.comp_settings.title")).setSize(150, 16)),
                    padding(7, 7),
                    TextWidget.dynamicString(() -> {
                        String active = null;

                        if (activeComponentX == -1 || activeComponentY == -1) {
                            active = I18n.format("nh_gui.sim.results.none");
                        } else {
                            var comp = config.components[activeComponentX + activeComponentY * COL_COUNT];

                            active = I18n.format(
                                "nh_gui.sim.comp_settings.xy",
                                activeComponentX,
                                activeComponentY,
                                comp == null ? I18n.format("nh_gui.sim.results.none")
                                    : I18n.format(comp.item.getUnlocalizedName()));
                        }

                        return I18n.format("nh_gui.sim.comp_settings.active", active);
                    }),
                    textSetting(
                        I18n.format("nh_gui.sim.settings.planner_code"),
                        () -> this.configCode,
                        this::setConfigCode),
                    padding(7, 7),
                    numericSetting(
                        I18n.format("nh_gui.sim.settings.max_simulation_ticks"),
                        () -> this.config.maxSimulationTicks,
                        v -> {
                            this.config.maxSimulationTicks = (int) v;
                            onConfigChanged();
                        }),
                    padding(4, 4),
                    booleanSetting(I18n.format("nh_gui.sim.settings.fluid"), () -> this.config.fluid, b -> {
                        this.config.fluid = b;
                        onConfigChanged();
                    }),
                    padding(4, 4),
                    booleanSetting(I18n.format("nh_gui.sim.settings.pulsed"), () -> this.config.pulsed, b -> {
                        this.config.pulsed = b;
                        onConfigChanged();
                    }),
                    padding(4, 4),
                    numericSetting(I18n.format("nh_gui.sim.settings.on_pulse"), () -> this.config.onPulse, v -> {
                        this.config.onPulse = (int) v;
                        onConfigChanged();
                    }),
                    padding(4, 6),
                    numericSetting(I18n.format("nh_gui.sim.settings.off_pulse"), () -> this.config.offPulse, v -> {
                        this.config.offPulse = (int) v;
                        onConfigChanged();
                    }),
                    padding(4, 6),
                    numericSetting(
                        I18n.format("nh_gui.sim.settings.suspend_temp"),
                        () -> this.config.suspendTemp,
                        v -> {
                            this.config.suspendTemp = (int) v;
                            onConfigChanged();
                        }),
                    padding(4, 6),
                    numericSetting(I18n.format("nh_gui.sim.settings.resume_temp"), () -> this.config.resumeTemp, v -> {
                        this.config.resumeTemp = (int) v;
                        onConfigChanged();
                    }),
                    padding(4, 6),
                    numericSetting(
                        I18n.format("nh_gui.sim.settings.initial_reactor_heat"),
                        () -> this.config.initialHeat,
                        v -> {
                            this.config.initialHeat = (int) v;
                            onConfigChanged();
                        })),
            padding(7, 7));
    }

    private static class SimulatorSlotWidget extends SlotWidget {

        private Runnable onRightClicked;

        public SimulatorSlotWidget(BaseSlot slot, @NotNull Runnable onRightClicked) {
            super(slot);
            this.onRightClicked = onRightClicked;
        }

        public java.util.List<String> getExtraTooltip() {
            var tooltip = (ArrayList<String>) super.getExtraTooltip();

            tooltip
                .add(EnumChatFormatting.DARK_GRAY.toString() + I18n.format("nh_gui.sim.actions.edit_comp_automation"));

            return tooltip;
        }

        public ClickResult onClick(int button, boolean doubleClick) {
            if (button == 1) {
                Widget.ClickData clickData = ClickData.create(button, doubleClick);
                this.syncToServer(6, clickData::writeToPacket);
                return ClickResult.SUCCESS;
            } else {
                return super.onClick(button, doubleClick);
            }
        }

        public void readOnServer(int id, PacketBuffer buf) throws IOException {
            if (id == 6) {
                if (this.onRightClicked != null) this.onRightClicked.run();

                this.markForUpdate();
            } else {
                super.readOnServer(id, buf);
            }
        }
    }

    private static class MessageChannel<FromServer extends IMessage, FromClient extends IMessage> extends SyncedWidget {

        private @Nullable Class<? extends FromServer> from_server;
        private @Nullable Consumer<FromServer> handle_on_client;
        private @Nullable Class<? extends FromClient> from_client;
        private @Nullable Consumer<FromClient> handle_on_server;

        public MessageChannel(@Nullable Class<? extends FromServer> from_server,
            @Nullable Consumer<FromServer> handle_on_client, @Nullable Class<? extends FromClient> from_client,
            @Nullable Consumer<FromClient> handle_on_server) {
            this.from_server = from_server;
            this.handle_on_client = handle_on_client;
            this.from_client = from_client;
            this.handle_on_server = handle_on_server;
        }

        @Override
        public void readOnClient(int arg0, PacketBuffer arg1) throws IOException {
            if (from_server != null && handle_on_client != null) {
                FromServer message;

                try {
                    message = from_server.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(
                        "Could not create instance of FromServer message; type must have a public no-argument constructor",
                        e);
                }

                message.fromBytes(arg1);

                handle_on_client.accept(message);
            }
        }

        @Override
        public void readOnServer(int arg0, PacketBuffer arg1) throws IOException {
            if (from_client != null && handle_on_server != null) {
                FromClient message;

                try {
                    message = from_client.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(
                        "Could not create instance of FromClient message; type must have a public no-argument constructor",
                        e);
                }

                message.fromBytes(arg1);

                handle_on_server.accept(message);
            }
        }

        public void sendToClient(@NotNull FromServer message) {
            if (NetworkUtils.isClient()) {
                throw new IllegalStateException("cannot call sendToClient() on the client");
            }

            this.syncToClient(0, buf -> { message.toBytes(buf); });
        }

        public void sendToServer(@NotNull FromClient message) {
            if (!NetworkUtils.isClient()) {
                throw new IllegalStateException("cannot call sendToServer() on the server");
            }

            this.syncToServer(0, buf -> { message.toBytes(buf); });
        }
    }
}
