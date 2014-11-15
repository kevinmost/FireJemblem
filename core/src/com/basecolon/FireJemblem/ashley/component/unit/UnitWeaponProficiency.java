package com.basecolon.FireJemblem.ashley.component.unit;

import com.basecolon.FireJemblem.ashley.component.MappedComponent;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;

public class UnitWeaponProficiency extends MappedComponent<WeaponTypes, WeaponProficiencyLevels> {
    @Override
    public WeaponProficiencyLevels defaultReturnValue() {
        return WeaponProficiencyLevels.NO;
    }
}
