package com.basecolon.firejemblem;

import com.badlogic.ashley.core.Entity;
import com.basecolon.firejemblem.ashley.component.unit.DecoratorComponent;
import com.basecolon.firejemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.firejemblem.ashley.system.unit.EquippedItemSystem;
import com.basecolon.firejemblem.constants.FireJemblem;
import com.basecolon.firejemblem.misc.battle.BattleRole;
import com.basecolon.firejemblem.misc.battle.precalculations.BaseCalculationStage;
import com.basecolon.firejemblem.misc.battle.precalculations.BaseCalculationStageDecorator;
import com.basecolon.firejemblem.misc.battle.precalculations.CalculateCritChance;
import com.basecolon.firejemblem.misc.battle.precalculations.CalculateMight;
import com.basecolon.firejemblem.misc.battle.precalculations_results.BattleCalculator;
import com.basecolon.firejemblem.misc.battle.precalculations_results.BattleStats;
import com.basecolon.firejemblem.misc.helpers.EntityHelpers;
import com.basecolon.firejemblem.misc.helpers.GameLauncherHelpers;
import org.junit.Before;
import org.junit.Test;

public class BattleSystemTest {
    private EntityHelpers.Mappers mappers;
    private EquippedItemSystem equippedItemSystem;

    @Before
    public void setup() {
        mappers = EntityHelpers.mappersFor(new UnitEntityBuilder());
        equippedItemSystem = new EquippedItemSystem();
    }

    @Test
    public void calculations() {

        Entity lyn = GameLauncherHelpers.createLyn();
        final DecoratorComponent lynDecorators = lyn.getComponent(DecoratorComponent.class);
        lynDecorators.decorators.add(new IncreaseCritBy15());
        lynDecorators.decorators.add(new IncreaseCritBy15());
        lynDecorators.decorators.add(new IncreaseMightBy2());


        Entity hector = GameLauncherHelpers.createHector();
        final DecoratorComponent hectorDecorators = hector.getComponent(DecoratorComponent.class);
        hectorDecorators.decorators.add(new IncreaseCritBy15());
        hectorDecorators.decorators.add(new IncreaseMightBy2());


        FireJemblem.updateEngine();
        BattleCalculator calculator = new BattleCalculator(lyn, hector);
        BattleStats stats = calculator.getStats();
        System.out.println(stats);
    }

    /**
     * A test-decorator that will increase the crit of whoever it is applied to by 15.
     */
    static class IncreaseCritBy15 extends BaseCalculationStageDecorator<Integer> {
        @Override
        public Class<? extends BaseCalculationStage<Integer>> getCalculationToBeDecorated() {
            return CalculateCritChance.class;
        }

        @Override
        protected Integer calculate(final BattleRole role) {
            final Integer mod;
            if (role == decoratorOwner) {
                mod = 15;
            } else {
                mod = 0;
            }
            return calculationToBeDecorated.getResult().forRole(role) + mod;
        }
    }

    static class IncreaseMightBy2 extends BaseCalculationStageDecorator<Integer> {

        @Override
        public Class<? extends BaseCalculationStage<Integer>> getCalculationToBeDecorated() {
            return CalculateMight.class;
        }

        @Override
        protected Integer calculate(final BattleRole role) {
            final Integer mod;
            if (role == decoratorOwner) {
                mod = 2;
            } else {
                mod = 0;
            }
            return calculationToBeDecorated.getResult().forRole(role) + mod;
        }
    }
}
