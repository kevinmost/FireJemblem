package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.ashley.component.PositionComponent;
import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public class CalculateDistanceBetween extends BaseCalculationStage<Integer> {
    public CalculateDistanceBetween(BattleData data) {
        super(data);
    }

    @Override
    protected Integer calculate(BattleRole role) {
        PositionComponent myPosition = data.get(role).position;
        PositionComponent theirPosition = data.get(role.other()).position;

        return Math.abs(myPosition.x - theirPosition.x) + Math.abs(myPosition.y - theirPosition.y);
    }
}
