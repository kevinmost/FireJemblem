package com.basecolon.firejemblem.ashley.event.unit;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.basecolon.firejemblem.ashley.component.unit.AttackingComponent;
import com.basecolon.firejemblem.ashley.component.unit.DefendingComponent;
import com.basecolon.firejemblem.ashley.component.unit.InventoryComponent;
import com.basecolon.firejemblem.constants.FireJemblem;
import com.basecolon.firejemblem.misc.items.Weapon;

import java.util.Random;

public class BattleEvent implements EntityListener {
    public static final Random RNG = new Random(System.nanoTime());

    private Entity attacker;
    private Entity defender;
    private Weapon attackerWeapon;
    private Weapon defenderWeapon;

    /**
     * The entry point for this method. Gets the battle participants and related fields, does the work, and cleans up
     * after everything is done by removing the marker-components from the participants.
     */
    @Override
    public void entityAdded(Entity entity) {
        initializeFields();
        doWork();
        removeMarkerComponentsFromBattlers();
    }


    private void doWork() {

    }

    /**
     * Tests a given value against the RNG to see if it is a success or not.
     * Ex: Pass in the result of a calculation that determined that the user has a 74% hit-rate. If the RNG generates
     * between 0 and 73, this will return true. If the RNG generates 74 or higher, this will return false.
     * @param chanceOfSuccess The value to test against the RNG
     * @param useTrueHit whether or not to use the "true hit" system (FE6+ use this when calculating hit %)
     * @return true if success, false if not
     */
    public static boolean rngSuccess(int chanceOfSuccess, boolean useTrueHit) {
        if (chanceOfSuccess <= 0) return false;
        if (chanceOfSuccess >= 100) return true;

        int generated = RNG.nextInt(100);
        if (useTrueHit) {
            // "True hit" involves getting 2 random numbers and averaging them for a result
            generated = (generated + RNG.nextInt(100)) / 2;
        }
        return generated < chanceOfSuccess;
    }





    @Override
    public void entityRemoved(Entity entity) {}

    @SuppressWarnings("unchecked")
    private void initializeFields() {
        attacker = FireJemblem.engine.getEntitiesFor(Family.all(AttackingComponent.class).get()).first();
        defender = FireJemblem.engine.getEntitiesFor(Family.all(DefendingComponent.class).get()).first();
        attackerWeapon = attacker.getComponent(InventoryComponent.class).getEquippedWeapon();
        defenderWeapon = defender.getComponent(InventoryComponent.class).getEquippedWeapon();
    }

    private void removeMarkerComponentsFromBattlers() {
        attacker.remove(AttackingComponent.class);
        defender.remove(DefendingComponent.class);
    }
}
