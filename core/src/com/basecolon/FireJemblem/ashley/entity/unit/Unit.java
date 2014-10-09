package com.basecolon.FireJemblem.ashley.entity.unit;

import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.component.unit.UnitIdentity;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public class Unit extends Entity {
    public Unit() {
        this.add(new UnitStats());
        this.add(new UnitIdentity());
        this.add(new Inventory());
    }
}
