package com.basecolon.FireJemblem.constants.component.item.weapon;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.unit.UnitWeaponProficiency;
import com.basecolon.FireJemblem.constants.component.item.ItemConstant;

public abstract class WeaponConstant implements ItemConstant {
    protected final ComponentMapper<UnitWeaponProficiency> proficiency = ComponentMapper.getFor(UnitWeaponProficiency.class);

    public abstract WeaponTypes getType();

    public abstract WeaponProficiencyLevels getLevel();

    public abstract Integer getMinRange();

    public abstract Integer getMaxRange();

    public abstract Integer getWeight();

    public abstract Integer getMight();

    public abstract Integer getHit();

    public abstract Integer getCrit();

    /**
     * The default behavior is to check if the proficiency-level for the unit is at least the required level for this
     * weapon, and return true if it is. Special weapons with ranks such as Prf, or special weapons such as Wo Dao
     * can override this method and provide logic for themselves.
     * @param unit The unit that we are testing to see if they can wield this weapon
     * @return true if the unit can wield it, otherwise false
     */
    public boolean canBeWieldedBy(Entity unit) {
        WeaponProficiencyLevels unitProficiencyInThisWeaponType = proficiency.get(unit).get(this.getType());

        return unitProficiencyInThisWeaponType.getNumericRank() >= this.getLevel().getNumericRank();
    }
}
