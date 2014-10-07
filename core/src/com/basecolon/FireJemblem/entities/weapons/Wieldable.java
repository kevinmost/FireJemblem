package com.basecolon.FireJemblem.entities.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.constants.weapons.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public interface Wieldable {

    public int getMight();

    public int getCrit();

    public int getHit();

    public int getWeight();

    public int getMinRange();

    public int getMaxRange();

    public int getMaxDurability();

    public String getName();

    public WeaponTypes getType();

    public WeaponProficiencyLevels getRank();

    public Texture getWeaponGraphic();
}
