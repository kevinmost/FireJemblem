package com.basecolon.firejemblem.misc.battle.precalculations_results;

import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;
import com.basecolon.firejemblem.misc.battle.precalculations.*;

import java.lang.reflect.Field;

public class BattleStats {

    public final BaseCalculationStage<Integer>.BattleStageResult distanceBetween;
    public final BaseCalculationStage<Boolean>.BattleStageResult repeatedAttack;
    public final BaseCalculationStage<Integer>.BattleStageResult continuedAttack;
    public final BaseCalculationStage<CalculateWeaponAdvantage.WeaponTriangleBonuses>.BattleStageResult
            triangleAdvantage;
    public final BaseCalculationStage<Integer>.BattleStageResult hit;
    public final BaseCalculationStage<Integer>.BattleStageResult might;
    public final BaseCalculationStage<Integer>.BattleStageResult crit;

    public BattleStats(BattleData data) {
        distanceBetween = data.getResultOfPreviousStage(CalculateDistanceBetween.class);
        repeatedAttack = data.getResultOfPreviousStage(CalculateRepeatedAttack.class);
        continuedAttack = data.getResultOfPreviousStage(CalculateContinuedAttack.class);
        triangleAdvantage = data.getResultOfPreviousStage(CalculateWeaponAdvantage.class);
        hit = data.getResultOfPreviousStage(CalculateHitRate.class);
        might = data.getResultOfPreviousStage(CalculateMight.class);
        crit = data.getResultOfPreviousStage(CalculateCritChance.class);
    }

    @Override
    public String toString() {
        return "ATTACKER:\n" + fieldsToStringFor(BattleRole.ATTACKER) + "\n\n" + "DEFENDER:\n"
                + fieldsToStringFor(BattleRole.DEFENDER) + "\n\n";
    }

    private String fieldsToStringFor(BattleRole role) {
        final String tableOutputTemplate = "%-25s| %s";
        StringBuilder sb = new StringBuilder();
        for (Field field : getClass().getFields()) {
            final String name = field.getName();
            final BaseCalculationStage.BattleStageResult value;
            try {
                value = (BaseCalculationStage.BattleStageResult) field.get(this);
            } catch (IllegalAccessException e) {
                return "ERROR";
            }
            sb.append(String.format(tableOutputTemplate, name, value.forRole(role))).append("\n");
        }
        return sb.toString();
    }
}
