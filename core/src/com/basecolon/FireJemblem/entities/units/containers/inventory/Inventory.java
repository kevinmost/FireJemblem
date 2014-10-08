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

    public Weapon getItemAsWeapon(InventorySlots slot) throws ItemCastException {
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

    // Convenience methods to set the first 1, 2, 3, 4, or 5 items at once
    public Inventory setItem(Item item1) {
        items[0] = item1;
        return this;
    }
    public Inventory setItem(Item item1, Item item2) {
        setItem(item1);
        items[1] = item2;
        return this;
    }
    public Inventory setItem(Item item1, Item item2, Item item3) {
        setItem(item1, item2);
        items[2] = item3;
        return this;
    }
    public Inventory setItem(Item item1, Item item2, Item item3, Item item4) {
        setItem(item1, item2, item3);
        items[3] = item4;
        return this;
    }
    public Inventory setItem(Item item1, Item item2, Item item3, Item item4, Item item5) {
        setItem(item1, item2, item3, item4);
        items[4] = item5;
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
