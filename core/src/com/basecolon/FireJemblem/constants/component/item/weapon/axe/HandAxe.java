package com.basecolon.FireJemblem.constants.component.item.weapon.axe;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponConstant;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;

public class HandAxe extends WeaponConstant {
    @Override
    public WeaponTypes getType() {
        return WeaponTypes.AXE;
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
        return 2;
    }

    @Override
    public Integer getWeight() {
        return 12;
    }

    @Override
    public Integer getMight() {
        return 7;
    }

    @Override
    public Integer getHit() {
        return 60;
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
        return "Hand Axe";
    }

    @Override
    public Integer getMaxDurability() {
        return 20;
    }

    @Override
    public String getInfoText() {
        return "";
    }
}
