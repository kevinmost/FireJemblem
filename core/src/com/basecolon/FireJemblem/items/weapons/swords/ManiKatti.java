package com.basecolon.FireJemblem.items.weapons.swords;

import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class ManiKatti extends Sword {

    @Override
    public int getMight() {
        return 8;
    }

    @Override
    public int getCrit() {
        return 20;
    }

    @Override
    public int getHit() {
        return 80;
    }

    @Override
    public int getWeight() {
        return 3;
    }

    @Override
    public int getMinRange() {
        return 1;
    }

    @Override
    public int getMaxRange() {
        return 1;
    }

    @Override
    public int getMaxDurability() {
        return 45;
    }

    @Override
    public int getWorth() {
        return -1; // MY SWORD IS PRICELESS
    }

    @Override
    public String getName() {
        return "Mani Katti";
    }

    @Override
    public WeaponProficiencyLevels getRank() {
        return WeaponProficiencyLevels.PRF;
    }

    @Override
    public Texture getGraphic() {
        return null;
    }
}
