package com.basecolon.FireJemblem.constants.classes;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public enum ClassTypes {
    MERCENARY(
            "Mercenary", // Name
            new MaxStats(60, 26, 20, 28, 26, 30, 25, 23), // The max stats for any unit of this class
            new AvailablePromotions() // The available promotions for this class
    )
    ;

    ClassTypes(String className, MaxStats maxStats, AvailablePromotions availablePromotions) {
        this.className = className;
        this.maxStats = maxStats;
        this.availablePromotions = availablePromotions;
    }
    private final String className;
    private final MaxStats maxStats;
    private final AvailablePromotions availablePromotions;

    public String getClassName() {
        return className;
    }
    public MaxStats getMaxStats() {
        return maxStats;
    }
    public AvailablePromotions getAvailablePromotions() {
        return availablePromotions;
    }
}
