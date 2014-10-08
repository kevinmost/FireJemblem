package com.basecolon.FireJemblem.entities.units.containers.stats;

import com.basecolon.FireJemblem.constants.stats.UnitStatLabels;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class UnitStats {
    private int currentHP;
    public int getCurrentHP() {
        return currentHP;
    }
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }
    public void increaseCurrentHPBy(int by) {
        setCurrentHP(currentHP + by);
    }
    public void decreaseCurrentHPBy(int by) {
        setCurrentHP(currentHP - by);
    }

    private Map<UnitStatLabels, Integer> stats = new HashMap<>();
    public Map<UnitStatLabels, Integer> getStats() {
        return stats;
    }

    public UnitStats(int hp, int str, int mag, int skl, int spd, int lck, int def, int res) {
        stats.put(UnitStatLabels.HP, hp);
        stats.put(UnitStatLabels.STR, str);
        stats.put(UnitStatLabels.MAG, mag);
        stats.put(UnitStatLabels.SKL, skl);
        stats.put(UnitStatLabels.SPD, spd);
        stats.put(UnitStatLabels.LCK, lck);
        stats.put(UnitStatLabels.DEF, def);
        stats.put(UnitStatLabels.RES, res);

        currentHP = hp;
    }

}
