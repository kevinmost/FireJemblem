package com.basecolon.firejemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;

public class ConditionComponent extends Component {
    public Condition condition;

    public ConditionComponent(Condition condition) {
        this.condition = condition;
    }

    public enum Condition {
        NONE,
        POISON,
        SILENCE,
        SLEEP,
        BERSERK,
        STONE // TODO: There are no plans right now to base this game off anything but FE7, so we don't really need Stone, but it can be included anyway
    }
}
