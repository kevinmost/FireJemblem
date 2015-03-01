package com.basecolon.firejemblem.ashley.component.unit;

import com.basecolon.firejemblem.ashley.component.MappedComponent;
import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Modifiers that can be used in things such as Lyn's Sol Katti, which gives her a +5 boost to Resistance.
 */
public class UnitStatsModComponent extends MappedComponent<UnitStatLabels, Integer> {

    public UnitStatsModComponent() {
        super(new HashMap<>());
    }

    public UnitStatsModComponent(Map<UnitStatLabels, Integer> components) {
        super(components);
    }

    @SafeVarargs
    public UnitStatsModComponent(Pair<UnitStatLabels, Integer>... components) {
        super(components);
    }

    @Override
    protected Integer defaultReturnValue(UnitStatLabels key) {
        return 0;
    }
}
