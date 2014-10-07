package com.basecolon.FireJemblem.entities.units.containers.weapons;

import com.basecolon.FireJemblem.constants.weapons.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class WeaponProficiency {

    private Map<WeaponTypes, WeaponProficiencyLevels> weapons = new HashMap<>();

    public WeaponProficiency() {
        // Add each weapon to the Map, with a proficiency of "NO" (these can be overwritten individually for each character)
        for (WeaponTypes type : WeaponTypes.values()) {
            weapons.put(type, WeaponProficiencyLevels.NO);
        }
    }

    public WeaponProficiencyLevels getWeaponProficiencyLevel(WeaponTypes weapon) {
        return weapons.get(weapon);
    }

    public void setWeaponProficiencyLevel(WeaponTypes weapon, WeaponProficiencyLevels level) {
        weapons.put(weapon, level);
    }

}
