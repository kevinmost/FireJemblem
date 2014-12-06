package com.basecolon.FireJemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;
import com.basecolon.FireJemblem.misc.items.Item;
import com.basecolon.FireJemblem.misc.items.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Components aren't supposed to have any logic in them. However, Inventories always have the same rule regarding
 * equipped weapons; the first equippable item in the character's inventory is always auto-equipped. So we are going
 * to put a little bit of logic in here; at any point where the unit's inventory could possibly be modified,
 * we are going to do a check on the inventory and set their first equippable weapon (if any) to their equipped one
 */
public class Inventory extends Component {
    public static final int INVENTORY_MAX_SIZE = 5;

    private List<Item> items = new ArrayList<>(INVENTORY_MAX_SIZE);

    /**
     * The index of the item in this inventory that is currently equipped
     */
    private Integer equippedItemIndex = -1;


    public Inventory(List<Item> items) {
        // If the list is larger than we're allowed to add, trim it
        if (items.size() > INVENTORY_MAX_SIZE) {
            items = items.subList(0, INVENTORY_MAX_SIZE);
        }
        this.items.addAll(items);
    }

    public List<Item> getItems() {
        return items;
    }

    public Integer getEquippedItemIndex() {
        return equippedItemIndex;
    }

    public Weapon getEquippedWeapon() {
        return items.get(equippedItemIndex).as(Weapon.class);
    }

    public boolean setEquippedWeapon(int slot) {
        if (items.size() <= slot) {
            return false;
        }
        this.equippedItemIndex = slot;
        return true;
    }

    /**
     * Adds the given item to the inventory
     * @return true if the inventory was changed as a result of invoking this method, false if not
     */
    public boolean addItem(Item item) {
        //noinspection SimplifiableIfStatement
        if (items.size() >= INVENTORY_MAX_SIZE) {
            return false;
        }
        return items.add(item);
    }

    /**
     * Swap the item at {@param item1} with the item at {@param item2}
     * @return true if the swap was successful, false if not
     */
    public boolean swapItems(int item1, int item2) {
        if (items.size() < item1 || items.size() < item2) {
            return false;
        }

        Item temp = items.set(item1, items.get(item2));
        items.set(item2, temp);
        return true;
    }

    public boolean removeItem(int item) {
        items.remove(item);
        return true;
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }
}
