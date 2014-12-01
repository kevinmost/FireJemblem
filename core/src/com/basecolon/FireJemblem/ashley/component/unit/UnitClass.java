package com.basecolon.FireJemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;

public class UnitClass extends Component {
    private ClassTypes unitClass;

    public UnitClass(ClassTypes unitClass) {
        this.unitClass = unitClass;
    }

    public ClassTypes getUnitClass() {
        return unitClass;
    }
}
