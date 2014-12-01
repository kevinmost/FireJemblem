package com.basecolon.FireJemblem.constants.component.unit.classes;

import java.util.ArrayList;
import java.util.List;

import static com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes.ClassTypeGroupings.*;

/**
 * NOTE: Notice that we put promoted classes first because then we can reference them in the pre-promote without running into forward reference problems
 * @author kevinmost
 * @date 10/7/14
 */
public enum ClassTypes {
    BLADE_LORD("Blade Lord", FOOT),
    LORD_LYN("Lord", FOOT, BLADE_LORD),
    KNIGHT_LORD("Knight Lord", KNIGHTS_B),
    LORD_ELIWOOD("Lord", FOOT, KNIGHT_LORD),
    GREAT_LORD("Great Lord", ARMORS),
    LORD_HECTOR("Lord", FOOT, GREAT_LORD),

    ARCHSAGE("Archsage", MAGES),
    DARK_DRUID("Dark Druid", MAGES),

    BARD("Bard", FOOT),
    DANCER("Dancer", FOOT),

    HERO("Hero", FOOT),
    MERCENARY("Mercenary", FOOT, HERO),

    SWORDMASTER("Swordmaster", FOOT),
    MYRMIDON("Myrmidon", FOOT, SWORDMASTER),

    ASSASSIN("Assassin", FOOT),
    THIEF("Thief", FOOT, ASSASSIN),

    GENERAL("General", ARMORS),
    KNIGHT("Knight", ARMORS, GENERAL),

    WARRIOR("Warrior", FIGHTERS),
    FIGHTER("Fighter", FIGHTERS, WARRIOR),

    BERSERKER("Berserker", PIRATES),
    PIRATE("Pirate", PIRATES, BERSERKER),

    SNIPER("Sniper", FOOT),
    ARCHER("Archer", FOOT, SNIPER),

    NOMAD_TROOPER("Nomad Trooper", ClassTypeGroupings.NOMAD_TROOPER),
    NOMAD("Nomad", ClassTypeGroupings.NOMAD, NOMAD_TROOPER),

    PALADIN("Paladin", KNIGHTS_B),
    CAVALIER("Cavalier", KNIGHTS_A, PALADIN),

    FALCON_KNIGHT("Falcon Knight", FLIERS),
    PEGASUS_KNIGHT("Pegasus Knight", FLIERS, FALCON_KNIGHT),

    WYVERN_LORD("Wyvern Lord", FLIERS),
    WYVERN_KNIGHT("Wyvern Knight", FLIERS, WYVERN_LORD),

    BISHOP("Bishop", MAGES),
    MONK("Monk", MAGES, BISHOP),
    CLERIC("Cleric", MAGES, BISHOP),

    VALKYRIE("Valkyrie", KNIGHTS_B),
    TROUBADOUR("Troubadour", KNIGHTS_A, VALKYRIE),

    SAGE("Sage", MAGES),
    MAGE("Mage", MAGES, SAGE),

    DRUID("Druid", MAGES),
    SHAMAN("Shaman", MAGES, DRUID),

    // TODO: No enemy-only classes yet, no Transporter
    ;

    private final ClassTypes promotion;
    private final String readableName;
    private final ClassTypeGroupings group;

    ClassTypes(String readableName, ClassTypeGroupings group) {
        this.readableName = readableName;
        this.promotion = null;
        this.group = group;
        group.addClass(this);
    }

    ClassTypes(String readableName, ClassTypeGroupings group, ClassTypes promotion) {
        this.readableName = readableName;
        this.promotion = promotion;
        this.group = group;
        group.addClass(this);
    }

    public ClassTypes getPromotion() {
        return promotion;
    }
    public String getName() {
        return readableName;
    }
    public ClassTypeGroupings getGroup() {
        return group;
    }

    /**
     * These are used only when determining movement cost for a particular class.
     * Since all classes fall within a group of several classes that have the same movement costs across all tile-types,
     * it is needlessly verbose to have to define movement on a per-class basis for each tile-type, when we can
     * instead group the classes and define movement on a per-group basis.
     * WARNING: Fire Emblem Wikia did not give any info regarding the grouping of certain unit types, so we must make
     * the closest guess possible. Luckily, most of the missing types were clearly FOOT units.
     * NOTE: Nomad and Nomad Trooper have their own groups.
     */
public enum ClassTypeGroupings {

        FOOT, ARMORS, KNIGHTS_A, KNIGHTS_B, FIGHTERS, BANDITS, PIRATES, MAGES, FLIERS, NOMAD, NOMAD_TROOPER;

        static {
            ClassTypes unused = ClassTypes.ARCHER; // This is just here so that the ClassTypes are instantiated and added to the grouping lists
        }

        private List<ClassTypes> classesInType = new ArrayList<>();

        void addClass(ClassTypes aClass) {
            classesInType.add(aClass);
        }

        public List<ClassTypes> getClassesInType() {
            return classesInType;
        }
    }
}
