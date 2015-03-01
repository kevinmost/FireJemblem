package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public class CalculateMight extends BaseCalculationStage<Integer> {
    public CalculateMight(BattleData data) {
        super(data);
    }

    @Override
    protected Integer calculate(BattleRole role) {
        return data.getResultOfPreviousStage(CalculateAttackPower.class).forRole(role) -
                data.getResultOfPreviousStage(CalculateDefensePower.class).forRole(role.other());
    }
}
