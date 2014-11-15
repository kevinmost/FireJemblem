package com.basecolon.FireJemblem.ashley.entity.world;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.ashley.component.SpriteComponent;
import com.basecolon.FireJemblem.ashley.component.world.Tile;
import com.basecolon.FireJemblem.ashley.entity.EntityBuilder;
import com.basecolon.FireJemblem.constants.component.world.TileConstants;

public class TileEntityBuilder extends EntityBuilder {

    @Required(componentsSetByThisMethod = Tile.class)
    public TileEntityBuilder setTileType(TileConstants constants) {
        add(Tile.class, new Tile(constants));
        return this;
    }

    public TileEntityBuilder setSprite(Sprite sprite) {
        add(SpriteComponent.class, new SpriteComponent(sprite));
        return this;
    }
}
