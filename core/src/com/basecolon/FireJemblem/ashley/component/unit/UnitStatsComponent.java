package com.basecolon.firejemblem.ashley.component.unit;

import com.basecolon.firejemblem.ashley.component.MappedComponent;
import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import javafx.util.Pair;

import java.util.Map;

public class UnitStatsComponent extends MappedComponent<UnitStatLabels, Integer> {

    public UnitStatsModComponent mods = new UnitStatsModComponent();

    @Override
    protected Integer defaultReturnValue(UnitStatLabels label) {
        if (label == UnitStatLabels.AID) return get(UnitStatLabels.CON) - 1;
        return 0;
    }

    public UnitStatsComponent(Map<UnitStatLabels, Integer> components) {
        super(components);
    }

    public UnitStatsComponent(Pair<UnitStatLabels, Integer>... components) {
        super(components);
    }

    /**
     * When getting the unit's stat, also check their mods
     */
    @Override
    public Integer get(UnitStatLabels key) {
        return super.get(key) + mods.get(key);
    }



}
