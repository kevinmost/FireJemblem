package com.basecolon.FireJemblem.constants.component.item.weapon;

import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;
import com.basecolon.FireJemblem.ashley.component.unit.UnitWeaponProficiency;
import com.basecolon.FireJemblem.ashley.system.unit.BattleSystem;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.FireJemblem.misc.items.Weapon;

public interface WeaponTemplate {

    public WeaponStats getStats();




    /**
     * Can be called by the {@link com.basecolon.FireJemblem.ashley.system.unit.BattleSystem} to take care of
     * calculations for it. Will perform all modifications upon the BattleSystem that is passed in as a parameter.
     * @return Number of damage dealt by this hit
     */
    public default int onHit(BattleSystem calculations) {
        return calculateAttack(calculations) - calculateDefense(calculations);
    }

    /**
     * Returns the attack component for this calculation. Meant to be called only in the {@link #onHit} method, and
     * overridden for special weapons like Eclipse, Light Brand, etc
     * @return The value of the attack component
     */
    default int calculateAttack(BattleSystem calculations) {
        UnitStats attackerStats = calculations.getAttackingEntity().getComponent(UnitStats.class);
        Weapon attackerWeapon = calculations.getAttackingEntity().getComponent(Inventory.class).getEquippedWeapon();
        Weapon defenderWeapon = calculations.getDefendingEntity().getComponent(Inventory.class).getEquippedWeapon();

        int str = attackerStats.get(UnitStatLabels.STRENGTH);
        int weaponMight = attackerWeapon.getMight();

        int weaponTriangleBonus = 0;
        switch (attackerWeapon.getType().advantageAgainst(defenderWeapon.getType())) {
            case ADVANTAGE:
                weaponTriangleBonus = 1;
                break;
            case DISADVANTAGE:
                weaponTriangleBonus = -1;
        }

        int effectiveDamageCoefficient = 1;
        if (isEffectiveAgainst(calculations.getDefendingEntity())) {
            effectiveDamageCoefficient = 2;
        }

        return str + ((weaponMight + weaponTriangleBonus) * effectiveDamageCoefficient);
    }

    /**
     * Returns the defense component for this calculation. Meant to be called only in the {@link #onHit} method, and
     * overridden for special weapons like Eclipse, Light Brand, etc
     * @return The value of the defense component
     */
    int calculateDefense(BattleSystem calculations);

    /**
     * The default behavior is to check if the proficiency-level for the unit is at least the required level for this
     * weapon, and return true if it is. Special weapons with ranks such as Prf, or special weapons such as Wo Dao
     * can override this method and provide logic for themselves.
     * @param unit The unit that we are testing to see if they can wield this weapon
     * @return true if the unit can wield it, otherwise false
     */
    public default boolean canBeWieldedBy(Entity unit) {
        WeaponTypes thisWeaponType = this.getStats().type();
        WeaponProficiencyLevels unitProficiencyInThisWeaponType = unit.getComponent(UnitWeaponProficiency.class).get(thisWeaponType);

        return unitProficiencyInThisWeaponType.getNumericRank() >= this.getStats().level().getNumericRank();
    }

    /**
     * @return Whether or not this weapon deals effective damage against {@param otherEntity}
     * (ie, bows against a pegasus knight, Wyrmslayer against a wyvern...)
     * If a weapon wants to implement this, it has to override this method and implement the logic itself
     */
    default boolean isEffectiveAgainst(Entity otherEntity) {
        return false;
    }
}
