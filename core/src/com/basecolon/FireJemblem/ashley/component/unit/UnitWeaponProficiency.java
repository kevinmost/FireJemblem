package com.basecolon.FireJemblem.ashley.component.unit;

import com.basecolon.FireJemblem.ashley.component.MappedComponent;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;

import java.util.Map;

public class UnitWeaponProficiency extends MappedComponent<WeaponTypes, WeaponProficiencyLevels> {
    @Override
    public WeaponProficiencyLevels defaultReturnValue(WeaponTypes weapon) {
        return WeaponProficiencyLevels.NO;
    }

    public UnitWeaponProficiency(Map<WeaponTypes, WeaponProficiencyLevels> components) {
        super(components);
    }
}
