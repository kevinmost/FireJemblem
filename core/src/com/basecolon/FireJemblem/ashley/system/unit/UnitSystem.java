package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.component.unit.UnitIdentity;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public class UnitSystem extends IteratingSystem {

    @SuppressWarnings("unchecked")
    public static final Family unitFamily = Family.getFor(
            UnitStats.class,
            UnitIdentity.class,
            Inventory.class
    );

    public UnitSystem(Family family) {
        super(family);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
// TODO wow also this
    }
}

