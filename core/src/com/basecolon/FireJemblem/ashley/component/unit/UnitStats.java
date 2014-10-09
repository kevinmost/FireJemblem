package com.basecolon.FireJemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public class UnitStats extends Component {
    private Map<UnitStatLabels, Integer> stats = new HashMap<UnitStatLabels, Integer>() {{
        for (UnitStatLabels unitStatLabel : UnitStatLabels.values()) {
            put(unitStatLabel, 0);
        }
    }};
}
