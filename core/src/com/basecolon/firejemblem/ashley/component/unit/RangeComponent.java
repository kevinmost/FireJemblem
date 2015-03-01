package com.basecolon.firejemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;

public class RangeComponent extends Component {
    public int moveRange;
    public int attackRange;
    public int staffRange;

    public RangeComponent(int moveRange, int attackRange, int staffRange) {
        this.moveRange = moveRange;
        this.attackRange = attackRange;
        this.staffRange = staffRange;
    }
}
