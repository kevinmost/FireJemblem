package com.basecolon.firejemblem.ashley.entity.world;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.firejemblem.ashley.component.SpriteComponent;
import com.basecolon.firejemblem.ashley.component.world.TileStatsComponent;
import com.basecolon.firejemblem.ashley.entity.EntityBuilder;
import com.basecolon.firejemblem.constants.component.world.TileConstants;

import java.util.ArrayList;
import java.util.List;

public class TileEntityBuilder extends EntityBuilder {

    public TileEntityBuilder setTileType(TileConstants constants) {
        put(TileStatsComponent.class, new TileStatsComponent(constants));
        return this;
    }

    public TileEntityBuilder setSprite(Sprite sprite) {
        put(SpriteComponent.class, new SpriteComponent(sprite));
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C extends Component> Class<C>[] getRequiredComponents() {
        List<Class<? extends Component>> requiredComponents = new ArrayList<Class<? extends Component>>() {{
            add(TileStatsComponent.class);
            add(SpriteComponent.class);
        }};
        return requiredComponents.toArray(new Class[requiredComponents.size()]);
    }

    @Override
    public <C extends Component> Class<C>[] getAllComponents() {
        return getRequiredComponents();
    }
}
