package com.basecolon.FireJemblem.ashley.entity.world;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.ashley.component.SpriteComponent;
import com.basecolon.FireJemblem.ashley.component.world.TileUnitInteraction;
import com.basecolon.FireJemblem.ashley.entity.EntityBuilder;
import com.basecolon.FireJemblem.constants.component.world.TileConstants;

public class TileEntityBuilder extends EntityBuilder {

    @Required(componentsSetByThisMethod = TileUnitInteraction.class)
    public TileEntityBuilder setTileType(TileConstants constants) {
        put(TileUnitInteraction.class, new TileUnitInteraction(constants));
        return this;
    }

    @Optional(componentsSetByThisMethod = SpriteComponent.class)
    public TileEntityBuilder setSprite(Sprite sprite) {
        put(SpriteComponent.class, new SpriteComponent(sprite));
        return this;
    }
}
