package com.basecolon.firejemblem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.basecolon.firejemblem.ashley.component.HealthComponent;
import com.basecolon.firejemblem.ashley.component.NameComponent;
import com.basecolon.firejemblem.ashley.component.PositionComponent;
import com.basecolon.firejemblem.ashley.component.unit.*;
import com.basecolon.firejemblem.ashley.component.world.TileStatsComponent;
import com.basecolon.firejemblem.ashley.entity.EntityBuilder;
import com.basecolon.firejemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.firejemblem.ashley.entity.world.TileEntityBuilder;
import com.basecolon.firejemblem.constants.component.item.weapon.template.PhysicalWeaponTemplate;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.firejemblem.misc.items.Item;
import com.basecolon.firejemblem.misc.items.Weapon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.basecolon.firejemblem.constants.component.item.weapon.WeaponProficiencyLevels.D;
import static com.basecolon.firejemblem.constants.component.item.weapon.WeaponTypes.SWORD;
import static com.basecolon.firejemblem.constants.component.unit.UnitStatLabels.*;
import static com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes.*;
import static com.basecolon.firejemblem.constants.component.world.TileConstants.FOREST;
import static com.basecolon.firejemblem.constants.component.world.TileConstants.PLAIN;
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
        ComponentMapper<TileStatsComponent> tileComponentMapper = ComponentMapper.getFor(TileStatsComponent.class);

        assertEquals(1, tileComponentMapper.get(plain).getMoveCost(HERO));
        assertEquals(1, tileComponentMapper.get(plain).getMoveCost(PALADIN));
        assertEquals(2, tileComponentMapper.get(forest).getMoveCost(HERO));
        assertEquals(3, tileComponentMapper.get(forest).getMoveCost(PALADIN));
    }

    @Test
    public void testTileClone() throws Exception {
        Entity a = new TileEntityBuilder().setTileType(PLAIN).setSprite(null).build();
        Entity b = new TileEntityBuilder().setTileType(PLAIN).setSprite(null).build();

        ComponentMapper<TileStatsComponent> tileComponentMapper = ComponentMapper.getFor(TileStatsComponent.class);

        // Assert that, even though we're creating two identical tiles, their components don't point to the same memory address, so that we can modify them separately
        assertFalse(tileComponentMapper.get(a) == tileComponentMapper.get(b));
    }

    @Test
    public void testUnit() throws Exception {
        Map<UnitStatLabels, Integer> stats = new HashMap<UnitStatLabels, Integer>() {{
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
            add(new Weapon(PhysicalWeaponTemplate.IRON_SWORD));
        }};

        Entity lyn = new UnitEntityBuilder()
                .setName("Lyn")
                .setSprite(null)
                .setPosition(0,0)
                .setHealth(16)
                .setStats(stats)
                .setClass(LORD_LYN)
                .setWeaponProficiency(proficiency)
                .setInventory(items)
                .build();

        assertEquals(D, ComponentMapper.getFor(UnitWeaponProficiency.class).get(lyn).get(SWORD));
        assertEquals("Lyn", ComponentMapper.getFor(NameComponent.class).get(lyn).name);
        assertEquals(5, (int)ComponentMapper.getFor(UnitStatsComponent.class).get(lyn).get(MOVE));
        assertEquals(16, lyn.getComponent(HealthComponent.class).getCurrent());
        assertEquals("Iron Sword", ComponentMapper.getFor(InventoryComponent.class).get(lyn).items.get(0).item.as(Weapon.class).getName());

        assertEquals(46, (int)ComponentMapper.getFor(InventoryComponent.class).get(lyn).items.get(0).item.as(Weapon.class).getCurrentDurability());
        ComponentMapper.getFor(InventoryComponent.class).get(lyn).items.get(0).item.as(Weapon.class).decreaseDurability();
        assertEquals(45, (int)ComponentMapper.getFor(InventoryComponent.class).get(lyn).items.get(0).item.as(Weapon.class).getCurrentDurability());

        assertEquals(5, (int)ComponentMapper.getFor(InventoryComponent.class).get(lyn).items.get(0).item.as(Weapon.class).getMight());
        assertEquals(1, ComponentMapper.getFor(InventoryComponent.class).get(lyn).items.size());
        assertEquals(LORD_LYN, ComponentMapper.getFor(UnitClassComponent.class).get(lyn).unitClass);
        assertEquals(0, ComponentMapper.getFor(PositionComponent.class).get(lyn).x);
    }

}