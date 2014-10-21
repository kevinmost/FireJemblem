package com.basecolon.FireJemblem.items.weapons.swords;

import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class KillingEdge extends Sword {
    @Override
    public int getMight() {
        return 9;
    }

    @Override
    public int getCrit() {
        return 30;
    }

    @Override
    public int getHit() {
        return 75;
    }

    @Override
    public int getWeight() {
        return 7;
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
        return 20;
    }

    @Override
    public int getWorth() {
        return 1300;
    }

    @Override
    public String getName() {
        return "Killing Edge";
    }

    @Override
    public WeaponProficiencyLevels getRank() {
        return WeaponProficiencyLevels.C;
    }

    @Override
    public Texture getGraphic() {
        return null;
    }
}
