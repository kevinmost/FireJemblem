package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;

import java.util.Random;


public class BattleSystem extends EntitySystem {
    private Engine engine;

    private static final Random rng = new Random(System.nanoTime());
    private static final ComponentMapper<UnitStats> stats = ComponentMapper.getFor(UnitStats.class);
    private static final ComponentMapper<Inventory> items = ComponentMapper.getFor(Inventory.class);

    /**
     * True if a battle needs to be processed in the next update iteration, false otherwise
     */
    private boolean queuedBattle;
    private Entity attackingEntity;
    private Entity defendingEntity;

    public void battleBetween(Entity attackingEntity, Entity defendingEntity) {
        this.attackingEntity = attackingEntity;
        this.defendingEntity = defendingEntity;
        this.queuedBattle = true;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        if (!queuedBattle) return;

        queuedBattle = false;

        new BattleSystemCalculations(attackingEntity, defendingEntity);
    }


    /**
     * The meat of the battle calculations will go on in here. This is going to end up as messy spaghetti-code,
     * thanks to some strange weapons like the Runesword, Light Brand, Eclipse, etc. and will probably just look like
     * a procedurally-written nightmare with a bunch of if-clauses. There might be a way to clean this up later.
     * Some things haven't been implemented, such as supports and tactician bonuses, and may never be implemented.
     */
    class BattleSystemCalculations {

        private final Entity attackingEntity;
        private final Entity defendingEntity;


        public BattleSystemCalculations(Entity attackingEntity, Entity defendingEntity) {
            this.attackingEntity = attackingEntity;
            this.defendingEntity = defendingEntity;
        }

        public void performBattle() {

        }
    }


}
