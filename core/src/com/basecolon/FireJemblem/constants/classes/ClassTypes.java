package com.basecolon.FireJemblem.constants.classes;

import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public enum ClassTypes {
    MERCENARY(
            "Mercenary", // Name
            new AllowedWeapons(WeaponTypes.SWORD), // Usable weapons
            new MaxStats(60, 26, 20, 28, 26, 30, 25, 23), // The max stats for any unit of this class
            new AvailablePromotions() // The available promotions for this class
    )
    ;

    ClassTypes(String className, AllowedWeapons allowedWeapons, MaxStats maxStats, AvailablePromotions availablePromotions) {
        this.className = className;
        this.allowedWeapons = allowedWeapons;
        this.maxStats = maxStats;
        this.availablePromotions = availablePromotions;
    }
    private final String className;
    private final AllowedWeapons allowedWeapons;
    private final MaxStats maxStats;
    private final AvailablePromotions availablePromotions;

    public String getClassName() {
        return className;
    }
    public AllowedWeapons getAllowedWeapons() {
        return allowedWeapons;
    }
    public MaxStats getMaxStats() {
        return maxStats;
    }
    public AvailablePromotions getAvailablePromotions() {
        return availablePromotions;
    }
}
