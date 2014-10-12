package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.basecolon.FireJemblem.ashley.entity.FireEmblemEntities;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public class UnitSystem extends IteratingSystem {

    public static final Family unitFamily = FireEmblemEntities.FRIEND_UNIT.getFamilyOf();

    public UnitSystem(Family family) {
        super(family);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
// TODO wow also this
    }
}

