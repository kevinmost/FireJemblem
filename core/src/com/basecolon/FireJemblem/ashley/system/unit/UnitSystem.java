package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.component.unit.UnitIdentity;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;
import com.basecolon.FireJemblem.ashley.component.world.Transform;
import com.basecolon.FireJemblem.ashley.entity.FireEmblemEntities;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public class UnitSystem extends IteratingSystem {

    public static final Family unitFamily = FireEmblemEntities.UNIT.getFamilyOf();

    ComponentMapper<UnitStats> unitStatsComponentMapper;
    ComponentMapper<UnitIdentity> unitIdentityComponentMapper;
    ComponentMapper<Inventory> inventoryComponentMapper;
    ComponentMapper<Transform> transformComponentMapper;

    public UnitSystem(Family family) {
        super(family);

        unitStatsComponentMapper = ComponentMapper.getFor(UnitStats.class);
        unitIdentityComponentMapper = ComponentMapper.getFor(UnitIdentity.class);
        inventoryComponentMapper = ComponentMapper.getFor(Inventory.class);
        transformComponentMapper = ComponentMapper.getFor(Transform.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        // TODO wow also this
    }
}

