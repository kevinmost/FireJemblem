package com.basecolon.FireJemblem.constants.component.unit.classes;

import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;

import java.util.HashMap;
import java.util.Map;

/**
 * This class can be initialized to indicate the max stat caps for a given class
 * @date 10/7/14
 */
public class MaxStats {

    private Map<UnitStatLabels, Integer> stats = new HashMap<>();

    MaxStats(int hp, int str, int mag, int skl, int spd, int lck, int def, int res) {
        stats.put(UnitStatLabels.HP, hp);
        stats.put(UnitStatLabels.STR, str);
        stats.put(UnitStatLabels.SKL, skl);
        stats.put(UnitStatLabels.SPD, spd);
        stats.put(UnitStatLabels.LCK, lck);
        stats.put(UnitStatLabels.DEF, def);
        stats.put(UnitStatLabels.RES, res);
    }

    public Map<UnitStatLabels, Integer> getStats() {
        return stats;
    }
}
