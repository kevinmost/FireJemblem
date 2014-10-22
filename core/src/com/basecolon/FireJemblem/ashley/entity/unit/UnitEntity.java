package com.basecolon.FireJemblem.ashley.entity.unit;

import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.component.unit.UnitIdentity;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;
import com.basecolon.FireJemblem.ashley.component.world.Transform;

public class UnitEntity extends Entity {
    public UnitStats unitStats;
    public UnitIdentity unitIdentity;
    public Inventory inventory;
    public Transform transform;
}
