package com.basecolon.FireJemblem.misc.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponConstant;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;

/**
 * Just a giant POJO set from an enum where the actual data is defined.
 * The only mutable field in here is {@link #currentDurability}, and the only way that the client can change it
 * is by calling {@link #decreaseDurability()}, which decreases the value by 1
 */
public class Item {

    private final Sprite sprite;
    private final String name;
    private Integer currentDurability;
    private final Integer maxDurability;
    private final String infoText;

    public Item(Sprite sprite, String name, Integer currentDurability, Integer maxDurability, String infoText) {
        this.sprite = sprite;
        this.name = name;
        this.currentDurability = currentDurability;
        this.maxDurability = maxDurability;
        this.infoText = infoText;
    }

    public Weapon asWeapon() {
        try {
            return (Weapon) this;
        } catch (ClassCastException e) {
            throw new ClassCastException(this.getName() + " is not a weapon!");
        }
    }

    public static class Weapon extends Item {
        private final WeaponTypes type;
        private final WeaponProficiencyLevels level;
        private final Integer minRange;
        private final Integer maxRange;
        private final Integer weight;
        private final Integer might;
        private final Integer hit;
        private final Integer crit;

        public Weapon(WeaponConstant weapon) {
            super(weapon.getSprite(), weapon.getName(), weapon.getMaxDurability(), weapon.getMaxDurability(), weapon.getInfoText());
            this.type = weapon.getType();
            this.level = weapon.getLevel();
            this.minRange = weapon.getMinRange();
            this.maxRange = weapon.getMaxRange();
            this.weight = weapon.getWeight();
            this.might = weapon.getMight();
            this.hit = weapon.getHit();
            this.crit = weapon.getCrit();
        }

        public Weapon(Sprite sprite, String name, Integer currentDurability, Integer maxDurability, String infoText, WeaponTypes type, WeaponProficiencyLevels level, Integer minRange, Integer maxRange, Integer weight, Integer might, Integer hit, Integer crit) {
            super(sprite, name, currentDurability, maxDurability, infoText);
            this.type = type;
            this.level = level;
            this.minRange = minRange;
            this.maxRange = maxRange;
            this.weight = weight;
            this.might = might;
            this.hit = hit;
            this.crit = crit;
        }


        public WeaponTypes getType() {
            return type;
        }

        public WeaponProficiencyLevels getLevel() {
            return level;
        }

        public Integer getMinRange() {
            return minRange;
        }

        public Integer getMaxRange() {
            return maxRange;
        }

        public Integer getWeight() {
            return weight;
        }

        public Integer getMight() {
            return might;
        }

        public Integer getHit() {
            return hit;
        }

        public Integer getCrit() {
            return crit;
        }
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
    public int decreaseDurability() {
        currentDurability--;
        return currentDurability;
    }
}
