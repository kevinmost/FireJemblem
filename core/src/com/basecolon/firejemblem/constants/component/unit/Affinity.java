package com.basecolon.firejemblem.constants.component.unit;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public enum Affinity implements UnitStat {
    ANIMA(0),
    DARK(1),
    FIRE(2),
    ICE(3),
    LIGHT(4),
    THUNDER(5),
    WIND(6)
    ;

    Affinity(int i) {
        this.i = i;
    }
    private final int i;

    @Override
    public int value() {
        return i;
    }
}
