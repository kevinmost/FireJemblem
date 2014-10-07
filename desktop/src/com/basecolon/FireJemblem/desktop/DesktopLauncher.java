package com.basecolon.FireJemblem.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.basecolon.FireJemblem.FireJemblemGame;
import com.basecolon.FireJemblem.constants.System;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = System.DESKTOP_WINDOW_WIDTH;
        config.height = System.DESKTOP_WINDOW_HEIGHT;
        config.title = System.TITLE;

        new LwjglApplication(new FireJemblemGame(), config);
        // TODO: Nothing works yet
    }
}
