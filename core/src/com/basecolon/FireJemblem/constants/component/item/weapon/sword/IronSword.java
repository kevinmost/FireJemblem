package com.basecolon.FireJemblem.constants.component.item.weapon.sword;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponConstant;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;

public class IronSword implements WeaponConstant {
    @Override
    public WeaponTypes getType() {
        return WeaponTypes.SWORD;
    }

    @Override
    public WeaponProficiencyLevels getLevel() {
        return WeaponProficiencyLevels.E;
    }

    @Override
    public Integer getMinRange() {
        return 1;
    }

    @Override
    public Integer getMaxRange() {
        return 1;
    }

    @Override
    public Integer getWeight() {
        return 5;
    }

    @Override
    public Integer getMight() {
        return 5;
    }

    @Override
    public Integer getHit() {
        return 90;
    }

    @Override
    public Integer getCrit() {
        return 0;
    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public String getName() {
        return "Iron Sword";
    }

    @Override
    public Integer getMaxDurability() {
        return 46;
    }

    @Override
    public String getInfoText() {
        return null;
    }
}
