package com.basecolon.FireJemblem.ashley.component.unit;

import com.basecolon.FireJemblem.ashley.component.MappedComponent;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;

import java.util.Map;

public class UnitStats extends MappedComponent<UnitStatLabels, Integer> {
    @Override
    public Integer defaultReturnValue(UnitStatLabels label) {
        if (label == UnitStatLabels.CURRENT_HP) return get(UnitStatLabels.MAX_HP);
        if (label == UnitStatLabels.AID) return get(UnitStatLabels.CON) - 1;
        return 0;
    }

    public UnitStats(Map<UnitStatLabels, Integer> components) {
        super(components);
    }
}
