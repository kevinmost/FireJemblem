package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleData.BattleDataCharacter;
import com.basecolon.firejemblem.misc.battle.BattleRole;

import static com.basecolon.firejemblem.constants.component.item.weapon.WeaponProficiencyLevels.S;
import static com.basecolon.firejemblem.constants.component.unit.UnitStatLabels.LUCK;
import static com.basecolon.firejemblem.constants.component.unit.UnitStatLabels.SKILL;

public class CalculateHitRate extends BaseCalculationStage<Integer> {
    public CalculateHitRate(BattleData data) {
        super(data);
    }


    @Override
    protected Integer calculate(BattleRole role) {
        BattleDataCharacter thisCharacter = data.get(role);

        return thisCharacter.equippedWeapon.getHit() +
                (thisCharacter.stats.get(SKILL) * 2) +
                (thisCharacter.stats.get(LUCK) / 2) +
                data.getResultOfPreviousStage(CalculateWeaponAdvantage.class).forRole(role).hitRateBonus +
                ((thisCharacter.weaponProficiency.get(thisCharacter.equippedWeapon.getType()) == S) ? 5 : 0);
    }


}
