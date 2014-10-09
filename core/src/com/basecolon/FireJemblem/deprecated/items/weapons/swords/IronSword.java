package com.basecolon.FireJemblem.deprecated.items.weapons.swords;

import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class IronSword extends Sword {

    @Override
    public int getMight() {
        return 5;
    }

    @Override
    public int getCrit() {
        return 0;
    }

    @Override
    public int getHit() {
        return 90;
    }

    @Override
    public int getWeight() {
        return 5;
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
        return 46;
    }

    @Override
    public int getWorth() {
        return 460;
    }

    @Override
    public String getName() {
        return "Iron Sword";
    }

    @Override
    public WeaponProficiencyLevels getRank() {
        return WeaponProficiencyLevels.E;
    }

    @Override
    public Texture getGraphic() {
        return null;
    }
}
