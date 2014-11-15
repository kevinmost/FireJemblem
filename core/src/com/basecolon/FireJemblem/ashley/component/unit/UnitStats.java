package com.basecolon.FireJemblem.ashley.component.unit;

import com.basecolon.FireJemblem.ashley.component.MappedComponent;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;

public class UnitStats extends MappedComponent<UnitStatLabels, Integer> {
    @Override
    public Integer defaultReturnValue() {
        return 0;
    }
}
