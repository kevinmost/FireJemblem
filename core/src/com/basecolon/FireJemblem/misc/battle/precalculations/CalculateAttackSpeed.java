package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public class CalculateAttackSpeed extends BaseCalculationStage<Integer> {

    public CalculateAttackSpeed(BattleData data) {
        super(data);
    }

    @Override
    protected Integer calculate(BattleRole role) {
        BattleData.BattleDataCharacter thisCharacter = data.get(role);
        return thisCharacter.stats.get(UnitStatLabels.SPEED) -
                Math.max(0, thisCharacter.equippedWeapon.getWeight() - thisCharacter.stats.get(UnitStatLabels.CON));
    }
}
