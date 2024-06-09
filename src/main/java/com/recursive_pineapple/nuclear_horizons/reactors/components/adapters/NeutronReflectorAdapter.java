package com.recursive_pineapple.nuclear_horizons.reactors.components.adapters;

import com.recursive_pineapple.nuclear_horizons.reactors.components.IComponentAdapter;
import com.recursive_pineapple.nuclear_horizons.reactors.components.IReactorGrid;
import com.recursive_pineapple.nuclear_horizons.reactors.components.InventoryDirection;
import com.recursive_pineapple.nuclear_horizons.reactors.items.INeutronReflector;

import net.minecraft.item.ItemStack;

public class NeutronReflectorAdapter implements IComponentAdapter {

    private final IReactorGrid reactor;
    private final int x, y;
    private final ItemStack itemStack;
    private final INeutronReflector reflector;

    public NeutronReflectorAdapter(IReactorGrid reactor, int x, int y, ItemStack itemStack, INeutronReflector reflector) {
        this.reactor = reactor;
        this.x = x;
        this.y = y;
        this.itemStack = itemStack;
        this.reflector = reflector;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void onHeatTick() {
        if(!reactor.isActive()) {
            return;
        }

        int damage = 0;

        for(var dir : InventoryDirection.values()) {
            int x2 = dir.offsetX(x);
            int y2 = dir.offsetY(y);

            if(x2 < 0 || y2 < 0 || x2 >= reactor.getWidth() || y2 >= reactor.getHeight()) {
                continue;
            }

            var neighbour = reactor.getComponent(x2, y2);

            if(neighbour != null) {
                damage += neighbour.getFuelRodCount();
            }
        }

        reflector.applyDamage(itemStack, damage);

        if(reflector.getRemainingHealth(itemStack) <= 0) {
            reactor.setItem(x, y, null);
        }
    }

    @Override
    public boolean reflectsNeutrons() {
        return reflector.canReflectNeutrons(itemStack);
    }
}
