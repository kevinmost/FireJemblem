package com.basecolon.firejemblem.ashley.component.world;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public class TransformComponent extends Component {
    private Vector3 pos = new Vector3();
    private Vector2 scale = new Vector2(1.0f, 1.0f);
    private float rotation = 0.0f;

    public TransformComponent(Vector3 pos, Vector2 scale, float rotation) {
        this.pos = pos;
        this.scale = scale;
        this.rotation = rotation;
    }

    public TransformComponent(Vector3 pos, Vector2 scale) {
        this.pos = pos;
        this.scale = scale;
    }

    public TransformComponent(Vector3 pos) {
        this.pos = pos;
    }

    public Vector3 getPos() {
        return pos;
    }

    public void setPos(Vector3 pos) {
        this.pos = pos;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
