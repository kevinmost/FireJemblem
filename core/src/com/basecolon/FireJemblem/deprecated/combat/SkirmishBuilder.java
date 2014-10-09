package com.basecolon.FireJemblem.deprecated.combat;

import com.basecolon.FireJemblem.deprecated.units.Unit;
import com.basecolon.FireJemblem.deprecated.units.containers.inventory.InventorySlots;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public class SkirmishBuilder {
    private Unit attackingUnit;
    private Unit defendingUnit;

    public SkirmishBuilder(Unit attackingUnit, Unit defendingUnit) {
        this.attackingUnit = attackingUnit;
        this.defendingUnit = defendingUnit;
    }

    public void with(InventorySlots slot) {
        // TODO Implement this pls
    }
}
