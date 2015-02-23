package com.basecolon.firejemblem.constants;

import com.badlogic.ashley.core.Engine;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class FireJemblem {
    public static final int DESKTOP_WINDOW_WIDTH = 800;
    public static final int DESKTOP_WINDOW_HEIGHT = 480;
    public static final String TITLE = "Fire Jemblem";
    public static final Engine engine = new Engine();
    public static final float deltaTime = 1/60;

    public static void updateEngine() {
        updateEngine(deltaTime);
    }
    public static void updateEngine(float time) {
        engine.update(time);
    }
}
