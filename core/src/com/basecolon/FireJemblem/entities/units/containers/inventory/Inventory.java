package com.basecolon.FireJemblem.entities.units.containers.inventory;

import com.basecolon.FireJemblem.entities.items.BlankItem;
import com.basecolon.FireJemblem.entities.items.Item;
import com.basecolon.FireJemblem.entities.items.ItemFactory;
import com.basecolon.FireJemblem.entities.items.ItemCastException;
import com.basecolon.FireJemblem.entities.items.usables.Usable;
import com.basecolon.FireJemblem.entities.items.weapons.Weapon;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class Inventory {

    /**
     * The items in this Unit Inventory. It is set to be equal in size to the number of inventory slots specified in the InventorySlots class.
     */
    private Item[] items = new Item[InventorySlots.getNumberOfSlots()];

    public Inventory() {
        for (int i = 0; i < items.length; i++) {
            items[i] = ItemFactory.create(BlankItem.class);
        }
    }

    /**
     * Get all of the items in this inventory
     * @return An array of all of the slots in this inventory, with their attached items
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Get the item in the given slot
     * @param slot The slot to get
     * @return The item in the given slot
     */
    public Item getItem(InventorySlots slot) {
        return items[slot.value()];
    }

    public Usable getItemAsUsable(InventorySlots slot) throws ItemCastException {
        Item item = getItem(slot);
        if (item instanceof Usable) {
            return (Usable) item;
        }
        else {
            throw new ItemCastException("The item in slot " + (slot.value()+1) + " is not a Usable. Item: " + item.getName());
        }
    }

    public Weapon getItemAsWeapon(InventorySlots slot) {
        Item item = getItem(slot);
        if (item instanceof Weapon) {
            return (Weapon) item;
        }
        else {
            throw new ItemCastException("The item in slot " + (slot.value()+1) + " is not a Weapon. Item: " + item.getName());
        }
    }

    /**
     * Set a given inventory slot to hold a given item
     * @param slot The slot
     * @param item The item to hold
     * @return This same inventory, so you can chain more setItem() calls to it
     */
    public Inventory setItem(InventorySlots slot, Item item) {
        items[slot.value()] = item;
        return this;
    }

    /**
     * Deletes an item by replacing it with a BlankItem
     * @param slot The slot to remove the item in
     * @return The inventory after replacing the item in the given slot with a BlankItem
     */
    public Inventory deleteItem(InventorySlots slot) {
        setItem(slot, ItemFactory.create(BlankItem.class));
        return this;
    }

    /**
     * Given two slots in this inventory, swaps the two elements and then returns the same Inventory
     * @param slot1 The first slot
     * @param slot2 The second slot
     * @return The inventory after it has been swapped
     */
    public Inventory swapElementsInArray(InventorySlots slot1, InventorySlots slot2) {
        Item tempItem = items[slot1.value()];
        items[slot1.value()] = items[slot2.value()];
        items[slot2.value()] = tempItem;

        return this;
    }
}
