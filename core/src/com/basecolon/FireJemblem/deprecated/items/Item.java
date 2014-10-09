package com.basecolon.FireJemblem.deprecated.items;

import com.badlogic.gdx.graphics.Texture;

/**
 * Any item that a Unit can have in their inventory
 * @author kevinmost
 * @date 10/7/14
 */
public abstract class Item {
    // Define this item's durability and the methods to manipulate that durability
    protected int durability;
    public final int getDurability() {
        return durability;
    }
    public final void lowerDurability() {
        lowerDurabilityBy(1);
    }
    public final void lowerDurabilityBy(int by) {
        setDurability(durability - by);
    }
    public final void setDurability(int durability) {
        this.durability = durability;

        if (durability <= 0) {
            System.err.println("BROADCAST MESSAGE: ITEM " + this.getName() + " BROKE");
            // TODO Item broke!
        }
    }

    public Item() {
        // Item starts with max durability
        durability = getMaxDurability();
    }

    public abstract int getMaxDurability();

    public abstract int getWorth();

    public abstract String getName();

    public abstract Texture getGraphic();
}
