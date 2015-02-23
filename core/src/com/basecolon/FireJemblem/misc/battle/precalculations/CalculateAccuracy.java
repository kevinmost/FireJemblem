package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public class CalculateAccuracy extends BaseCalculationStage<Integer> {

    public CalculateAccuracy(BattleData data) {
        super(data);
    }


    @Override
    protected Integer calculate(BattleRole role) {
        return data.getResultOfPreviousStage(CalculateHitRate.class).forRole(role) -
                data.getResultOfPreviousStage(CalculateAvoid.class).forRole(role.other());
    }


}
