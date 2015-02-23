package com.basecolon.firejemblem.ashley.system.unit;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.basecolon.firejemblem.ashley.component.PositionComponent;
import com.basecolon.firejemblem.ashley.component.unit.AttackingComponent;
import com.basecolon.firejemblem.ashley.component.unit.DefendingComponent;
import com.basecolon.firejemblem.ashley.component.unit.InventoryComponent;
import com.basecolon.firejemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.firejemblem.misc.helpers.EntityHelpers;
import com.basecolon.firejemblem.misc.items.Weapon;

import java.util.Random;

import static com.basecolon.firejemblem.misc.helpers.EntityHelpers.Mappers;


public class BattleSystem extends EntitySystem {
    private Engine engine;

    private static final Random RNG = new Random(System.nanoTime());
    private static final Mappers unitComponents = EntityHelpers.mappersFor(new UnitEntityBuilder());

    private Entity attackingEntity;
    private Entity defendingEntity;
    private Weapon attackingEntityWeapon;
    private Weapon defendingEntityWeapon;
    private int attackSpeed;
    private int distanceBetween;

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

        attackingEntityWeapon = attackingEntity.getComponent(InventoryComponent.class).getEquippedWeapon();
        defendingEntityWeapon = defendingEntity.getComponent(InventoryComponent.class).getEquippedWeapon();

        PositionComponent attackingPosition = attackingEntity.getComponent(PositionComponent.class);
        PositionComponent defendingPosition = defendingEntity.getComponent(PositionComponent.class);
        distanceBetween = Math.abs(attackingPosition.x - defendingPosition.x) +
                Math.abs(attackingPosition.y - defendingPosition.y);

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

    public int getDistanceBetween() {
        return distanceBetween;
    }

    /**
     * Tests a given value against the RNG to see if it is a success or not.
     * Ex: Pass in the result of a calculation that determined that the user has a 74% hit-rate. If the RNG generates
     * between 0 and 73, this will return true. If the RNG generates 74 or higher, this will return false.ad
     * @param calculationResult The value to test against the RNG
     * @param useTrueHit whether or not to use the "true hit" system (FE6+ use this when calculating hit %)
     * @return true if success, false if not
     */
    public static boolean rngSuccess(int calculationResult, boolean useTrueHit) {
        int generated = RNG.nextInt(100);
        if (useTrueHit) {
            // "True hit" involves getting 2 random numbers and averaging them for a result
            generated = (generated + RNG.nextInt(100)) / 2;
        }
        return generated < calculationResult;
    }
}