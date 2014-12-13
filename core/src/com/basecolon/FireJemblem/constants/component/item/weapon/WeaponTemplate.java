package com.basecolon.FireJemblem.constants.component.item.weapon;

import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.unit.UnitClass;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;
import com.basecolon.FireJemblem.ashley.component.unit.UnitWeaponProficiency;
import com.basecolon.FireJemblem.ashley.system.unit.BattleSystem;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;

/**
 * Contains a method to get a weapon's stats, whether a given unit can wield it, and also all of the battle-calculation
 * logic that goes with this weapon.
 * We place this in the interface because of the sheer quantity of "exceptional" weapons in Fire Emblem. For example,
 * in FE7 alone, there are Light Brand, Runesword, Nosferatu, Eclipse, Reaver weapons, etc. This interface allows any
 * weapon to be created with any sort of behavior, by overriding that weapon's methods.
 */
public interface WeaponTemplate {

    /**
     * @return The weapon's raw stats (such as might, hit, crit, etc)
     */
    public WeaponStats getStats();

    /**
     * Can be called by the {@link com.basecolon.FireJemblem.ashley.system.unit.BattleSystem} to take care of
     * calculations for it. Will perform all modifications upon the BattleSystem that is passed in as a parameter.
     * Will be overridden by, for example, Eclipse, which always halves the defender's HP on hit.
     * @return Number of damage dealt by this hit
     */
    public default int getDamage(BattleSystem calculations) {
        return calculateAttack(calculations) - calculateDefense(calculations);
    }


    /**
     * @return the battle accuracy of the attack (as a percent out of 100).
     */
    public default int getBattleAccuracy(BattleSystem calculations) {
        return calculateAccuracy(calculations) - calculateAvoid(calculations);
    }

    /**
     *
     * @return the percentage chance that this battle will result in a crit (as a percent out of 100).
     */
    public default int getCritAccuracy(BattleSystem calculations) {
        return calculateCrit(calculations) - calculateCritAvoid(calculations);
    }

    /**
     * Does nothing by default.
     * Override this method for special weapons that need to perform an action on hit. For example, the poison sword
     * should add a {@link com.basecolon.FireJemblem.ashley.component.unit.ConditionComponent} of Poison to the defender
     */
    public default void onHit(BattleSystem calculations) {

    }

    default int calculateCrit(BattleSystem calculations) {
        int weaponCrit = calculations.getAttackingEntityWeapon().getCrit();
        int attackingSkill = calculations.getAttackingEntity().getComponent(UnitStats.class).get(UnitStatLabels.SKILL);

        ClassTypes attackingClass = calculations.getAttackingEntity().getComponent(UnitClass.class).unitClass;
        int critBonus = attackingClass == ClassTypes.SWORDMASTER || attackingClass == ClassTypes.BERSERKER ? 15 : 0;

        int sRankBonus = 0;
        if (calculations.getAttackingEntity().getComponent(UnitWeaponProficiency.class).get(calculations.getAttackingEntityWeapon().getType()) == WeaponProficiencyLevels.S) {
            sRankBonus = 5;
        }

        return weaponCrit + (attackingSkill/2) + critBonus + sRankBonus;
    }

    default int calculateCritAvoid(BattleSystem calculations) {
        return calculations.getDefendingEntity().getComponent(UnitStats.class).get(UnitStatLabels.LUCK);
    }

