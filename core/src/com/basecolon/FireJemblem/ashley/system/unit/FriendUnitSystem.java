package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.basecolon.FireJemblem.ashley.component.world.Selectable;

/**
 * @author mbs
 * @date 10/12/14
 */
public class FriendUnitSystem extends UnitSystem {

    ComponentMapper<Selectable> selectableComponentMapper;

    public FriendUnitSystem(Family family) {
        super(family);

        selectableComponentMapper = ComponentMapper.getFor(Selectable.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        // TODO wow also this
    }
}
