package com.basecolon.FireJemblem.ashley.entity.world;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.ashley.component.SpriteComponent;
import com.basecolon.FireJemblem.ashley.component.world.TileUnitInteraction;
import com.basecolon.FireJemblem.ashley.entity.EntityBuilder;
import com.basecolon.FireJemblem.constants.component.world.TileConstants;

import java.util.ArrayList;
import java.util.List;

public class TileEntityBuilder extends EntityBuilder {

    public TileEntityBuilder setTileType(TileConstants constants) {
        put(TileUnitInteraction.class, new TileUnitInteraction(constants));
        return this;
    }

    public TileEntityBuilder setSprite(Sprite sprite) {
        put(SpriteComponent.class, new SpriteComponent(sprite));
        return this;
    }

    @Override
    public <C extends Component> Class<C>[] getRequiredComponents() {
        List<Class<? extends Component>> requiredComponents = new ArrayList<Class<? extends Component>>() {{
            add(TileUnitInteraction.class);
            add(SpriteComponent.class);
        }};
        return requiredComponents.toArray(new Class[requiredComponents.size()]);
    }

    @Override
    public <C extends Component> Class<C>[] getAllComponents() {
        return getRequiredComponents();
    }
}
