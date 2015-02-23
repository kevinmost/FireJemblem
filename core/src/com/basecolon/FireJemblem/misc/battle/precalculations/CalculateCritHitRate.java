package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

import static com.basecolon.firejemblem.constants.component.item.weapon.WeaponProficiencyLevels.S;

public class CalculateCritHitRate extends BaseCalculationStage<Integer> {
    public CalculateCritHitRate(BattleData data) {
        super(data);
    }

    @Override
    protected Integer calculate(BattleRole role) {
        // TODO: This should definitely not be hardcoded in here, it should be with the classes themselves, but I don't
        // know where exactly in the class I would put the decorator (and the code in the system to grab that decorator
        // and put it in the unit's DecoratorComponent
        ClassTypes unitClass = data.get(role).unitClass;
        Integer critBonus = unitClass == ClassTypes.BERSERKER || unitClass == ClassTypes.SWORDMASTER ? 15 : 0;


        Integer sRankBonus = (data.get(role).weaponProficiency.get(data.get(role).equippedWeapon.getType()) == S) ? 5 : 0;

        return data.get(role).equippedWeapon.getCrit() +
                (data.get(role).stats.get(UnitStatLabels.SKILL) / 2) +
                critBonus +
                sRankBonus;
    }
}
