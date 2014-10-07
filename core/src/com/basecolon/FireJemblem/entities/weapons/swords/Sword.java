package com.basecolon.FireJemblem.entities.weapons.swords;

import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;
import com.basecolon.FireJemblem.entities.weapons.Weapon;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public abstract class Sword extends Weapon {
    protected Sword() {
        setType(WeaponTypes.SWORD);
        // TODO: Add a default graphic here for swords
    }
}
