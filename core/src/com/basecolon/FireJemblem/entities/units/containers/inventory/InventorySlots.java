package com.basecolon.FireJemblem.entities.units.containers.inventory;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public enum InventorySlots {
    SLOT_1(0),
    SLOT_2(1),
    SLOT_3(2),
    SLOT_4(3),
    SLOT_5(4);

    private final int slot;
    InventorySlots(int slot) {
        this.slot = slot;
    }

    public int value() {
        return slot;
    }

    public static int getNumberOfSlots() {
        return InventorySlots.values().length;
    }
}
