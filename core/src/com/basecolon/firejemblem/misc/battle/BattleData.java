package com.basecolon.firejemblem.misc.battle;

import com.badlogic.ashley.core.Entity;
import com.basecolon.firejemblem.ashley.component.PositionComponent;
import com.basecolon.firejemblem.ashley.component.unit.*;
import com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.firejemblem.constants.component.world.TileConstants;
import com.basecolon.firejemblem.misc.battle.precalculations.BaseCalculationStage;
import com.basecolon.firejemblem.misc.battle.precalculations.BaseCalculationStageDecorator;
import com.basecolon.firejemblem.misc.items.Weapon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that holds data about the combatants. This class is provided for convenience so that any {@link com.basecolon.firejemblem.misc.battle.precalculations.BaseCalculationStage}
 * can access the data that it needs.
 */
public class BattleData {

    private final Map<BattleRole, BattleDataCharacter> characters = new HashMap<>();

    /**
     * This map contains the results of previous stages of the battle that were computed
     */
    private Map<Class<? extends BaseCalculationStage>, BaseCalculationStage> stageResults = new HashMap<>();

    public BattleData(Entity attacker, Entity defender) {
        characters.put(BattleRole.ATTACKER, new BattleDataCharacter(attacker));
        characters.put(BattleRole.DEFENDER, new BattleDataCharacter(defender));
    }


    public BattleDataCharacter get(BattleRole role) {
        return characters.get(role);
    }


    @SuppressWarnings("unchecked")
    public <R, S extends BaseCalculationStage<R>> BaseCalculationStage<R>.BattleStageResult getResultOfPreviousStage(Class<S> stage) {
        return stageResults.get(stage).getResult();
    }

    public <S extends BaseCalculationStage> BattleData addStage(S stage) {
        /*
         * If the stage is a decorator, we need to record it in the results under the class of
         * its undecorated equivalent, so other code that is not aware of the fact that the stage
         * was decorated will be able to find it in the map.
         */
        final Class<? extends BaseCalculationStage> stageClass;
        if (stage instanceof BaseCalculationStageDecorator) {
            stageClass = stage.unwrap().getClass();
        } else {
            stageClass = stage.getClass();
        }
        this.stageResults.put(stageClass, stage);
        return this;
    }


    public static class BattleDataCharacter {
        public final Weapon equippedWeapon;
        public final UnitStatsComponent stats;
        public final UnitWeaponProficiency weaponProficiency;
        public final ClassTypes unitClass;
        public final PositionComponent position;
        public final TileConstants tileStandingOn;
        public final List<BaseCalculationStageDecorator<?>> decorators;

        public BattleDataCharacter(Entity entity) {
            this.equippedWeapon = entity.getComponent(InventoryComponent.class).getEquippedWeapon();
            this.stats = entity.getComponent(UnitStatsComponent.class);
            this.weaponProficiency = entity.getComponent(UnitWeaponProficiency.class);
            this.unitClass = entity.getComponent(UnitClassComponent.class).unitClass;
            this.position = entity.getComponent(PositionComponent.class);
            this.tileStandingOn = position.getTileTypeAtLocation();
            this.decorators = entity.getComponent(DecoratorComponent.class).decorators;
        }
    }
}
