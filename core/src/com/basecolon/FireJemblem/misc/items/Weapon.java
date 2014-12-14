package com.basecolon.FireJemblem.misc.items;

import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponStats;
import com.basecolon.FireJemblem.constants.component.item.weapon.template.WeaponTemplate;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;

/**
 * A Weapon that will be used throughout the game. Unlike WeaponTemplates, Weapons are actually interacted with as
 * you would expect. The constructor for a Weapon imports all of its stats from a WeaponTemplate object and then
 */
public class Weapon extends Item {

    private final WeaponTypes type;
    private final WeaponProficiencyLevels level;
    private final Integer minRange;
    private final Integer maxRange;
    private final Integer weight;
    private final Integer might;
    private final Integer hit;
    private final Integer crit;
    private final WeaponTemplate weapon;


    public Weapon(WeaponTemplate fromTemplate) {
        // TODO: Actually construct a sprite from the stats spritePath attribute
        super(null, fromTemplate.getStats().name(), fromTemplate.getStats().durability(), fromTemplate.getStats().durability(), fromTemplate.getStats().infotext());
        this.weapon = fromTemplate;

        WeaponStats weaponStats = fromTemplate.getStats();
        this.type = weaponStats.type();
        this.level = weaponStats.level();
        this.minRange = weaponStats.minRange();
        this.maxRange = weaponStats.maxRange();
        this.weight = weaponStats.weight();
        this.might = weaponStats.might();
        this.hit = weaponStats.hit();
        this.crit = weaponStats.crit();
    }


    public WeaponTypes getType() {
        return type;
    }

    public WeaponProficiencyLevels getLevel() {
        return level;
    }

    public Integer getMinRange() {
        return minRange;
    }

    public Integer getMaxRange() {
        return maxRange;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getMight() {
        return might;
    }

    public Integer getHit() {
        return hit;
    }

    public Integer getCrit() {
        return crit;
    }

    public WeaponTemplate getWeapon() {
        return weapon;
    }

}