    /**
     * Calculates the accuracy of a given attack. NOTE: "Accuracy" in the calculations is not the percentage chance of
     * an attack landing. That is "battle accuracy". Battle accuracy is the difference between the accuracy of the
     * attacker and the avoid of the defender
     * @return accuracy of the attacker (as a percent out of 100).
     */
    default int calculateAccuracy(BattleSystem calculations) {
        int weaponHit = calculations.getAttackingEntityWeapon().getHit();

        UnitStats attackerStats = calculations.getAttackingEntity().getComponent(UnitStats.class);

        int sRankBonus = 0;
        if (calculations.getAttackingEntity().getComponent(UnitWeaponProficiency.class).get(calculations.getAttackingEntityWeapon().getType()) == WeaponProficiencyLevels.S) {
            sRankBonus = 5;
        }

        return weaponHit + (2 * attackerStats.get(UnitStatLabels.SKILL)) + (attackerStats.get(UnitStatLabels.LUCK) / 2)
                + calculateWeaponTriangleAccuracyBonus(calculations) + sRankBonus;
    }

    default int calculateAvoid(BattleSystem calculations) {
        UnitStats defenderStats = calculations.getDefendingEntity().getComponent(UnitStats.class);

        int weaponPenalty = Math.max(0, calculations.getDefendingEntityWeapon().getWeight() - defenderStats.get(UnitStatLabels.CON));

        int attackSpeed = defenderStats.get(UnitStatLabels.SPEED) - weaponPenalty;

        // TODO: Get terrain-bonus based on unit's position component
        int terrainBonus = 0;

        return (2 * attackSpeed) + defenderStats.get(UnitStatLabels.LUCK) + terrainBonus;
    }

    /**
     * Returns the attack component for this calculation. Meant to be called only in the {@link #getDamage} method, and
     * overridden for special weapons like Eclipse, Light Brand, etc
     * @return The value of the attack component
     */
    default int calculateAttack(BattleSystem calculations) {
        return calculateStr(calculations) +
                ((calculateMight(calculations) + calculateWeaponTriangleDamageBonus(calculations)) * calculateEffectiveDamageCoefficient(calculations));
    }

    /**
     * Returns the defense component for this calculation. Meant to be called only in the {@link #getDamage} method, and
     * overridden for special weapons like Eclipse, Light Brand, etc
     * @return The value of the defense component
     */
    int calculateDefense(BattleSystem calculations);


    /**
     * This can be overridden if we decide to implement Awakening's battle system in the future, for example.
     * @return By default, the attacker's strength.
     */
    default int calculateStr(BattleSystem calculations) {
        return calculations.getAttackingEntity().getComponent(UnitStats.class).get(UnitStatLabels.STRENGTH);
    }

    /**
     * @return By default, the attacker's equipped weapon's might
     */
    default int calculateMight(BattleSystem calculations) {
        return calculations.getAttackingEntityWeapon().getMight();
    }

    /**
     * Can be overridden by, for example, Reaver weapons, which get 2x bonus/penalty
     * @return how much weapon-triangle bonus is given by this weapon.
     */
    default int calculateWeaponTriangleDamageBonus(BattleSystem calculations) {
        switch (calculations.getAttackingEntityWeapon().getType()
                .advantageAgainst(calculations.getDefendingEntityWeapon().getType())) {
            case ADVANTAGE:
                return 1;
            case DISADVANTAGE:
                return -1;
            default:
                return 0;
        }
    }

    /**
     * Can be overridden, for example, if we want to implement the Japanese FE7 battle system, where effective weapons
     * get a 3x bonus instead of a 2x bonus in the international version.
     * @return the coefficient to be used in the multiplication
     */
    default int calculateEffectiveDamageCoefficient(BattleSystem calculations) {
        return isEffectiveAgainst(calculations.getDefendingEntity()) ? 2 : 1;
    }

    /**
     * Can be overridden by, for example, Reaver weapons, which get 2x bonus/penalty
     * @return how much weapon-triangle bonus is given by this weapon.
     */
    default int calculateWeaponTriangleAccuracyBonus(BattleSystem calculations) {
        switch (calculations.getAttackingEntityWeapon().getType()
                .advantageAgainst(calculations.getDefendingEntityWeapon().getType())) {
            case ADVANTAGE:
                return 15;
            case DISADVANTAGE:
                return -15;
        }
        return 0;
    }



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
