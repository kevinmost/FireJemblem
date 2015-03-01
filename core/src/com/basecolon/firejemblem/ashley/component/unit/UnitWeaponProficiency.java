package com.basecolon.firejemblem.ashley.component.unit;

import com.basecolon.firejemblem.ashley.component.MappedComponent;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponTypes;

import java.util.Map;

public class UnitWeaponProficiency extends MappedComponent<WeaponTypes, WeaponProficiencyLevels> {
    @Override
    protected WeaponProficiencyLevels defaultReturnValue(WeaponTypes weapon) {
        return WeaponProficiencyLevels.NO;
    }

    public UnitWeaponProficiency(Map<WeaponTypes, WeaponProficiencyLevels> components) {
        super(components);
    }
}
