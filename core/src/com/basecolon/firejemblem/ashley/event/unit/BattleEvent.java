package com.basecolon.firejemblem.ashley.event.unit;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.basecolon.firejemblem.ashley.component.unit.AttackingComponent;
import com.basecolon.firejemblem.ashley.component.unit.DefendingComponent;
import com.basecolon.firejemblem.ashley.component.unit.InventoryComponent;
import com.basecolon.firejemblem.constants.FireJemblem;
import com.basecolon.firejemblem.misc.battle.BattleRole;
import com.basecolon.firejemblem.misc.battle.precalculations_results.BattleCalculator;
import com.basecolon.firejemblem.misc.battle.precalculations_results.BattleStats;
import com.basecolon.firejemblem.misc.items.Weapon;

import java.util.Random;

public class BattleEvent implements EntityListener {
    public static final Random RNG = new Random(System.nanoTime());

    private Entity attacker;
    private Entity defender;

    /**
     * The entry point for this method. Gets the battle participants and related fields, does the
     * work, and cleans up after everything is done by removing the marker-components from the
     * participants.
     */
    @Override
    public void entityAdded(Entity entity) {
        initializeFields();
        doWork();
        removeMarkerComponentsFromBattlers();
    }

    private void doWork() {
        final BattleCalculator battleCalculator = new BattleCalculator(attacker, defender);
        final BattleStats stats = battleCalculator.getStats();

        final Integer distanceBetween = stats.distanceBetween.forRole(BattleRole.ATTACKER);
        final Weapon attackerWeapon =
                attacker.getComponent(InventoryComponent.class).getEquippedWeapon();
        final Weapon defenderWeapon =
                defender.getComponent(InventoryComponent.class).getEquippedWeapon();

        BattleRole currentRole = BattleRole.ATTACKER;

        if (attackerWeapon.canHitAtRange(distanceBetween)) {
            final Integer hitChance = stats.hit.forRole(currentRole);
            if (rngSuccess(hitChance, TrueHit.ENABLED)) {
                final Integer critChance = stats.crit.forRole(currentRole);
                final int hitMultiplier;
                if (rngSuccess(critChance, TrueHit.DISABLED)) {
                    hitMultiplier = 3;
                } else {
                    hitMultiplier = 1;
                }

            }
        }
    }

    /**
     * Tests a given value against the RNG to see if it is a success or not.
     * Ex: Pass in the result of a calculation that determined that the user has a 74% hit-rate. If
     * the RNG generates between 0 and 73, this will return true. If the RNG generates 74 or higher,
     * this will return false.
     *
     * @param chanceOfSuccess The value to test against the RNG
     * @param trueHit whether or not to use the "true hit" system (FE6+ use this when calculating
     * hit %)
     * @return true if success, false if not
     */
    private static boolean rngSuccess(int chanceOfSuccess, TrueHit trueHit) {
        if (chanceOfSuccess <= 0) { return false; }
        if (chanceOfSuccess >= 100) { return true; }

        int generated = RNG.nextInt(100);
        if (trueHit.isEnabled()) {
            // "True hit" involves getting 2 random numbers and averaging them for a result
            generated = (generated + RNG.nextInt(100)) / 2;
        }
        return generated < chanceOfSuccess;
    }

    @Override
    public void entityRemoved(Entity entity) {}

    @SuppressWarnings("unchecked")
    private void initializeFields() {
        attacker = FireJemblem.engine.getEntitiesFor(
                Family.all(AttackingComponent.class).get()).first();
        defender = FireJemblem.engine.getEntitiesFor(
                Family.all(DefendingComponent.class).get()).first();
    }

    private void removeMarkerComponentsFromBattlers() {
        attacker.remove(AttackingComponent.class);
        defender.remove(DefendingComponent.class);
    }

    private static enum TrueHit {
        ENABLED(true),
        DISABLED(false);

        private final boolean enabled;

        TrueHit(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }
    }

    private static class BattleResults {
        
    }
}
