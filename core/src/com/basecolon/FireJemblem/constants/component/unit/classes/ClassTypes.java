package com.basecolon.FireJemblem.constants.component.unit.classes;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public enum ClassTypes {
    MERCENARY(
            "Mercenary",
            new MaxStats(60, 20, 20, 20, 30, 20, 20, 20)
    ),
    HERO(
            "Hero",
            new MaxStats(60, 25, 30, 26, 30, 25, 22, 20)
    );

    ClassTypes(String className, MaxStats maxStats) {
        this.className = className;
        this.maxStats = maxStats;

    }
    private final String className;
    private final MaxStats maxStats;


    public String getClassName() {
        return className;
    }
    public MaxStats getMaxStats() {
        return maxStats;
    }

}
