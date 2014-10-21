package com.basecolon.FireJemblem.items.weapons;

import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.items.Item;

/**
 * A weapon in particular. All weapons are also items.
 * @author kevinmost
 * @date 10/7/14
 */
public abstract class Weapon extends Item {

    public abstract int getMight();

    public abstract int getCrit();

    public abstract int getHit();

    public abstract int getWeight();

    public abstract int getMinRange();

    public abstract int getMaxRange();

    public abstract WeaponTypes getType();

    public abstract WeaponProficiencyLevels getRank();

}
