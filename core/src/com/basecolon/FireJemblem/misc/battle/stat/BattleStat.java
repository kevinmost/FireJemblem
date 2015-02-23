package com.basecolon.firejemblem.misc.battle.stat;

import com.basecolon.firejemblem.misc.battle.precalculations.CalculateWeaponAdvantage;

public enum BattleStat {
    HIT(Integer.class),
    MIGHT(Integer.class),
    CRIT(Integer.class),
    REPEATED_ATTACK(Boolean.class),
    TRIANGLE_ADVANTAGE(CalculateWeaponAdvantage.WeaponTriangleBonuses.class)
    ;

    private final Class<?> type;
    BattleStat(Class<?> type) {
        this.type = type;
    }
    public Class<?> getType() {
        return type;
    }


}
