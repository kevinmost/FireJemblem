package com.basecolon.firejemblem.misc.items;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Just a giant POJO set from an enum where the actual data is defined.
 * The only mutable field in here is {@link #currentDurability}, and the only way that the client can change it
 * is by calling {@link #decreaseDurability()}, which decreases the value by 1
 */
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


    /**
     * Can be used, for example, to cast this item to a Weapon. Useful if you got an Item out of an Inventory and need
     * to get Weapon-related fields off it
     */
    @SuppressWarnings("unchecked")
    public <C extends Item> C as(Class<C> clazz) {
        if (clazz.isInstance(this)) {
            return (C) this;
        }
        throw new ClassCastException("Tried to cast object of instance " + this.getClass().getSimpleName() +
                " to " + clazz.getClass().getSimpleName());
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
