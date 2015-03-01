package com.basecolon.firejemblem.constants.component.item.weapon;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public enum WeaponTypes {
    SWORD("sword", 0),
    LANCE("lance", 1),
    AXE("axe", 2),

    ANIMA("anima", 5),
    DARK("dark", 6),
    LIGHT("light", 7),

    // These are always neutral in the weapon-triangle so their ordinal values are very off
    BOW("bow", Integer.MAX_VALUE - 100),
    STAFF("staff", Integer.MAX_VALUE);

    WeaponTypes(String name, int i) {
        this.name = name;
        this.i = i;
    }
    private String name;
    private int i;

    public String getName() {
        return name;
    }

    /**
     * If (attacker - defender) == 1 or -2, attacker has advantage. If (attacker - defender) == 2 or -1, attacker has
     * disadvantage. If any other value, there is no advantage/disadvantage.
     * @return What kind of advantage-status this weapon has against the other weapon
     */
    public WeaponAdvantage advantageAgainst(WeaponTypes otherWeapon) {
        int difference = this.i - otherWeapon.i;
        switch (difference) {
            case 1: case -2:
                return WeaponAdvantage.ADVANTAGE;
            case 2: case -1:
                return WeaponAdvantage.DISADVANTAGE;
        }
        return WeaponAdvantage.NEUTRAL;
    }


    public enum WeaponAdvantage {
        ADVANTAGE,
        DISADVANTAGE,
        NEUTRAL;

        /**
         * Reverses the Weapon Advantage triangle; useful for the -reaver weapons
         * @return The opposite of the current object's advantage
         */
        public WeaponAdvantage reverse() {
            if (this == ADVANTAGE) return DISADVANTAGE;
            if (this == DISADVANTAGE) return ADVANTAGE;
            return NEUTRAL;
        }
    }
}
