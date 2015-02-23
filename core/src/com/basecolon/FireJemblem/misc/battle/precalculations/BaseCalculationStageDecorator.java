package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.misc.battle.BattleRole;

public abstract class BaseCalculationStageDecorator<RESULT_TYPE>
        extends BaseCalculationStage<RESULT_TYPE> {

    protected BaseCalculationStage<RESULT_TYPE> calculationToBeDecorated;
    protected BattleRole decoratorOwner;

    /**
     * WARNING: When using this constructor, {@code calculationToBeDecorated} is not set. You must
     * use all of the setter methods exposed on this class to instantiate it properly before
     * performing any calculations using this or an NPE will result.
     */
    public BaseCalculationStageDecorator() {}

    public void setData(
            BaseCalculationStage<RESULT_TYPE> calculationToBeDecorated) {
        super.data = calculationToBeDecorated.data;
        this.calculationToBeDecorated = calculationToBeDecorated;
    }

    public void setDecoratorOwner(final BattleRole decoratorOwner) {
        this.decoratorOwner = decoratorOwner;
    }

    public abstract Class<? extends BaseCalculationStage<RESULT_TYPE>> getCalculationToBeDecorated();

    /**
     * Unwraps this decorator (and all nested decorators) all the way down to the undecorated
     * component at its core
     */
    public BaseCalculationStage<RESULT_TYPE> unwrap() {
        BaseCalculationStage<RESULT_TYPE> innermostCalculationStage = this.calculationToBeDecorated;
        while (innermostCalculationStage instanceof BaseCalculationStageDecorator) {
            innermostCalculationStage =
                    ((BaseCalculationStageDecorator<RESULT_TYPE>) innermostCalculationStage)
                            .calculationToBeDecorated;
        }
        return innermostCalculationStage;
    }
}
