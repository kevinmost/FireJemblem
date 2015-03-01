package com.basecolon.firejemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;
import com.basecolon.firejemblem.misc.items.Item;
import com.basecolon.firejemblem.misc.items.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * Components aren't supposed to have any logic in them. However, Inventories always have the same rule regarding
 * equipped weapons; the first equippable item in the character's inventory is always auto-equipped. So we are going
 * to put a little bit of logic in here; at any point where the unit's inventory could possibly be modified,
 * we are going to do a check on the inventory and set their first equippable weapon (if any) to their equipped one
 */
public class InventoryComponent extends Component {
    public static final int INVENTORY_MAX_SIZE = 5;

    public List<InventoryItem> items = new ArrayList<>(INVENTORY_MAX_SIZE);

    /**
     * The index of the item in this inventory that is currently equipped
     */
    public Integer equippedItemIndex = -1;

    public static class InventoryItem {
        public Item item;
        /**
         * Whether or not this item is usable by the unit whose inventory it is in
         */
        public boolean isUsable;

        public InventoryItem(Item item) {
            this.item = item;
            this.isUsable = false;
        }
    }

    public InventoryComponent(List<Item> items) {
        // If the list is larger than we're allowed to add, trim it
        if (items.size() > INVENTORY_MAX_SIZE) {
            items = items.subList(0, INVENTORY_MAX_SIZE);
        }
        // Add each one as unusable right now; the {@link com.basecolon.FireJemblem.ashley.system.unit.EquippedItemSystem} will take care of it later
        for (Item item : items) {
            this.items.add(new InventoryItem(item));
        }
    }

    public Weapon getEquippedWeapon() {
        if (equippedItemIndex < 0) {
            return null;
        }
        return items.get(equippedItemIndex).item.as(Weapon.class);
    }
}
