package com.basecolon.firejemblem.constants.component.unit;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public enum Condition implements UnitStat {
    BERSERK(0),
    POISON(1),
    SILENCE(2),
    SLEEP(3)
    ;

    Condition(int i) {
        this.i = i;
    }
    private final int i;

    @Override
    public int value() {
        return 0;
    }
}
