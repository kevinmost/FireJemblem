package com.basecolon.FireJemblem.ashley.system.unit;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.basecolon.FireJemblem.ashley.component.unit.AttackingComponent;
import com.basecolon.FireJemblem.ashley.component.unit.DefendingComponent;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.FireJemblem.misc.helpers.EntityHelpers;
import com.basecolon.FireJemblem.misc.items.Weapon;

import java.util.Random;

import static com.basecolon.FireJemblem.misc.helpers.EntityHelpers.Mappers;


public class BattleSystem extends EntitySystem {
    private Engine engine;

    private static final Random rng = new Random(System.nanoTime());
    private static final Mappers unitComponents = EntityHelpers.mappersFor(new UnitEntityBuilder());

    private Entity attackingEntity;
    private Entity defendingEntity;
    private Weapon attackingEntityWeapon;
    private Weapon defendingEntityWeapon;
    private int attackSpeed;

    @Override
    public void update(float deltaTime) {
        if (refreshEntities()) {
            // TODO: Write me
        }

        cleanUp();
    }

    private void performCalculations() {

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

        // Set up entities that will be hit often
        attackingEntity = attackingEntities.first();
        defendingEntity = defendingEntities.first();

        attackingEntityWeapon = attackingEntity.getComponent(Inventory.class).getEquippedWeapon();
        defendingEntityWeapon = defendingEntity.getComponent(Inventory.class).getEquippedWeapon();

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

    public Weapon getAttackingEntityWeapon() {
        return attackingEntityWeapon;
    }

    public Weapon getDefendingEntityWeapon() {
        return defendingEntityWeapon;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    /**
     * Tests a given value against the RNG to see if it is a success or not.
     * Ex: Pass in the result of a calculation that determined that the user has a 74% hit-rate. If the RNG generates
     * between 0 and 73, this will return true. If the RNG generates 74 or higher, this will return false.ad
     * @param calculationResult The value to test against the RNG
     * @param trueHit whether or not to use the "true hit" system (FE6+ use this when calculating hit %)
     * @return true if success, false if not
     */
    private static boolean rngSuccess(int calculationResult, boolean trueHit) {
        int generated = rng.nextInt(100);
        if (trueHit) {
            // "True hit" involves getting 2 random numbers and averaging them for a result
            generated = (generated + rng.nextInt(100)) / 2;
        }
        return generated < calculationResult;
    }
}