package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.basecolon.FireJemblem.ashley.component.world.Networked;

/**
 * @author mbs
 * @date 10/12/14
 */
public class EnemyUnitSystem extends UnitSystem {

    ComponentMapper<Networked> networkedComponentMapper;

    public EnemyUnitSystem(Family family) {
        super(family);

        networkedComponentMapper = ComponentMapper.getFor(Networked.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        // TODO wow also this
    }
}