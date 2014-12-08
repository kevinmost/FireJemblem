package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.FireJemblem.constants.FireJemblem;
import com.basecolon.FireJemblem.misc.helpers.FamilyHelpers;
import com.basecolon.FireJemblem.misc.items.Item;
import com.basecolon.FireJemblem.misc.items.Weapon;

import java.util.List;

public class EquippedItemSystem extends IteratingSystem {


    public EquippedItemSystem() {
        super(FamilyHelpers.getFamilyOf(UnitEntityBuilder.class));
        FireJemblem.engine.addSystem(this);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        setEquippedItem(entity);
    }


    private void setEquippedItem(Entity user) {
        Inventory userInventory = user.getComponent(Inventory.class);
        List<Inventory.InventoryItem> userItems = userInventory.items;
        for (int i = 0; i < userItems.size(); i++) {
            Item item = userItems.get(i).item;
            if (item instanceof Weapon) {
                if (canUserWield(user, item.as(Weapon.class))) {
                    userInventory.equippedItemIndex = i;
                    break;
                }
            }
        }
    }

    private boolean canUserWield(Entity user, Weapon weapon) {
        return weapon.getWeapon().canBeWieldedBy(user);
    }

}
