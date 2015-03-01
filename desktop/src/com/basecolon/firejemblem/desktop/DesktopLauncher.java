package com.basecolon.firejemblem.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.basecolon.firejemblem.FireJemblemGame;
import com.basecolon.firejemblem.constants.FireJemblem;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = FireJemblem.DESKTOP_WINDOW_WIDTH;
        config.height = FireJemblem.DESKTOP_WINDOW_HEIGHT;
        config.title = FireJemblem.TITLE;

        new LwjglApplication(new FireJemblemGame(), config);
        // TODO: Nothing works yet


    }
}
