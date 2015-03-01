package com.basecolon.firejemblem.misc.battle.precalculations_results;

import com.basecolon.firejemblem.misc.battle.precalculations.*;

/**
 * The calculation-steps will be calculated in the order they are written in this enum.
 * This is important because certain steps rely on previous steps in their calculation,
 * and will throw an NPE if done out-of-order.
 */
public enum CalculationOrder {
    CONTINUED_ATTACK(CalculateContinuedAttack.class),
    DISTANCE_BETWEEN(CalculateDistanceBetween.class),
    ATTACK_SPEED(CalculateAttackSpeed.class),
    REPEATED_ATTACK(CalculateRepeatedAttack.class),
    WEAPON_ADVANTAGE(CalculateWeaponAdvantage.class),

    HIT_RATE(CalculateHitRate.class),
    AVOID(CalculateAvoid.class),
    ACCURACY(CalculateAccuracy.class),

    ATTACK_POWER(CalculateAttackPower.class),
    DEFENSE_POWER(CalculateDefensePower.class),
    MIGHT(CalculateMight.class),

    CRIT_RATE(CalculateCritHitRate.class),
    CRIT_AVOID(CalculateCritAvoid.class),
    CRIT_CHANCE(CalculateCritChance.class)
    ;

    private final Class<? extends BaseCalculationStage<?>> stage;

    CalculationOrder(Class<? extends BaseCalculationStage<?>> stage) {
        this.stage = stage;
    }

    public Class<? extends BaseCalculationStage<?>> getStage() {
        return stage;
    }
}
