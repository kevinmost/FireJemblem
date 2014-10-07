package com.basecolon.FireJemblem.entities.weapons;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public abstract  class Weapon implements Wieldable {

    private int currentDurability;

    public int getCurrentDurability() {
        return currentDurability;
    }
    public void degradeWeapon() {
        currentDurability--;
    }

    public Weapon() {
        currentDurability = getMaxDurability();
    }

}
