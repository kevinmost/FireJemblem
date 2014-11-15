package com.basecolon.FireJemblem.constants.component.world;

import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public enum TileConstants {
    PLAIN(0, 0, 0,
            1
    ),
    FOREST(1, 20, 0,
            2, new Pair<>(ClassTypes.HERO, 4) // TODO This is just an example and is actually wrong in terms of FE gameplay, but we are doing this so we can test the move-cost override for now
    )
    ;

    public final int def;
    public final int avoid;
    public final int heal;
    public final int moveCost;
    public final Map<ClassTypes, Integer> specialMoveCost;

    /**
     * @param def The defense buff given by this tile
     * @param avoid The avoid buff given by this tile
     * @param heal The percentage that a character heals if they are standing on this tile at the start of their turn
     * @param moveCost The cost of a character to move onto this tile
     * @param specialMoveCost The cost of a character to move onto this tile if there are special move costs for certain classes (eg, Forests require 2 move for most characters and 3 move for horseback characters)
     */
    @SafeVarargs
    TileConstants(int def, int avoid, int heal, int moveCost, Pair<ClassTypes, Integer>... specialMoveCost) {
        this.def = def;
        this.avoid = avoid;
        this.heal = heal;
        this.moveCost = moveCost;

        this.specialMoveCost = new HashMap<>();
        for (Pair<ClassTypes, Integer> aCost : specialMoveCost) {
            this.specialMoveCost.put(aCost.getKey(), aCost.getValue());
        }
    }

}
