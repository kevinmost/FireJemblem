package com.basecolon.FireJemblem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.NameComponent;
import com.basecolon.FireJemblem.ashley.component.unit.*;
import com.basecolon.FireJemblem.ashley.component.world.TileUnitInteraction;
import com.basecolon.FireJemblem.ashley.entity.EntityBuilder;
import com.basecolon.FireJemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.FireJemblem.ashley.entity.world.TileEntityBuilder;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.constants.component.item.weapon.sword.IronSword;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.FireJemblem.misc.items.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels.D;
import static com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes.SWORD;
import static com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels.*;
import static com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes.*;
import static com.basecolon.FireJemblem.constants.component.world.TileConstants.FOREST;
import static com.basecolon.FireJemblem.constants.component.world.TileConstants.PLAIN;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class EntityCreationTest {

    @Test(expected = EntityBuilder.RequiredEntityComponentsNotSetException.class)
    public void testRequiredParametersEnforced() {
        new TileEntityBuilder().build();
    }

    @Test
    public void testTiles() throws Exception {
        Entity plain = new TileEntityBuilder().setTileType(PLAIN).setSprite(null).build();

        Entity forest = new TileEntityBuilder().setTileType(FOREST).setSprite(null).build();

        // Create a ComponentMapper for Tiles
        ComponentMapper<TileUnitInteraction> tileComponentMapper = ComponentMapper.getFor(TileUnitInteraction.class);

        assertEquals(1, tileComponentMapper.get(plain).getMoveCost(HERO));
        assertEquals(1, tileComponentMapper.get(plain).getMoveCost(PALADIN));
        assertEquals(2, tileComponentMapper.get(forest).getMoveCost(HERO));
        assertEquals(3, tileComponentMapper.get(forest).getMoveCost(PALADIN));
    }

    @Test
    public void testTileClone() throws Exception {
        Entity a = new TileEntityBuilder().setTileType(PLAIN).setSprite(null).build();
        Entity b = new TileEntityBuilder().setTileType(PLAIN).setSprite(null).build();

        ComponentMapper<TileUnitInteraction> tileComponentMapper = ComponentMapper.getFor(TileUnitInteraction.class);

        // Assert that, even though we're creating two identical tiles, their components don't point to the same memory address, so that we can modify them separately
        assertFalse(tileComponentMapper.get(a) == tileComponentMapper.get(b));
    }

    @Test
    public void testUnit() throws Exception {
        Map<UnitStatLabels, Integer> stats = new HashMap<UnitStatLabels, Integer>() {{
            put(MAX_HP, 16);
            put(STRENGTH, 4);
            put(SKILL, 7);
            put(SPEED, 9);
            put(LUCK, 5);
            put(DEFENSE, 2);
            put(RESISTANCE, 0);
            put(CON, 5);
            put(MOVE, 5);
        }};
        Map<WeaponTypes, WeaponProficiencyLevels> proficiency = new HashMap<WeaponTypes, WeaponProficiencyLevels>() {{
            put(SWORD, D);
        }};

        List<Item> items = new ArrayList<Item>() {{
            // TODO
            add(new Item.Weapon(new IronSword()));
        }};

        Entity lyn = new UnitEntityBuilder()
                .setName("Lyn")
                .setSprite(null)
                .setPosition(0,0)
                .setStats(stats)
                .setClass(LORD_LYN)
                .setWeaponProficiency(proficiency)
                .setInventory(items)
                .build();

        assertEquals(D, ComponentMapper.getFor(UnitWeaponProficiency.class).get(lyn).get(SWORD));
        assertEquals("Lyn", ComponentMapper.getFor(NameComponent.class).get(lyn).getName());
        assertEquals(5, (int)ComponentMapper.getFor(UnitStats.class).get(lyn).get(MOVE));
        assertEquals(16, (int)ComponentMapper.getFor(UnitStats.class).get(lyn).get(CURRENT_HP));
        assertEquals("Iron Sword", ComponentMapper.getFor(Inventory.class).get(lyn).getItems().get(0).asWeapon().getName());

        assertEquals(46, (int)ComponentMapper.getFor(Inventory.class).get(lyn).getItems().get(0).asWeapon().getCurrentDurability());
        ComponentMapper.getFor(Inventory.class).get(lyn).getItems().get(0).asWeapon().decreaseDurability();
        assertEquals(45, (int)ComponentMapper.getFor(Inventory.class).get(lyn).getItems().get(0).asWeapon().getCurrentDurability());

        assertEquals(5, (int)ComponentMapper.getFor(Inventory.class).get(lyn).getItems().get(0).asWeapon().getMight());
        assertEquals(1, ComponentMapper.getFor(Inventory.class).get(lyn).getItems().size());
        assertEquals(LORD_LYN, ComponentMapper.getFor(UnitClass.class).get(lyn).getUnitClass());
        assertEquals(0, ComponentMapper.getFor(PositionComponent.class).get(lyn).x);
    }

}