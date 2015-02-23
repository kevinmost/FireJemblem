package com.basecolon.firejemblem.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
// TODO Write me
public class AnimatedSpriteComponent extends Component {
    Animation animation;
    private final Texture spritesheet;

    public AnimatedSpriteComponent(Texture texture) {
        spritesheet = texture;

    }
}
