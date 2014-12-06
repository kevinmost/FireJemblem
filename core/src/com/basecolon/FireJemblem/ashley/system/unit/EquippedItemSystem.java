package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.FireJemblem.constants.FireJemblem;
import com.basecolon.FireJemblem.misc.helpers.EntityHelpers;
import com.basecolon.FireJemblem.misc.items.Item;
import com.basecolon.FireJemblem.misc.items.Weapon;

import java.util.List;

public class EquippedItemSystem extends IteratingSystem {

    private static final EntityHelpers.Mappers mappers;

    static {
        mappers = EntityHelpers.mappersFor(new UnitEntityBuilder());
    }

    public EquippedItemSystem() {
        super(Family.getFor(new UnitEntityBuilder().getRequiredComponents()));
        FireJemblem.engine.addSystem(this);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        setEquippedItem(entity);
    }


    private void setEquippedItem(Entity user) {
        Inventory userInventory = mappers.getMapperFor(Inventory.class).get(user);
        List<Item> items = userInventory.getItems();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item instanceof Weapon) {
                if (canUserWield(user, item.as(Weapon.class))) {
                    userInventory.setEquippedWeapon(i);
                    break;
                }
            }
        }
    }

    private boolean canUserWield(Entity user, Weapon weapon) {
        return weapon.getWeapon().canBeWieldedBy(user);
    }

}
