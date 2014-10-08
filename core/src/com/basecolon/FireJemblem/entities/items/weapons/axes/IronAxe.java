package com.basecolon.FireJemblem.entities.items.weapons.axes;

import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.constants.weapons.WeaponProficiencyLevels;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class IronAxe extends Axe {
    @Override
    public int getMight() {
        return 8;
    }

    @Override
    public int getCrit() {
        return 0;
    }

    @Override
    public int getHit() {
        return 75;
    }

    @Override
    public int getWeight() {
        return 10;
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
    public WeaponProficiencyLevels getRank() {
        return WeaponProficiencyLevels.E;
    }

    @Override
    public int getMaxDurability() {
        return 45;
    }

    @Override
    public String getName() {
        return "Iron Axe";
    }

    @Override
    public Texture getGraphic() {
        return null;
    }
}
