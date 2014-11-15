package com.basecolon.FireJemblem.constants.component.world;

import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

// TODO Should we also put the Sprite and name into here instead of the identity? They are specific to a tile's type, after all
public enum TileConstants {
    PLAIN("Plain", null,
            0, 0, 0,
            1
    ),
    FOREST("Forest", null,
            1, 20, 0,
            2, new Pair<>(ClassTypes.HERO, 4) // TODO This is just an example and is actually wrong in terms of FE gameplay, but we are doing this so we can test the move-cost override for now
    )
    ;

    private final String name;
    private final Texture sprite;
    private final int def;
    private final int avoid;
    private final int heal;
    private final int moveCost;
    private final Map<ClassTypes, Integer> specialMoveCost;



    /**
     * @param def The defense buff given by this tile
     * @param avoid The avoid buff given by this tile
     * @param heal The percentage that a character heals if they are standing on this tile at the start of their turn
     * @param moveCost The cost of a character to move onto this tile
     * @param specialMoveCost The cost of a character to move onto this tile if there are special move costs for certain classes (eg, Forests require 2 move for most characters and 3 move for horseback characters)
     */
    @SafeVarargs
    TileConstants(String name, Texture sprite, int def, int avoid, int heal, int moveCost, Pair<ClassTypes, Integer>... specialMoveCost) {
        this.name = name;
        this.sprite = sprite;
        this.def = def;
        this.avoid = avoid;
        this.heal = heal;
        this.moveCost = moveCost;

        this.specialMoveCost = new HashMap<>();
        for (Pair<ClassTypes, Integer> aCost : specialMoveCost) {
            this.specialMoveCost.put(aCost.getKey(), aCost.getValue());
        }
    }


    public String getName() {
        return name;
    }

    public Texture getSprite() {
        return sprite;
    }

    public int getDef() {
        return def;
    }

    public int getAvoid() {
        return avoid;
    }

    public int getHeal() {
        return heal;
    }

    public int getMoveCost() {
        return moveCost;
    }

    public Map<ClassTypes, Integer> getSpecialMoveCost() {
        return specialMoveCost;
    }

    /**
     * Allows a user to search for a tile by the tile's standard name, such as "Plain", "Forest", etc
     * @param name The name of the tile type to get, such as "Plain", "Forest", etc. This is not case-sensitive
     * @return The {@link TileConstants} object that represents that tile-name, or null if there is no tile type by that name
     */
    public static TileConstants getTileConstantsByName(String name) {
        for (TileConstants tileConstants : TileConstants.values()) {
            if (tileConstants.getName().toLowerCase().equals(name.toLowerCase())) {
                return tileConstants;
            }
        }
        return null;
    }
}
