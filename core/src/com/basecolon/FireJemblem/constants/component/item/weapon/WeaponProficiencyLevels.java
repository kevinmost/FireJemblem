package com.basecolon.firejemblem.constants.component.item.weapon;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public enum WeaponProficiencyLevels {
    NO(0),
    E(NO),
    D(E),
    C(D),
    B(C),
    A(B),
    S(A),
    PRF(S);

    WeaponProficiencyLevels(int rank) {
        this.rank = rank;
    }

    WeaponProficiencyLevels(WeaponProficiencyLevels levelBefore) {
        this.rank = levelBefore.getNumericRank() + 1;
    }
    private int rank;

    public int getNumericRank() {
        return rank;
    }

}
