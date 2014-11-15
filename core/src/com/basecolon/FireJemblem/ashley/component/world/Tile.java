package com.basecolon.FireJemblem.ashley.component.world;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.FireJemblem.constants.component.world.TileConstants;

import java.util.HashMap;
import java.util.Map;

public class Tile extends Component {
    /**
     * The name of this tile (ex: Plain, Forest, etc)
     */
    private String name;
    /**
     * The sprite that this tile represents
     */
    private Texture sprite;
    /**
     * The def buff that a character gets by standing on this tile
     */
    private int def;
    /**
     * The avoid buff that a character gets by standing on this tile
     */
    private int avoid;
    /**
     * The percent of their HP that a character heals by standing on this tile at the beginning of their turn
     */
    private int heal;
    /**
     * The cost for a unit to walk onto this tile
     */
    private int moveCost;
    /**
     * The cost for certain classes to move onto this tile. Ex: A cavalier would have to spend 3 move to walk across a forest tile, not 2
     */
    private Map<ClassTypes, Integer> specialMoveCost = new HashMap<>();

    public Tile(TileConstants tile) {
        this.setAllParametersFromEnum(tile);
    }

    /**
     * Takes in a {@link TileConstants} enum and sets all parameters in this class
     * @param constants The enum entry to take in
     */
    private void setAllParametersFromEnum(TileConstants constants) {
        this.name = constants.getName();
        this.sprite = constants.getSprite();
        this.def = constants.getDef();
        this.avoid = constants.getAvoid();
        this.heal = constants.getHeal();
        this.moveCost = constants.getMoveCost();
        this.specialMoveCost = constants.getSpecialMoveCost();
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

    public int getMoveCost(ClassTypes unitClass) {
        Integer specialMoveCost = this.specialMoveCost.get(unitClass);

        if (specialMoveCost != null) {
            return specialMoveCost;
        }

        return moveCost;

    }
}
