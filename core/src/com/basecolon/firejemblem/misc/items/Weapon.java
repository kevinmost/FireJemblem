package com.basecolon.firejemblem.misc.items;

import com.basecolon.firejemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.firejemblem.constants.component.item.weapon.template.WeaponTemplate;

/**
 * A Weapon that will be used throughout the game. Unlike WeaponTemplates, Weapons are actually
 * interacted with as
 * you would expect. The constructor for a Weapon imports all of its stats from a WeaponTemplate
 * object and then
 */
public interface Weapon {
    public WeaponTypes getType();

    public WeaponProficiencyLevels getLevel();

    public Integer getMinRange();

    public Integer getMaxRange();

    public Integer getWeight();

    public Integer getMight();

    public Integer getHit();

    public Integer getCrit();

    public WeaponTemplate getWeapon();

    public default boolean canHitAtRange(int range) {
        return getMinRange() < range && range < getMaxRange();
    }
}
