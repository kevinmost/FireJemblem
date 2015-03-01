package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public class CalculateCritChance extends BaseCalculationStage<Integer> {
    public CalculateCritChance(BattleData data) {
        super(data);
    }

    @Override
    protected Integer calculate(BattleRole role) {
        return data.getResultOfPreviousStage(CalculateCritHitRate.class).forRole(role) -
                data.getResultOfPreviousStage(CalculateCritAvoid.class).forRole(role.other());
    }
}
