package com.basecolon.FireJemblem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.FireJemblem.misc.helpers.GameLauncherHelpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FireJemblemGame implements ApplicationListener {
    final Logger logger = LoggerFactory.getLogger(FireJemblemGame.class);

    SpriteBatch batch;
    BitmapFont font;

    ComponentMapper<UnitStats> unitStats = ComponentMapper.getFor(UnitStats.class);
    private Entity lyn;
    private Entity hector;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        font.setColor(Color.BLACK);

        lyn = GameLauncherHelpers.createLyn();
        hector = GameLauncherHelpers.createHector();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Lyn has " + unitStats.get(lyn).get(UnitStatLabels.CURRENT_HP) + " HP", 50, 50);
        font.draw(batch, "Hector has " + unitStats.get(hector).get(UnitStatLabels.CURRENT_HP) + " HP", 100, 100);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
