package com.basecolon.firejemblem.constants.component.unit;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public enum Gender implements UnitStat {
    MALE(0),
    FEMALE(1)
    ;

    private final int i;
    Gender(int i) {
        this.i = i;
    }

    @Override
    public int value() {
        return i;
    }
}
