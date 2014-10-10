package com.basecolon.FireJemblem.ashley.component.world;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public class Transform extends Component {
    public Vector3 pos = new Vector3();
    public Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotation = 0.0f;
}
