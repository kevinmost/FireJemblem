package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public class CalculateAttackPower extends BaseCalculationStage<Integer> {
    public CalculateAttackPower(BattleData data) {
        super(data);
    }

    @Override
    protected Integer calculate(BattleRole role) {
        BattleData.BattleDataCharacter character = data.get(role);

        Integer strength = character.stats.get(UnitStatLabels.STRENGTH);
        Integer weaponMight = character.equippedWeapon.getMight();

        Integer weaponTriangleBonus = data.getResultOfPreviousStage(CalculateWeaponAdvantage.class).forRole(role).mightBonus;

        Integer effectiveDamageBonus =
                character.equippedWeapon.getWeapon().isEffectiveAgainst(data.get(role.other()).unitClass) ?
                getEffectiveDamageCoefficient() : 1;

        return strength + ((weaponMight + weaponTriangleBonus) * effectiveDamageBonus);
    }

    public Integer getEffectiveDamageCoefficient() {
        return 3;
    }

    public Integer getWeaponTriangleAttackPowerBonus() {
        return 1;
    }
}
