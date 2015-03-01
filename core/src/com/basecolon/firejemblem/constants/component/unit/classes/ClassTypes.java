package com.basecolon.firejemblem.constants.component.unit.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NOTE: Notice that we put promoted classes first because then we can reference them in the pre-promote without running into forward reference problems
 */
public enum ClassTypes {
    BLADE_LORD("Blade Lord"),
    LORD_LYN("Lord", BLADE_LORD),
    KNIGHT_LORD("Knight Lord"),
    LORD_ELIWOOD("Lord", KNIGHT_LORD),
    GREAT_LORD("Great Lord"),
    LORD_HECTOR("Lord", GREAT_LORD),

    ARCHSAGE("Archsage"),
    DARK_DRUID("Dark Druid"),

    BARD("Bard"),
    DANCER("Dancer"),

    HERO("Hero"),
    MERCENARY("Mercenary", HERO),

    SWORDMASTER("Swordmaster"),
    MYRMIDON("Myrmidon", SWORDMASTER),

    ASSASSIN("Assassin"),
    THIEF("Thief", ASSASSIN),

    GENERAL("General"),
    KNIGHT("Knight", GENERAL),

    WARRIOR("Warrior"),
    FIGHTER("Fighter", WARRIOR),

    BERSERKER("Berserker"),
    PIRATE("Pirate", BERSERKER),

    SNIPER("Sniper"),
    ARCHER("Archer", SNIPER),

    NOMAD_TROOPER("Nomad Trooper"),
    NOMAD("Nomad", NOMAD_TROOPER),

    PALADIN("Paladin"),
    CAVALIER("Cavalier", PALADIN),

    FALCON_KNIGHT("Falcon Knight"),
    PEGASUS_KNIGHT("Pegasus Knight", FALCON_KNIGHT),

    WYVERN_LORD("Wyvern Lord"),
    WYVERN_RIDER("Wyvern Knight", WYVERN_LORD),

    BISHOP("Bishop"),
    MONK("Monk", BISHOP),
    CLERIC("Cleric", BISHOP),

    VALKYRIE("Valkyrie"),
    TROUBADOUR("Troubadour", VALKYRIE),

    SAGE("Sage"),
    MAGE("Mage", SAGE),

    DRUID("Druid"),
    SHAMAN("Shaman", DRUID),

    // TODO: No enemy-only classes yet, no Transporter
    ;

    private final ClassTypes promotion;
    private final String readableName;

    ClassTypes(String readableName) {
        this.readableName = readableName;
        this.promotion = null;
    }

    ClassTypes(String readableName, ClassTypes promotion) {
        this.readableName = readableName;
        this.promotion = promotion;
    }

    public ClassTypes getPromotion() {
        return promotion;
    }
    public String getName() {
        return readableName;
    }
    public ClassTypeGroupings getGroup() {
        return ClassTypeGroupings.groupOf(this);
    }
    public EffectiveDamageGroups getEffectiveDamage() {
        return EffectiveDamageGroups.groupOf(this);
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

        // TODO: Berserker is weird, it belongs to both bandits and pirates. Will this be an issue?
        FOOT(LORD_LYN, LORD_ELIWOOD, LORD_HECTOR, BLADE_LORD, MYRMIDON, SWORDMASTER, MERCENARY, HERO, ARCHER, SNIPER, THIEF, ASSASSIN),
        ARMORS(GREAT_LORD, KNIGHT, GENERAL),
        KNIGHTS_A(CAVALIER, TROUBADOUR),
        KNIGHTS_B(KNIGHT_LORD, PALADIN, VALKYRIE),
        FIGHTERS(FIGHTER, WARRIOR),
        BANDITS(BERSERKER),
        PIRATES(PIRATE, BERSERKER),
        MAGES(MAGE, SAGE, SHAMAN, DRUID, CLERIC, BISHOP),
        FLIERS(PEGASUS_KNIGHT, FALCON_KNIGHT, WYVERN_RIDER, WYVERN_LORD),
        NOMAD(ClassTypes.NOMAD),
        NOMAD_TROOPER(ClassTypes.NOMAD_TROOPER);

        static {
            ClassTypes unused = ClassTypes.ARCHER; // This is just here so that the ClassTypes are instantiated and added to the grouping lists
        }

        private List<ClassTypes> classesInType = new ArrayList<>();

        ClassTypeGroupings(ClassTypes... classesInThisGroup) {
            classesInType = Arrays.asList(classesInThisGroup);
        }


        public List<ClassTypes> getClassesInType() {
            return classesInType;
        }

        public static ClassTypeGroupings groupOf(ClassTypes unitClass) {
            for (ClassTypeGroupings classTypeGrouping : ClassTypeGroupings.values()) {
                if (classTypeGrouping.classesInType.contains(unitClass)) {
                    return classTypeGrouping;
                }
            }
            return FOOT; // The default should be foot, I guess, but we should never hit this point anyway
        }
    }

    public enum EffectiveDamageGroups {
        NONE(), // Everything else
        MOUNTED(KNIGHT_LORD, CAVALIER, PALADIN, TROUBADOUR, VALKYRIE, NOMAD, NOMAD_TROOPER),
        ARMORED(GREAT_LORD, KNIGHT, GENERAL),
        SWORDS(MERCENARY, HERO, MYRMIDON, SWORDMASTER, BLADE_LORD),
        FLYING(PEGASUS_KNIGHT, FALCON_KNIGHT),
        DRAGON(WYVERN_RIDER, WYVERN_LORD)
        ;

        private List<ClassTypes> classesInType = new ArrayList<>();

        EffectiveDamageGroups(ClassTypes... classesInThisGroup) {
            classesInType = Arrays.asList(classesInThisGroup);
        }

        public List<ClassTypes> getClassesInType() {
            return classesInType;
        }

        public static EffectiveDamageGroups groupOf(ClassTypes unitClass) {
            for (EffectiveDamageGroups effectiveDamageGroup : EffectiveDamageGroups.values()) {
                if (effectiveDamageGroup.classesInType.contains(unitClass)) {
                    return effectiveDamageGroup;
                }
            }
            return NONE;
        }

    }
}
