package com.basecolon.FireJemblem.constants.component.unit.classes;

/**
 * This class can be instantiated with a list of the promotions that this class can reach
 * @date 10/7/14
 */
public class AvailablePromotions {
    private final ClassTypes[] availableClasses;

    AvailablePromotions(ClassTypes... availableClasses) {
        this.availableClasses = availableClasses;
    }

    public ClassTypes[] getAvailableClasses() {
        return availableClasses;
    }
}
