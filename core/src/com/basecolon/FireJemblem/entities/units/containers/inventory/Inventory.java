package com.basecolon.FireJemblem.entities.units.containers.inventory;

import com.basecolon.FireJemblem.entities.weapons.Weapon;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class Inventory {

    private Weapon[] items = new Weapon[InventorySlots.getNumberOfSlots()];

    public Weapon[] getItems() {
        return items;
    }

    public Weapon getItem(InventorySlots slot) throws IndexOutOfBoundsException {
        return items[slot.value()];
    }

    public Inventory setItem(InventorySlots slot, Weapon item) throws IndexOutOfBoundsException {
        items[slot.value()] = item;
        return this;
    }

}
