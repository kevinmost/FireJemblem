package com.basecolon.FireJemblem.units.containers.stats;

import com.badlogic.ashley.core.Component;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class UnitStats extends Component {
    private int currentHP;

    private Map<UnitStatLabels, Integer> stats = new HashMap<>();

    public int getCurrentHP() {
        return currentHP;
    }

    public Map<UnitStatLabels, Integer> getStats() {
        return stats;
    }
}