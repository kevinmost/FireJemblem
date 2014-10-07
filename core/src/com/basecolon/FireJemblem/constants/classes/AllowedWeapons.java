package com.basecolon.FireJemblem.constants.classes;

import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class AllowedWeapons {
    private WeaponTypes[] usableWeaponTypes;

    AllowedWeapons(WeaponTypes... usableWeaponTypes) {
        this.usableWeaponTypes = usableWeaponTypes;
    }

    public WeaponTypes[] getUsableWeaponTypes() {
        return usableWeaponTypes;
    }
}
