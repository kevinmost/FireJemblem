package com.basecolon.FireJemblem.items.usables.healing;

import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.items.usables.Usable;
import com.basecolon.FireJemblem.units.Unit;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class Vulnerary extends Usable {
    @Override
    public int getMaxDurability() {
        return 3;
    }

    @Override
    public int getWorth() {
        return 300;
    }

    @Override
    public String getName() {
        return "Vulnerary";
    }

    @Override
    public Texture getGraphic() {
        return null;
    }

    @Override
    public void useOn(Unit usingUnit) {
        usingUnit.getStats();
    }
}
