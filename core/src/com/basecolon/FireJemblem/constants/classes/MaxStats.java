package com.basecolon.FireJemblem.constants.classes;

import com.basecolon.FireJemblem.constants.stats.UnitStatLabels;

import java.util.HashMap;
import java.util.Map;

/**
 * This class can be initialized to indicate the max stat caps for a given class
 * @date 10/7/14
 */
public class MaxStats {

    private Map<UnitStatLabels, Integer> stats = new HashMap<>();
    private String affinity; // TODO make enum for this

    MaxStats(int hp, int str, int mag, int skl, int spd, int lck, int def, int res, int con, int mov, int aid, String ) {
        UnitStatLabels[] statLabels = UnitStatLabels.values();

        for (int i = 0; i < statLabels.length; i++) {

        }
    }
}
