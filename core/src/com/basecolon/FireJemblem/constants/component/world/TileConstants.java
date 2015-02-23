package com.basecolon.firejemblem.constants.component.world;

import com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes;

import java.util.HashMap;
import java.util.Map;

import static com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes.ClassTypeGroupings.*;
import static com.basecolon.firejemblem.constants.component.world.TileConstants.SpecialValue.UNWALKABLE;

// TODO: There is a lot of logic missing, such as +5 RES when standing on a throne, and how to ensure that Fliers don't get stat buffs from tiles (maybe handle this in an EntitySystem?)
public enum TileConstants {
    INVALID("----", 0, 0, 0, UNWALKABLE),
    ARENA("Arena", 0, 10, 0, 1),
    BRIDGE("Bridge", 0, 0, 0, 1),
    CLIFF("Cliff", 0, 0, 0, UNWALKABLE),
    DECK("Deck", 0, 0, 0, 1),
    DESERT("Desert", 0, 5, 0, 2, new SpecialMoveCost(1, MAGES).and(3, ARMORS, FIGHTERS, NOMAD, NOMAD_TROOPER).and(4, KNIGHTS_A, KNIGHTS_B)),
    DOOR("Door", 0, 0, 0, UNWALKABLE),
    FENCE("Fence", 0, 0, 0, UNWALKABLE),
    FLOOR("Floor", 0, 0, 0, 1),
    FOREST("Forest", 1, 20, 0, 2, new SpecialMoveCost(3, KNIGHTS_A, KNIGHTS_B)),
    FORT("Fort", 2, 20, 20, 2),
    GATE("Gate", 2, 20, 10, 1),
    HOUSE("House", 0, 10, 0, 1),
    LAKE("Lake", 0, 10, 0, UNWALKABLE, new SpecialMoveCost(3, PIRATES)),
    MOUNTAIN("Mountain", 1, 30, 0, 4, new SpecialMoveCost(UNWALKABLE, KNIGHTS_A, NOMAD, ARMORS).and(3, FIGHTERS, BANDITS, PIRATES).and(5, NOMAD_TROOPER).and(6, KNIGHTS_B)),
    PEAK("Peak", 2, 40, 0, UNWALKABLE, new SpecialMoveCost(4, BANDITS)),
    PILLAR("Pillar", 1, 20, 0, 2, new SpecialMoveCost(3, KNIGHTS_A, KNIGHTS_B, NOMAD, NOMAD_TROOPER)),
    PLAIN("Plain", 0, 0, 0, 1),
    RIVER("River", 0, 0, 0, UNWALKABLE, new SpecialMoveCost(5, FOOT, BANDITS, NOMAD_TROOPER).and(2, PIRATES)),
    ROAD("Road", 0, 0, 0, 1),
    RUINS("Ruins", 0, 10, 0, 1),
    RUINS_VILLAGE("Ruins", 0, 0, 0, 2),
    SAND("Sand", 0, 5, 0, 1),
    SEA("Sea", 0, 10, 0, UNWALKABLE, new SpecialMoveCost(2, PIRATES)),
    SHOP("Shop", 0, 10, 0, 1),
    SNAG("Snag", 0, 0, 0, UNWALKABLE), // TODO: Are we going to have to do something special with this? Maybe not define it as a tile-type at all, but use a Plain with an NPC entity on it?
    STAIRS("Stairs", 0, 0, 0, 1),
    THRONE("Throne", 2, 20, 10, 1), // TODO: Also needs to give RES+5 boost
    VILLAGE("Village", 0, 10, 0, 1),
    WALL("Wall", 0, 0, 0, UNWALKABLE),
    WALL_WEAK("Wall", 0, 0, 0, UNWALKABLE)
    ;

    public final String name;
    public final int def;
    public final int avoid;
    public final int heal;
    public final int moveCost;
    public final Map<ClassTypes, Integer> specialMoveCost;

    /**
     * @param name The human-readable name of this tile
     * @param def The defense buff given by this tile
     * @param avoid The avoid buff given by this tile
     * @param heal The percentage that a character heals if they are standing on this tile at the start of their turn
     * @param moveCost The cost of a character to move onto this tile
     * @param specialMoveCost The cost of a character to move onto this tile if there are special move costs for certain classes (eg, Forests require 2 move for most characters and 3 move for horseback characters)
     */
    TileConstants(String name, int def, int avoid, int heal, int moveCost, SpecialMoveCost specialMoveCost) {
        this(name, def, avoid, heal, moveCost);
        for (Map.Entry<ClassTypes.ClassTypeGroupings, Integer> groupingMoveCost : specialMoveCost.specialMoveCostPairs.entrySet()) {
            for (ClassTypes classMoveCost : groupingMoveCost.getKey().getClassesInType()) {
                this.specialMoveCost.put(classMoveCost, groupingMoveCost.getValue());
            }
        }

        // All tiles except the INVALID and WALL ones need to allow fliers to walk across them with only 1 cost
        if (!name.equals("----") && !name.equals("Wall")) {
            for (ClassTypes flyingClass : FLIERS.getClassesInType()) {
                this.specialMoveCost.put(flyingClass, 1);
            }
        }
    }
    TileConstants(String name, int def, int avoid, int heal, int moveCost) {
        this.name = name;
        this.def = def;
        this.avoid = avoid;
        this.heal = heal;
        if (moveCost == 0) throw new RuntimeException("You can't specify 0 as a move-cost for a tile, you probably meant to specify UNWALKABLE");
        this.moveCost = moveCost;
        this.specialMoveCost = new HashMap<>();
    }

    /**
     * Contains constants to be used in this enum.
     */
    class SpecialValue {
        /**
         * A value that is sufficiently large to ensure that any tile that has this movement cost will be unwalkable,
         * but not too large so that an overflow occurs when executing movement-previewing algorithms
         */
        static final int UNWALKABLE = Integer.MAX_VALUE - 100;
    }

    /**
     * Forces exceptional movement-costs to be built fluently.
     */
    static class SpecialMoveCost {
        Map<ClassTypes.ClassTypeGroupings, Integer> specialMoveCostPairs = new HashMap<>();

        private SpecialMoveCost(Integer cost, ClassTypes.ClassTypeGroupings... group) {
            and(cost, group);
        }

        SpecialMoveCost and(Integer cost, ClassTypes.ClassTypeGroupings... group) {
            for (ClassTypes.ClassTypeGroupings aGroup : group) {
                specialMoveCostPairs.put(aGroup, cost);
            }
            return this;
        }
    }
}
