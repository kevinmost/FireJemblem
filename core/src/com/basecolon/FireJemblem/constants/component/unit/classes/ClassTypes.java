package com.basecolon.FireJemblem.constants.component.unit.classes;

/**
 * NOTE: Notice that we put promoted classes first because then we can reference them in the pre-promote without running into forward reference problems
 * @author kevinmost
 * @date 10/7/14
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
    WYVERN_KNIGHT("Wyvern Knight", WYVERN_LORD),

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
}
