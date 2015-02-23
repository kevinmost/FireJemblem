package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public class CalculateRepeatedAttack extends BaseCalculationStage<Boolean> {
    public CalculateRepeatedAttack(BattleData data) {
        super(data);
    }

    @Override
    protected Boolean calculate(BattleRole role) {
        BaseCalculationStage<Integer>.BattleStageResult attackSpeeds = data.getResultOfPreviousStage(CalculateAttackSpeed.class);
        return attackSpeeds.forRole(role) - attackSpeeds.forRole(role.other()) >= 4;
    }
}
