package com.basecolon.FireJemblem.constants.component.item.weapon;

import com.basecolon.FireJemblem.constants.component.item.ItemConstant;

public interface WeaponConstant extends ItemConstant {

    WeaponTypes getType();

    WeaponProficiencyLevels getLevel();

    Integer getMinRange();

    Integer getMaxRange();

    Integer getWeight();

    Integer getMight();

    Integer getHit();

    Integer getCrit();

}
