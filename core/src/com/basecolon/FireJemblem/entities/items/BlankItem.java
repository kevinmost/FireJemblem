package com.basecolon.FireJemblem.entities.items;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class BlankItem extends Item {
    @Override
    public int getMaxDurability() {
        return -1; // TODO process negative values as N/A when showing elements in inventory
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Texture getGraphic() {
        return null;
    }
}
