package com.basecolon.FireJemblem.entities.weapons.swords;

import com.basecolon.FireJemblem.constants.weapons.WeaponProficiencyLevels;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class IronSword extends Sword {
    public IronSword() {
        setName("Iron Sword");
        setMight(5);
        setCrit(0);
        setMaxDurability(46);
        setDurability(getMaxDurability());
        setHit(90);
        setRank(WeaponProficiencyLevels.E);
        setMaxRange(1);
        setMinRange(getMaxRange());
        setWeight(5);
    }
}
