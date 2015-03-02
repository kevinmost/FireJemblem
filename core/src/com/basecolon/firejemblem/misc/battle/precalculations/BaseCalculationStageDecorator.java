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
    public BaseCalculationStageDecorator() {
        super(null);
    }

    public void setData(BaseCalculationStage<RESULT_TYPE> calculationToBeDecorated) {
        super.data = calculationToBeDecorated.data;
        this.calculationToBeDecorated = calculationToBeDecorated;
    }

    public void setDecoratorOwner(final BattleRole decoratorOwner) {
        this.decoratorOwner = decoratorOwner;
    }

    public abstract Class<? extends BaseCalculationStage<RESULT_TYPE>> getCalculationToBeDecorated();


}
