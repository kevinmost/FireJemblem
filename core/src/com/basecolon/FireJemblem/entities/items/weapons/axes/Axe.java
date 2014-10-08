package com.basecolon.FireJemblem.entities.items.weapons.axes;

import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;
import com.basecolon.FireJemblem.entities.items.weapons.Weapon;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public abstract class Axe extends Weapon {
    @Override
    public WeaponTypes getType() {
        return WeaponTypes.AXE;
    }
}
