package com.basecolon.FireJemblem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.world.TileUnitInteraction;
import com.basecolon.FireJemblem.ashley.entity.world.TileEntityBuilder;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.FireJemblem.constants.component.world.TileConstants;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class AshleyTester {


    @Test
    public void testTiles() throws Exception {
        Entity plain = new TileEntityBuilder().setTileType(TileConstants.PLAIN).build();

        Entity forest = new TileEntityBuilder().setTileType(TileConstants.FOREST).build();

        // Create a ComponentMapper for Tiles
        ComponentMapper<TileUnitInteraction> tileComponentMapper = ComponentMapper.getFor(TileUnitInteraction.class);

        assertEquals(1, (int)tileComponentMapper.get(plain).moveCost.get(ClassTypes.MERCENARY));
        assertEquals(1, (int)tileComponentMapper.get(plain).moveCost.get(ClassTypes.HERO));
        assertEquals(2, (int)tileComponentMapper.get(forest).moveCost.get(ClassTypes.MERCENARY));
        assertEquals(4, (int)tileComponentMapper.get(forest).moveCost.get(ClassTypes.HERO));
    }

    @Test
    public void testTileClone() throws Exception {
        Entity a = new TileEntityBuilder().setTileType(TileConstants.PLAIN).build();
        Entity b = new TileEntityBuilder().setTileType(TileConstants.PLAIN).build();

        ComponentMapper<TileUnitInteraction> tileComponentMapper = ComponentMapper.getFor(TileUnitInteraction.class);

        // Assert that, even though we're creating two identical tiles, their components don't point to the same memory address, so that we can modify them separately
        assertFalse(tileComponentMapper.get(a) == tileComponentMapper.get(b));
    }
}
