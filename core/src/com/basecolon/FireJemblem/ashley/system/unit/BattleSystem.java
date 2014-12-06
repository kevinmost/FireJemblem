package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.basecolon.FireJemblem.ashley.component.unit.AttackingComponent;
import com.basecolon.FireJemblem.ashley.component.unit.DefendingComponent;
import com.basecolon.FireJemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.FireJemblem.misc.helpers.EntityHelpers;

import java.util.Random;

import static com.basecolon.FireJemblem.misc.helpers.EntityHelpers.Mappers;


public class BattleSystem extends EntitySystem {
    private Engine engine;

    private static final Random rng = new Random(System.nanoTime());
    private static final Mappers unitComponents = EntityHelpers.mappersFor(new UnitEntityBuilder());

    private Entity attackingEntity;
    private Entity defendingEntity;

    @Override
    public void update(float deltaTime) {
        if (refreshEntities()) {
        }

        cleanUp();
    }


    /**
     * Gets the currently attacking and defending units. {@return true} if everything went okay and we have two units
     * to process a battle event for.
     */
    @SuppressWarnings("unchecked")
    private boolean refreshEntities() {
        ImmutableArray<Entity> attackingEntities = engine.getEntitiesFor(Family.all(AttackingComponent.class).get());
        ImmutableArray<Entity> defendingEntities = engine.getEntitiesFor(Family.all(DefendingComponent.class).get());
        if (attackingEntities.size() != 1 || defendingEntities.size() != 1) {
            // TODO: We have a problem if we hit this point, what should we do?
            return false;
        }

        this.attackingEntity = attackingEntities.first();
        this.defendingEntity = defendingEntities.first();

        return true;
    }

    /**
     * Removes the marker-components from the attacking and defending entities, and then shuts down this system.
     */
    private void cleanUp() {
        attackingEntity.remove(AttackingComponent.class);
        defendingEntity.remove(DefendingComponent.class);
        this.setProcessing(false);
    }

    public Entity getAttackingEntity() {
        return attackingEntity;
    }

    public Entity getDefendingEntity() {
        return defendingEntity;
    }

}