package com.basecolon.FireJemblem.constants.component.item.weapon;

import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.unit.UnitClass;
import com.basecolon.FireJemblem.ashley.system.unit.BattleSystem;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;

import static com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels.D;
import static com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels.E;
import static com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels.PRF;
import static com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes.AXE;
import static com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes.SWORD;
import static com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes.*;
import static com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes.EffectiveDamageGroups.ARMORED;
import static com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes.EffectiveDamageGroups.MOUNTED;

// TODO: Implement all the weapons and put them in a readable order
public enum PhysicalWeaponTemplate implements WeaponTemplate {

    // *** SWORDS ***
    @WeaponStats(name = "Iron Sword", type = SWORD, level = E, durability = 46, minRange = 1, maxRange = 1,
            weight = 5, might = 5, hit = 90, crit = 0, spritePath = "")
    IRON_SWORD,

    @WeaponStats(name = "Slim Sword", type = SWORD, level = E, durability = 30, minRange = 1, maxRange = 2,
            weight = 5, might = 5, hit = 90, crit = 0, spritePath = "")
    SLIM_SWORD,

    @WeaponStats(name = "Emblem Blade", type = SWORD, level = E, durability = 60, minRange = 1, maxRange = 1,
            weight = 5, might = 5, hit = 90, crit = 0, spritePath = "")
    EMBLEM_BLADE,

    @WeaponStats(name = "Poison Sword", type = SWORD, level = D, durability = 40, minRange = 1, maxRange = 1,
            weight = 6, might = 3, hit = 70, crit = 0, spritePath = "",
            infotext = "Inflicts Poison upon contact")
    POISON_SWORD {
        @Override
        public int onHit(BattleSystem calculations) {
            int toReturn = super.onHit(calculations);
            // TODO: Implement poison as a component so we can inflict it here upon the defender
            calculations.getDefendingEntity();
            return toReturn;
        }
    },

    @WeaponStats(name = "Wo Dao", type = SWORD, level = D, durability = 20, minRange = 1, maxRange = 2,
            weight = 5, might = 8, hit = 75, crit = 35, spritePath = "",
            infotext = "Only Myrmidons, Swordmasters, and Blade Lords may wield")
    WO_DAO {
        @Override
        public boolean canBeWieldedBy(Entity unit) {
            ClassTypes unitClass = unit.getComponent(UnitClass.class).unitClass;
            return unitClass == MYRMIDON || unitClass == SWORDMASTER || unitClass == BLADE_LORD;
        }
    },

    @WeaponStats(name = "Mani Katti", type = SWORD, level = PRF, durability = 45, minRange = 1, maxRange = 2,
            weight = 3, might = 8, hit = 80, crit = 20, effectiveAgainst = {ARMORED, MOUNTED}, spritePath = "",
            infotext = "Only Lyn may wield. Bonus damage to horseback and armored units.")
    MANI_KATTI {
        @Override
        public boolean canBeWieldedBy(Entity unit) {
            ClassTypes unitClass = unit.getComponent(UnitClass.class).unitClass;
            return unitClass == LORD_LYN || unitClass == BLADE_LORD;
        }
    },

    // *** SWORDS ***


    // *** LANCES ***

    // *** LANCES ***



    // *** AXES ***

    @WeaponStats(name = "Hand Axe", type = AXE, level = E, durability = 20, minRange = 1, maxRange = 2,
            weight = 12, might = 7, hit = 60, crit = 0, spritePath = "")
    HAND_AXE,

    // *** AXES ***
    ;


    private WeaponStats stats;

    PhysicalWeaponTemplate() {
        try {
            this.stats = this.getClass().getField(this.name()).getAnnotation(WeaponStats.class);
        } catch (NoSuchFieldException e) {
            // this.name has to exist because it references the enum whose constructor we are already inside
            // right now so we can just catch this and throw it as a RuntimeException because it should never happen
            throw new RuntimeException("ERROR: " + this.name() + " is not implemented properly!");
        }
    }

    @Override
    public WeaponStats getStats() {
        return stats;
    }

    @Override
    public int calculateDefense(BattleSystem calculations) {
        // TODO: Write me
        return 0;
    }
}
