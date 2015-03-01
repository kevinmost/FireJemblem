package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public class CalculateCritAvoid extends BaseCalculationStage<Integer> {
    public CalculateCritAvoid(BattleData data) {
        super(data);
    }

    @Override
    protected Integer calculate(BattleRole role) {
        return data.get(role).stats.get(UnitStatLabels.LUCK);
    }
}
