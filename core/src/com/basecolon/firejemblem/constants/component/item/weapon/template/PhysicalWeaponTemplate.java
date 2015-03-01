package com.basecolon.firejemblem.constants.component.item.weapon.template;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.basecolon.firejemblem.ashley.component.HealthComponent;
import com.basecolon.firejemblem.ashley.component.unit.ConditionComponent;
import com.basecolon.firejemblem.ashley.component.unit.UnitClassComponent;
import com.basecolon.firejemblem.ashley.component.unit.UnitStatsComponent;
import com.basecolon.firejemblem.ashley.component.unit.UnitStatsModComponent;
import com.basecolon.firejemblem.ashley.system.unit.BattleSystem;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponStats;
import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes;
import javafx.util.Pair;

import static com.basecolon.firejemblem.constants.component.item.weapon.WeaponProficiencyLevels.*;
import static com.basecolon.firejemblem.constants.component.item.weapon.WeaponTypes.*;
import static com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes.*;
import static com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes.EffectiveDamageGroups.*;


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
        public void onHit(BattleSystem calculations) {
            super.onHit(calculations);
            calculations.getDefendingEntity().add(new ConditionComponent(ConditionComponent.Condition.POISON));
        }
    },

    @WeaponStats(name = "Steel Sword", type = SWORD, level = D, durability = 30, minRange = 1, maxRange = 1,
            weight = 10, might = 8, hit = 75, crit = 0, spritePath = "")
    STEEL_SWORD,

    @WeaponStats(name = "Iron Blade", type = SWORD, level = D, durability = 35, minRange = 1, maxRange = 1,
            weight = 12, might = 9, hit = 70, crit = 0, spritePath = "")
    IRON_BLADE,

    @WeaponStats(name = "Armorslayer", type = SWORD, level = D, durability = 18, minRange = 1, maxRange = 1,
            weight = 11, might = 8, hit = 80, crit = 0, effectiveAgainst = {ARMORED}, spritePath = "",
            infotext = "Effective vs. Armored units.")
    ARMORSLAYER,

    @WeaponStats(name = "Longsword", type = SWORD, level = D, durability = 18, minRange = 1, maxRange = 1,
            weight = 11, might = 6, hit = 85, crit = 0, effectiveAgainst = {MOUNTED}, spritePath = "",
            infotext = "Effective vs. Horseback units.")
    LONGSWORD,

    @WeaponStats(name = "Wo Dao", type = SWORD, level = D, durability = 20, minRange = 1, maxRange = 2,
            weight = 5, might = 8, hit = 75, crit = 35, spritePath = "",
            infotext = "Only Myrmidons, Swordmasters, and Blade Lords may wield")
    WO_DAO {
        /**
         * Only for myrmidons, swordmasters, and blade-lords
         */
        @Override
        public boolean canBeWieldedBy(Entity unit) {
            ClassTypes unitClass = unit.getComponent(UnitClassComponent.class).unitClass;
            return (unitClass == MYRMIDON || unitClass == SWORDMASTER || unitClass == BLADE_LORD) && super.canBeWieldedBy(unit);
        }
    },

    @WeaponStats(name = "Steel Blade", type = SWORD, level = C, durability = 25, minRange = 1, maxRange = 1,
            weight = 14, might = 11, hit = 65, crit = 0, spritePath = "")
    STEEL_BLADE,

    @WeaponStats(name = "Killing Edge", type = SWORD, level = C, durability = 20, minRange = 1, maxRange = 1,
            weight = 7, might = 9, hit = 75, crit = 30, spritePath = "")
    KILLING_EDGE,

    @WeaponStats(name = "Wyrmslayer", type = SWORD, level = C, durability = 20, minRange = 1, maxRange = 1,
            weight = 5, might = 7, hit = 75, crit = 0, effectiveAgainst = {DRAGON}, spritePath = "",
            infotext = "Effective vs. Dragon units.")
    WYRMSLAYER,

    //TODO: Light Brand's effect needs to be implemented
    @WeaponStats(name = "Light Brand", type = SWORD, level = C, durability = 25, minRange = 1, maxRange = 2,
            weight = 9, might = 9, hit = 70, crit = 0, spritePath = "",
            infotext = "Targets Resistance. Inflicts magic-based damage at ranged.")
    LIGHT_BRAND {
        @Override
        public int calculateWeaponTriangleDamageBonus(BattleSystem calculations) {
            if (calculations.getDistanceBetween() == 1) {
                return super.calculateWeaponTriangleDamageBonus(calculations);
            }
            switch (LIGHT
                    .advantageAgainst(calculations.getDefendingEntityWeapon().getType())) {
                case ADVANTAGE:
                    return 1;
                case DISADVANTAGE:
                    return -1;
                default:
                    return 0;
            }
        }
        @Override
        public int calculateStr(BattleSystem calculations) {
            if (calculations.getDistanceBetween() == 1) {
                return super.calculateStr(calculations);
            }
            return super.calculateStr(calculations) / 2;
        }
        @Override
        public int calculateDefense(BattleSystem calculations) {
            return calculations.getDefendingEntity().getComponent(UnitStatsComponent.class).get(UnitStatLabels.RESISTANCE);
        }
        @Override
        public int getCritAccuracy(BattleSystem calculations) {
            if (calculations.getDistanceBetween() == 1) {
                return 0;
            }
            return super.getCritAccuracy(calculations);
        }
    },

    //TODO: Reaver ability needs to be implemented
    @WeaponStats(name = "Lancereaver", type = SWORD, level = C, durability = 15, minRange = 1, maxRange = 1,
            weight = 9, might = 9, hit = 75, crit = 5, spritePath = "",
            infotext = "Strong vs. Lances. Weak vs. Axes.")
    LANCEREAVER,

    //TODO: Brave ability needs to be implemented
    @WeaponStats(name = "Brave Sword", type = SWORD, level = B, durability = 30, minRange = 1, maxRange = 1,
            weight = 12, might = 9, hit = 75, crit = 0, spritePath = "",
            infotext = "Allows user to strike twice in one attack.")
    BRAVE_SWORD,

    // TODO: Testing
    @WeaponStats(name = "Wind Sword", type = SWORD, level = B, durability = 40, minRange = 1, maxRange = 2,
            weight = 9, might = 9, hit = 70, crit = 0, spritePath = "",
            infotext = "Targets Resistance. Inflicts Wind magic damage. Effective vs. flying units.")
    WIND_SWORD {
        @Override
        public int calculateWeaponTriangleDamageBonus(BattleSystem calculations) {
            if (calculations.getDistanceBetween() == 1) {
                return super.calculateWeaponTriangleDamageBonus(calculations);
            }
            switch (ANIMA
                    .advantageAgainst(calculations.getDefendingEntityWeapon().getType())) {
                case ADVANTAGE:
                    return 1;
                case DISADVANTAGE:
                    return -1;
                default:
                    return 0;
            }
        }
        @Override
        public int calculateStr(BattleSystem calculations) {
            if (calculations.getDistanceBetween() == 1) {
                return super.calculateStr(calculations);
            }
            return super.calculateStr(calculations) / 2;
        }
        @Override
        public int calculateDefense(BattleSystem calculations) {
            return calculations.getDefendingEntity().getComponent(UnitStatsComponent.class).get(UnitStatLabels.RESISTANCE);
        }
        @Override
        public int getCritAccuracy(BattleSystem calculations) {
            if (calculations.getDistanceBetween() == 1) {
                return 0;
            }
            return super.getCritAccuracy(calculations);
        }
    },

    @WeaponStats(name = "Silver Sword", type = SWORD, level = B, durability = 20, minRange = 1, maxRange = 1,
            weight = 8, might = 13, hit = 80, crit = 0, spritePath = "")
    SILVER_SWORD,

    @WeaponStats(name = "Silver Blade", type = SWORD, level = A, durability = 15, minRange = 1, maxRange = 1,
            weight = 13, might = 14, hit = 60, crit = 0, spritePath = "")
    SILVER_BLADE,

    // TODO: Testing
    @WeaponStats(name = "Runesword", type = SWORD, level = A, durability = 15, minRange = 1, maxRange = 2,
            weight = 11, might = 12, hit = 65, crit = 0, spritePath = "",
            infotext = "Targets Resistance. Inflicts Dark magic damage. Restores HP to user equal to damage dealt.")
    RUNESWORD {
        @Override
        public int calculateWeaponTriangleDamageBonus(BattleSystem calculations) {
            switch (DARK
                    .advantageAgainst(calculations.getDefendingEntityWeapon().getType())) {
                case ADVANTAGE:
                    return 1;
                case DISADVANTAGE:
                    return -1;
                default:
                    return 0;
            }
        }
        @Override
        public int calculateStr(BattleSystem calculations) {
            return super.calculateStr(calculations) / 2;
        }
        @Override
        public int calculateDefense(BattleSystem calculations) {
            return calculations.getDefendingEntity().getComponent(UnitStatsComponent.class).get(UnitStatLabels.RESISTANCE);
        }
        @Override
        public int getCritAccuracy(BattleSystem calculations) {
            return 0;
        }

        @Override
        public void onHit(BattleSystem calculations) {
            super.onHit(calculations);
            calculations.getAttackingEntity().getComponent(HealthComponent.class).increaseBy(0);
            // TODO: Implement
        }
    },

    @WeaponStats(name = "Regal Blade", type = SWORD, level = S, durability = 25, minRange = 1, maxRange = 1,
            weight = 9, might = 20, hit = 85, crit = 0, spritePath = "")
    REGAL_BLADE,

    @WeaponStats(name = "Mani Katti", type = SWORD, level = PRF, durability = 45, minRange = 1, maxRange = 2,
            weight = 3, might = 8, hit = 80, crit = 20, effectiveAgainst = {ARMORED, MOUNTED}, spritePath = "",
            infotext = "Only Lyn may wield. Bonus damage to horseback and armored units.")
    MANI_KATTI {
        @Override
        public boolean canBeWieldedBy(Entity unit) {
            ClassTypes unitClass = unit.getComponent(UnitClassComponent.class).unitClass;
            return unitClass == LORD_LYN || unitClass == BLADE_LORD;
        }
    },

    @WeaponStats(name = "Rapier", type = SWORD, level = PRF, durability = 40, minRange = 1, maxRange = 1,
            weight = 5, might = 7, hit = 95, crit = 10, effectiveAgainst = {ARMORED, MOUNTED}, spritePath = "",
            infotext = "Eliwood only. Effective vs. Armored/Mounted units.")
    RAPIER {
        @Override
        public boolean canBeWieldedBy(Entity unit) {
            ClassTypes unitClass = unit.getComponent(UnitClassComponent.class).unitClass;
            return unitClass == LORD_ELIWOOD || unitClass == KNIGHT_LORD;
        }
    },

    //TODO: Implement stat increase
    @WeaponStats(name = "Durandal", type = SWORD, level = PRF, durability = 20, minRange = 1, maxRange = 1,
            weight = 16, might = 17, hit = 90, crit = 0, effectiveAgainst = {DRAGON}, spritePath = "",
            infotext = "Eliwood only. Strength +5. Effective vs. Dragon units.")
    DURANDAL {
        @Override
        public boolean canBeWieldedBy(Entity unit) {
            ClassTypes unitClass = unit.getComponent(UnitClassComponent.class).unitClass;
            return unitClass == LORD_ELIWOOD || unitClass == KNIGHT_LORD;
        }
        @Override
        public UnitStatsModComponent statModsOnEquip() {
            return new UnitStatsModComponent(new Pair<>(UnitStatLabels.STRENGTH, 5));
        }
    },

    @WeaponStats(name = "Sol Katti", type = SWORD, level = PRF, durability = 30, minRange = 1, maxRange = 1,
            weight = 14, might = 12, hit = 95, crit = 25, effectiveAgainst = {DRAGON}, spritePath = "",
            infotext = "Lyn only. Resistance +5. Effective vs. Dragon units.")
    SOL_KATTI {
        @Override
        public boolean canBeWieldedBy(Entity unit) {
            ClassTypes unitClass = unit.getComponent(UnitClassComponent.class).unitClass;
            return unitClass == LORD_LYN || unitClass == BLADE_LORD;
        }
        @Override
        public UnitStatsModComponent statModsOnEquip() {
            return new UnitStatsModComponent(new Pair<>(UnitStatLabels.RESISTANCE, 5));
        }
    },
    // *** SWORDS ***


    // *** LANCES ***
    @WeaponStats(name = "Iron Lance", type = LANCE, level = E, durability = 45, minRange = 1, maxRange = 1,
            weight = 8, might = 7, hit = 80, crit = 0, spritePath = "")
    IRON_LANCE,

    @WeaponStats(name = "Slim Lance", type = LANCE, level = E, durability = 30, minRange = 1, maxRange = 1,
            weight = 4, might = 4, hit = 85, crit = 5, spritePath = "")
    SLIM_LANCE,

    @WeaponStats(name = "Javelin", type = LANCE, level = E, durability = 20, minRange = 1, maxRange = 2,
            weight = 11, might = 6, hit = 65, crit = 0, spritePath = "")
    JAVELIN,

    @WeaponStats(name = "Emblem Lance", type = LANCE, level = E, durability = 60, minRange = 1, maxRange = 1,
            weight = 8, might = 7, hit = 80, crit = 0, spritePath = "")
    EMBLEM_LANCE,

    @WeaponStats(name = "Poison Lance", type = LANCE, level = E, durability = 40, minRange = 1, maxRange = 1,
            weight = 8, might = 4, hit = 60, crit = 0, spritePath = "", infotext = "Inflicts Poison upon contact.")
    POISON_LANCE {
        @Override
        public void onHit(BattleSystem calculations) {
            super.onHit(calculations);
            calculations.getDefendingEntity().add(new ConditionComponent(ConditionComponent.Condition.POISON));
        }
    },

    @WeaponStats(name = "Steel Lance", type = LANCE, level = D, durability = 30, minRange = 1, maxRange = 1,
            weight = 13, might = 10, hit = 70, crit = 0, spritePath = "")
    STEEL_LANCE,

    @WeaponStats(name = "Heavy Spear", type = LANCE, level = D, durability = 16, minRange = 1, maxRange = 1,
            weight = 14, might = 9, hit = 70, crit = 0, effectiveAgainst = {ARMORED}, spritePath = "",
            infotext = "Effective vs. Armored units.")
    HEAVY_SPEAR,

    @WeaponStats(name = "Horseslayer", type = LANCE, level = D, durability = 16, minRange = 1, maxRange = 1,
            weight = 13, might = 7, hit = 70, crit = 0, effectiveAgainst = {MOUNTED}, spritePath = "",
            infotext = "Effective vs. Horseback units.")
    HORSESLAYER,

    @WeaponStats(name = "Short Spear", type = LANCE, level = C, durability = 18, minRange = 1, maxRange = 2,
            weight = 12, might = 9, hit = 60, crit = 0, spritePath = "")
    SHORT_SPEAR,

    @WeaponStats(name = "Killer Lance", type = LANCE, level = C, durability = 20, minRange = 1, maxRange = 1,
            weight = 9, might = 10, hit = 70, crit = 30, spritePath = "")
    KILLER_LANCE,

    @WeaponStats(name = "Axereaver", type = LANCE, level = C, durability = 15, minRange = 1, maxRange = 1,
            weight = 11, might = 10, hit = 70, crit = 5, spritePath = "", infotext = "Strong vs. Axes. Weak vs. Swords.")
    AXEREAVER {
        @Override
        public int calculateWeaponTriangleDamageBonus(BattleSystem calculations) {
            return -2 * super.calculateWeaponTriangleDamageBonus(calculations);
        }
        @Override
        public int calculateWeaponTriangleAccuracyBonus(BattleSystem calculations) {
            return -2 * super.calculateWeaponTriangleAccuracyBonus(calculations);
        }
    },

    //TODO: Brave ability needs to be implemented
    @WeaponStats(name = "Brave Lance", type = LANCE, level = B, durability = 30, minRange = 1, maxRange = 1,
            weight = 14, might = 10, hit = 70, crit = 0, spritePath = "", infotext = "Allows user to strike twice in one attack.")
    BRAVE_LANCE,

    @WeaponStats(name = "Spear", type = LANCE, level = B, durability = 15, minRange = 1, maxRange = 2,
            weight = 10, might = 12, hit = 70, crit = 5, spritePath = "")
    SPEAR,

    @WeaponStats(name = "Silver Lance", type = LANCE, level = A, durability = 20, minRange = 1, maxRange = 1,
            weight = 10, might = 14, hit = 75, crit = 0, spritePath = "")
    SILVER_LANCE,

    @WeaponStats(name = "Rex Hasta", type = LANCE, level = S, durability = 25, minRange = 1, maxRange = 1,
            weight = 11, might = 21, hit = 80, crit = 0, spritePath = "")
    REX_HASTA,
    // *** LANCES ***



    // *** AXES ***
    @WeaponStats(name = "Hand Axe", type = AXE, level = E, durability = 20, minRange = 1, maxRange = 2,
            weight = 12, might = 7, hit = 60, crit = 0, spritePath = "")
    HAND_AXE,

    @WeaponStats(name = "Iron Axe", type = AXE, level = E, durability = 45, minRange = 1, maxRange = 1,
            weight = 10, might = 8, hit = 75, crit = 0, spritePath = "")
    IRON_AXE,

    @WeaponStats(name = "Emblem Axe", type = AXE, level = E, durability = 60, minRange = 1, maxRange = 1,
            weight = 10, might = 8, hit = 75, crit = 0, spritePath = "")
    EMBLEM_AXE,

    @WeaponStats(name = "Steel Axe", type = AXE, level = E, durability = 30, minRange = 1, maxRange = 1,
            weight = 15, might = 11, hit = 65, crit = 0, spritePath = "")
    STEEL_AXE,

    //TODO: Devil ability needs to be implemented, shouldn't be difficult
    @WeaponStats(name = "Devil Axe", type = AXE, level = E, durability = 20, minRange = 1, maxRange = 1,
            weight = 18, might = 18, hit = 55, crit = 0, spritePath = "",
            infotext = "May inflict damage to the user instead when attacking.")
    DEVIL_AXE {
    },

    @WeaponStats(name = "Poison Axe", type = AXE, level = D, durability = 40, minRange = 1, maxRange = 1,
            weight = 10, might = 4, hit = 60, crit = 0, spritePath = "",
            infotext = "Inflicts Poison upon contact.")
    POISON_AXE {
        @Override
        public void onHit(BattleSystem calculations) {
            super.onHit(calculations);
            calculations.getDefendingEntity().add(new ConditionComponent(ConditionComponent.Condition.POISON));
        }
    },

    @WeaponStats(name = "Halberd", type = AXE, level = D, durability = 18, minRange = 1, maxRange = 1,
            weight = 15, might = 10, hit = 60, crit = 0, effectiveAgainst = {MOUNTED}, spritePath = "",
            infotext = "Effective vs. Horseback units.")
    HALBERD,

    @WeaponStats(name = "Hammer", type = AXE, level = D, durability = 20, minRange = 1, maxRange = 1,
            weight = 15, might = 10, hit = 55, crit = 0, effectiveAgainst = {ARMORED}, spritePath = "",
            infotext = "Effective vs. Armored units.")
    HAMMER,

    @WeaponStats(name = "Dragon Axe", type = AXE, level = C, durability = 40, minRange = 1, maxRange = 1,
            weight = 11, might = 10, hit = 60, crit = 0, spritePath = "",
            infotext = "Effective vs. Dragon units.")
    DRAGON_AXE,

    @WeaponStats(name = "Killer Axe", type = AXE, level = C, durability = 20, minRange = 1, maxRange = 1,
            weight = 11, might = 11, hit = 65, crit = 30, spritePath = "")
    KILLER_AXE,

    //TODO: Reaver ability needs to be implemented
    @WeaponStats(name = "Swordreaver", type = AXE, level = C, durability = 15, minRange = 1, maxRange = 1,
            weight = 13, might = 11, hit = 65, crit = 5, spritePath = "",
            infotext = "Strong vs. Swords. Weak vs. Lances.")
    SWORDREAVER {
        @Override
        public int calculateWeaponTriangleDamageBonus(BattleSystem calculations) {
            return -2 * super.calculateWeaponTriangleDamageBonus(calculations);
        }
        @Override
        public int calculateWeaponTriangleAccuracyBonus(BattleSystem calculations) {
            return -2 * super.calculateWeaponTriangleAccuracyBonus(calculations);
        }
    },

    //TODO: Reaver ability needs to be implemented
    @WeaponStats(name = "Swordslayer", type = AXE, level = C, durability = 20, minRange = 1, maxRange = 1,
            weight = 13, might = 11, hit = 80, crit = 5, effectiveAgainst = {SWORDS}, spritePath = "",
            infotext = "Strong vs. Swords. Weak vs Lances. Effective vs. Mercenaries/Heroes/Myrmidons/Swordmasters/Blade Lords.")
    SWORDSLAYER {
        @Override
        public int calculateWeaponTriangleDamageBonus(BattleSystem calculations) {
            return -2 * super.calculateWeaponTriangleDamageBonus(calculations);
        }
        @Override
        public int calculateWeaponTriangleAccuracyBonus(BattleSystem calculations) {
            return -2 * super.calculateWeaponTriangleAccuracyBonus(calculations);
        }
    },

    //TODO: Brave ability needs to be implemented
    @WeaponStats(name = "Brave Axe", type = AXE, level = B, durability = 30, minRange = 1, maxRange = 1,
            weight = 16, might = 10, hit = 65, crit = 0, spritePath = "",
            infotext = "Allows user to strike twice in one attack.")
    BRAVE_AXE,

    @WeaponStats(name = "Tomahawk", type = AXE, level = A, durability = 15, minRange = 1, maxRange = 2,
            weight = 14, might = 13, hit = 65, crit = 0, spritePath = "")
    TOMAHAWK,

    @WeaponStats(name = "Silver Axe", type = AXE, level = A, durability = 20, minRange = 1, maxRange = 1,
            weight = 12, might = 15, hit = 70, crit = 0, spritePath = "")
    SILVER_AXE,

    @WeaponStats(name = "Basilikos", type = AXE, level = S, durability = 25, minRange = 1, maxRange = 1,
            weight = 13, might = 22, hit = 75, crit = 0, spritePath = "")
    BASILIKOS,

    @WeaponStats(name = "Wolf Beil", type = AXE, level = PRF, durability = 30, minRange = 1, maxRange = 1,
            weight = 10, might = 10, hit = 75, crit = 5, effectiveAgainst = {ARMORED, MOUNTED}, spritePath = "",
            infotext = "Hector only. Effective vs. Armored/Horseback units.")
    WOLF_BEIL {
        @Override
        public boolean canBeWieldedBy(Entity unit) {
            ClassTypes unitClass = unit.getComponent(UnitClassComponent.class).unitClass;
            return unitClass == LORD_HECTOR || unitClass == GREAT_LORD;
        }
    },

    @WeaponStats(name = "Armads", type = AXE, level = PRF, durability = 25, minRange = 1, maxRange = 1,
            weight = 18, might = 18, hit = 85, crit = 0, effectiveAgainst = {DRAGON}, spritePath = "",
            infotext = "Hector only. Defense +5. Effective vs. Dragon units.")
    ARMADS {
        @Override
        public boolean canBeWieldedBy(Entity unit) {
            ClassTypes unitClass = unit.getComponent(UnitClassComponent.class).unitClass;
            return unitClass == LORD_HECTOR || unitClass == GREAT_LORD;
        }

        // TODO: Test this and all other stat mods
        @Override
        public UnitStatsModComponent statModsOnEquip() {
            return new UnitStatsModComponent(new Pair<>(UnitStatLabels.DEFENSE, 5));
        }
    }
    // *** AXES ***



    // TODO: Implement bows and ballistae
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
        return calculations.getDefendingEntity().getComponent(UnitStatsComponent.class).get(UnitStatLabels.DEFENSE);
    }
}
