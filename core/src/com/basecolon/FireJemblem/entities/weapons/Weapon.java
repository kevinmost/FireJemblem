package com.basecolon.FireJemblem.entities.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.constants.weapons.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;
import com.sun.istack.internal.NotNull;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public abstract class Weapon implements Wieldable {
    @NotNull
    private int might;
    @NotNull
    private int crit;
    @NotNull
    private int hit;
    @NotNull
    private int weight;

    @NotNull
    private int minRange;
    @NotNull
    private int maxRange;

    @NotNull
    private int durability;
    @NotNull
    private int maxDurability;

    @NotNull
    private String name;

    @NotNull
    private WeaponTypes type;
    @NotNull
    private WeaponProficiencyLevels rank;

    @NotNull
    private Texture weaponGraphic;

    public int getMight() {
        return might;
    }

    public void setMight(int might) {
        this.might = might;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeaponTypes getType() {
        return type;
    }

    public void setType(WeaponTypes type) {
        this.type = type;
    }

    public WeaponProficiencyLevels getRank() {
        return rank;
    }

    public void setRank(WeaponProficiencyLevels rank) {
        this.rank = rank;
    }

    public Texture getWeaponGraphic() {
        return weaponGraphic;
    }

    public void setWeaponGraphic(Texture weaponGraphic) {
        this.weaponGraphic = weaponGraphic;
    }
}
