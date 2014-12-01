package com.basecolon.FireJemblem.constants.component.item.weapon.sword;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.ashley.component.unit.UnitClass;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponConstant;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;

public class WoDao extends WeaponConstant {
    @Override
    public WeaponTypes getType() {
        return WeaponTypes.SWORD;
    }

    @Override
    public WeaponProficiencyLevels getLevel() {
        return WeaponProficiencyLevels.D;
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
        return 8;
    }

    @Override
    public Integer getHit() {
        return 75;
    }

    @Override
    public Integer getCrit() {
        return 35;
    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public String getName() {
        return "Wo Dao";
    }

    @Override
    public Integer getMaxDurability() {
        return 20;
    }

    @Override
    public String getInfoText() {
        return "Only usable by Myrmidons, Swordmasters, and Blade Lords";
    }

    @Override
    public boolean canBeWieldedBy(Entity unit) {
        // First performs a guard-check to make sure that this unit is of the right class. If they are, processes normally.
        ClassTypes unitClass = ComponentMapper.getFor(UnitClass.class).get(unit).getUnitClass();

        //noinspection SimplifiableIfStatement
        if (unitClass != ClassTypes.MYRMIDON && unitClass != ClassTypes.SWORDMASTER && unitClass != ClassTypes.BLADE_LORD) {
            return false;
        }

        return super.canBeWieldedBy(unit);

    }
}
