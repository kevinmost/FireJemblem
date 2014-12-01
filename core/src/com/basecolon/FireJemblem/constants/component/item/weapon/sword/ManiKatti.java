package com.basecolon.FireJemblem.constants.component.item.weapon.sword;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.ashley.component.unit.UnitClass;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponConstant;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;

public class ManiKatti extends WeaponConstant {
    @Override
    public WeaponTypes getType() {
        return WeaponTypes.SWORD;
    }

    @Override
    public WeaponProficiencyLevels getLevel() {
        return WeaponProficiencyLevels.PRF;
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
        return 3;
    }

    @Override
    public Integer getMight() {
        return 8;
    }

    @Override
    public Integer getHit() {
        return 80;
    }

    @Override
    public Integer getCrit() {
        return 20;
    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public String getName() {
        return "Mani Katti";
    }

    @Override
    public Integer getMaxDurability() {
        return 45;
    }

    @Override
    public String getInfoText() {
        return "Lyn only; Bonus damage to horseback and armored units";
    }

    @Override
    public boolean canBeWieldedBy(Entity unit) {
        ClassTypes unitClass = ComponentMapper.getFor(UnitClass.class).get(unit).getUnitClass();

        return unitClass == ClassTypes.LORD_LYN || unitClass == ClassTypes.BLADE_LORD;
    }
}
