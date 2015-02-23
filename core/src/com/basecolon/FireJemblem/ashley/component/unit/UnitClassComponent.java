package com.basecolon.firejemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;
import com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes;

public class UnitClassComponent extends Component {
    public ClassTypes unitClass;

    public UnitClassComponent(ClassTypes unitClass) {
        this.unitClass = unitClass;
    }
}
