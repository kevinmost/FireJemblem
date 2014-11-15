package com.basecolon.FireJemblem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.world.Tile;
import com.basecolon.FireJemblem.ashley.entity.world.TileEntityBuilder;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.FireJemblem.constants.component.world.TileConstants;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class AshleyTester {


    @Test
    public void testTiles() throws Exception {
        // Create a tile using a direct reference to the enum
        Entity plain = new TileEntityBuilder().setTileType(TileConstants.PLAIN).build();

        // Create a tile using the name of the tile in FE
        Entity forest = new TileEntityBuilder().setTileType(TileConstants.getTileConstantsByName("Forest")).build();

        // Create a ComponentMapper for Tiles
        ComponentMapper<Tile> tileComponentMapper = ComponentMapper.getFor(Tile.class);

        assertEquals(1, tileComponentMapper.get(plain).getMoveCost(ClassTypes.MERCENARY));
        assertEquals(1, tileComponentMapper.get(plain).getMoveCost(ClassTypes.HERO));
        assertEquals(2, tileComponentMapper.get(forest).getMoveCost(ClassTypes.MERCENARY));
        assertEquals(4, tileComponentMapper.get(forest).getMoveCost(ClassTypes.HERO));
    }

    @Test
    public void testTileClone() throws Exception {
        Entity a = new TileEntityBuilder().setTileType(TileConstants.PLAIN).build();
        Entity b = new TileEntityBuilder().setTileType(TileConstants.PLAIN).build();

        ComponentMapper<Tile> tileComponentMapper = ComponentMapper.getFor(Tile.class);

        List<Tile> tiles = new ArrayList<>();
        tiles.add(tileComponentMapper.get(a));
        tiles.add(tileComponentMapper.get(b));

        // Assert that, even though we're creating two identical tiles, their components don't point to the same memory address, so that we can modify them separately
        assertFalse(tiles.get(0) == tiles.get(1));
    }
}
