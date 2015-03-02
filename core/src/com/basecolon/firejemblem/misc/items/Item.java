package com.basecolon.firejemblem.misc.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Item {
    private final Sprite sprite;
    private final String name;
    private Integer currentDurability;
    private final Integer maxDurability;
    private final String infoText;

    protected Item(Sprite sprite, String name, Integer currentDurability, Integer maxDurability, String infoText) {
        this.sprite = sprite;
        this.name = name;
        this.currentDurability = currentDurability;
        this.maxDurability = maxDurability;
        this.infoText = infoText;
    }


    public Integer getMaxDurability() {
        return maxDurability;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    public Integer getCurrentDurability() {
        return currentDurability;
    }

    public String getInfoText() {
        return infoText;
    }

    /**
     * Called when an item is used. Reduces durability by 1
     * @return The new durability of the item
     */
    public int decreaseDurability() {
        currentDurability--;
        return currentDurability;
    }
}
