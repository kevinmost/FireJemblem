package com.basecolon.firejemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;
import com.basecolon.firejemblem.misc.battle.BattleRole;
import com.basecolon.firejemblem.misc.battle.BattleRolePerspective;
import com.basecolon.firejemblem.misc.battle.precalculations.BaseCalculationStage;
import com.basecolon.firejemblem.misc.battle.precalculations.BaseCalculationStageDecorator;

import java.util.ArrayList;
import java.util.List;

public class DecoratorComponent extends Component {

    public final List<BaseCalculationStageDecorator<?>> decorators = new ArrayList<>();


    public static class Decorator {
        public final BaseCalculationStageDecorator<?> decorator;
        public final Class<? extends BaseCalculationStage<?>> stage;
        public final BattleRolePerspective who;
        public final BattleRole asRole;

        public Decorator(BaseCalculationStageDecorator<?> decorator, Class<? extends BaseCalculationStage<?>> stage, BattleRolePerspective who, BattleRole asRole) {
            this.decorator = decorator;
            this.stage = stage;
            this.who = who;
            this.asRole = asRole;
        }
    }
}
