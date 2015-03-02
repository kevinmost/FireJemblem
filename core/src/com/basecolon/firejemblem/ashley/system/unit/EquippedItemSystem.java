package com.basecolon.firejemblem.ashley.system.unit;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.basecolon.firejemblem.ashley.component.unit.InventoryComponent;
import com.basecolon.firejemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.firejemblem.constants.FireJemblem;
import com.basecolon.firejemblem.constants.component.item.weapon.template.WeaponTemplate;
import com.basecolon.firejemblem.misc.helpers.EntityHelpers;
import com.basecolon.firejemblem.misc.items.Item;
import com.basecolon.firejemblem.misc.items.Weapon;

import java.util.List;

public class EquippedItemSystem extends IteratingSystem {


    public EquippedItemSystem() {
        super(EntityHelpers.familyFor(new UnitEntityBuilder()));
        FireJemblem.engine.addSystem(this);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        setEquippedItem(entity);
    }


    private void setEquippedItem(Entity user) {
        InventoryComponent userInventory = user.getComponent(InventoryComponent.class);
        List<InventoryComponent.InventoryItem> userItems = userInventory.items;

        int indexToAssign = -1;
        for (int i = 0; i < userItems.size(); i++) {
            Item item = userItems.get(i).item;
            if (item instanceof Weapon) {
                WeaponTemplate weapon = item.as(Weapon.class).getWeapon();
                if (weapon.canBeWieldedBy(user)) {
                    indexToAssign = i;
                    break;
                }
            }
        }
        userInventory.equippedItemIndex = indexToAssign;
    }
}
