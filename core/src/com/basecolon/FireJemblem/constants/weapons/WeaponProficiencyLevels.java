package com.basecolon.FireJemblem.constants.weapons;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public enum WeaponProficiencyLevels {
    NO(0),
    E(1),
    D(2),
    C(3),
    B(4),
    A(5),
    S(6),
    PRF(Integer.MAX_VALUE);

    WeaponProficiencyLevels(int rank) {
        this.rank = rank;
    }

    private int rank;

    public int getNumericRank() {
        return rank;
    }

}
