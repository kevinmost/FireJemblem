package com.basecolon.FireJemblem.constants.component.item.weapon;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public enum WeaponTypes {
    SWORD("sword"),
    LANCE("lance"),
    AXE("axe"),
    BOW("bow"),
    ANIMA("anima"),
    LIGHT("light"),
    DARK("staff");

    WeaponTypes(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
}
