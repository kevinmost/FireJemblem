package com.basecolon.firejemblem.misc.battle.precalculations_results;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;
import com.basecolon.firejemblem.misc.battle.precalculations.BaseCalculationStage;
import com.basecolon.firejemblem.misc.battle.precalculations.BaseCalculationStageDecorator;

import java.util.List;

public class BattleCalculator {
    private final BattleStats stats;

    public BattleCalculator(Entity attacker, Entity defender) {
        stats = calculate(new BattleData(attacker, defender));
    }

    public BattleStats getStats() {
        return stats;
    }

    private static BattleStats calculate(BattleData data) {
        for (CalculationOrder calculation : CalculationOrder.values()) {
            final Class<? extends BaseCalculationStage<?>> stage = calculation.getStage();

            BaseCalculationStage baseCalculationStage;
            try {
                final Constructor stageConstructor =
                        ClassReflection.getDeclaredConstructor(stage, BattleData.class);
                baseCalculationStage = (BaseCalculationStage) stageConstructor.newInstance(data);
            } catch (ReflectionException e) {
                e.printStackTrace();
                return null;
            }

            for (BattleRole battleRole : new BattleRole[] { BattleRole.ATTACKER,
                    BattleRole.DEFENDER }) {
                List<BaseCalculationStageDecorator<?>> decorators = data.get(battleRole).decorators;
                for (BaseCalculationStageDecorator<?> decorator : decorators) {
                    if (decorator.getCalculationToBeDecorated().getCanonicalName().equals(
                            baseCalculationStage.unwrap().getClass().getCanonicalName())) {
                        //noinspection unchecked
                        decorator.setData(baseCalculationStage);
                        decorator.setDecoratorOwner(battleRole);
                        baseCalculationStage = decorator;
                    }
                }
            }
            data.addStage(baseCalculationStage);
        }
        return new BattleStats(data);
    }
}
