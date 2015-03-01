package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

/**
 * "Continued Attacks" are attacks that occur in succession after each player's "round" when
 * attacking. For example, the Brave Sword gives one Continued Attack to its wielder, because every
 * time they attack, they get one additional strike with it. The Astra skill, for example, gives
 * 4 Continued Attacks.
 */
public class CalculateContinuedAttack extends BaseCalculationStage<Integer> {
    /**
     * Always invoke this constructor from each stage that extends this class.
     * This will place the result into the {@link #result} variable
     */
    public CalculateContinuedAttack(final BattleData data) {
        super(data);
    }

    @Override
    protected Integer calculate(final BattleRole role) {
        return 0;
    }
}
