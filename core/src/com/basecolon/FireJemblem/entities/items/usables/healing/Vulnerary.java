package com.basecolon.FireJemblem.entities.items.usables.healing;

import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.entities.items.usables.Usable;
import com.basecolon.FireJemblem.entities.units.Unit;

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
    public String getName() {
        return "Vulnerary";
    }

    @Override
    public Texture getGraphic() {
        return null;
    }

    @Override
    public void use(Unit usingUnit) {
        usingUnit.getStats().increaseCurrentHPBy(10);
    }
}
