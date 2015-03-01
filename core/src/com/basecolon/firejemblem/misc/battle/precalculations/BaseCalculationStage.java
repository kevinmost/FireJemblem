package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public abstract class BaseCalculationStage<T> {
    /**
     * The data about who is participating in this battle.
     * WARNING: Do not modify any of this data in any calculation stage. This data is meant to be
     * there for reference
     * in calculations only.
     */
    protected BattleData data;
    /**
     * The result yielded by the battle calculations in this class.
     */
    private BattleStageResult result;

    /**
     * Always invoke this constructor from each stage that extends this class.
     * This will place the result into the {@link #result} variable
     */
    public BaseCalculationStage(BattleData data) {
        this.data = data;
    }


    protected abstract T calculate(BattleRole role);


    public final BattleStageResult getResult() {
        // Lazy-loading results helps prevent NPEs when decorating
        if (result == null) {
            result = new BattleStageResult(calculate(BattleRole.ATTACKER),
                    calculate(BattleRole.DEFENDER));
        }
        return result;
    }

    /**
     * Unwraps this decorator (and all nested decorators) all the way down to the undecorated
     * component at its core
     */
    public BaseCalculationStage<T> unwrap() {
        BaseCalculationStage<T> innermostCalculationStage = this;
        while (innermostCalculationStage instanceof BaseCalculationStageDecorator) {
            innermostCalculationStage =
                    ((BaseCalculationStageDecorator<T>) innermostCalculationStage)
                            .calculationToBeDecorated;
        }
        return innermostCalculationStage;
    }

    public class BattleStageResult {
        private final T attacker;
        private final T defender;

        public BattleStageResult(T attacker, T defender) {
            this.attacker = attacker;
            this.defender = defender;
        }

        public T forRole(BattleRole role) {
            switch (role) {
                case ATTACKER:
                    return attacker;
                case DEFENDER:
                    return defender;
            }
            throw new IllegalArgumentException("Cannot get result for role \"BOTH\"!");
        }
    }
}
