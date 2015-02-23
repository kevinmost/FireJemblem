package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

import static com.basecolon.firejemblem.constants.component.unit.UnitStatLabels.LUCK;

public class CalculateAvoid extends BaseCalculationStage<Integer> {
    public CalculateAvoid(BattleData data) {
        super(data);
    }

    @Override
    protected Integer calculate(BattleRole role) {
        return 2 * data.getResultOfPreviousStage(CalculateAttackSpeed.class).forRole(role) + data.get(role).stats.get(LUCK);
    }
}
