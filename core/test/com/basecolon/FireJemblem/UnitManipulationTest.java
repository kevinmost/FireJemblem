package com.basecolon.FireJemblem;

import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.FireJemblem.ashley.system.SystemMapperHelpers;
import com.basecolon.FireJemblem.ashley.system.unit.EquippedItemSystem;
import com.basecolon.FireJemblem.constants.FireJemblem;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.constants.component.item.weapon.sword.IronSword;
import com.basecolon.FireJemblem.constants.component.item.weapon.sword.ManiKatti;
import com.basecolon.FireJemblem.constants.component.item.weapon.sword.WoDao;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.FireJemblem.misc.helpers.GameLauncherHelpers;
import com.basecolon.FireJemblem.misc.items.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class UnitManipulationTest {
    private SystemMapperHelpers.Mappers mappers;
    private EquippedItemSystem equippedItemSystem;

    @Before
    public void setup() {
        mappers = SystemMapperHelpers.mappersFor(new UnitEntityBuilder());
        equippedItemSystem = new EquippedItemSystem();
    }

    @Test
    public void equipItem() {
        Entity lyn = GameLauncherHelpers.createLyn();

        Inventory lynsInventory = mappers.getMapperFor(Inventory.class).get(lyn);

        lynsInventory.removeItem(0);
        lynsInventory.addItem(new Item.Weapon(new WoDao()));

        assertEquals(1, lynsInventory.getItems().size());
        assertEquals("Wo Dao", lynsInventory.getItems().get(0).getName());

        lynsInventory.addItem(new Item.Weapon(new IronSword()));

        FireJemblem.engine.update(FireJemblem.deltaTime);

        assertEquals(2, lynsInventory.getItems().size());
        assertEquals("Iron Sword", lynsInventory.getItems().get(1).getName());

        assertEquals("Iron Sword", lynsInventory.getEquippedWeapon().getName());


    }

    @Test
    public void prfEquip() {
        Entity lyn = GameLauncherHelpers.createLyn();

        Inventory lynsInventory = mappers.getMapperFor(Inventory.class).get(lyn);
        lynsInventory.removeItem(0);

        lynsInventory.addItem(new Item.Weapon(new ManiKatti()));
        lynsInventory.addItem(new Item.Weapon(new IronSword()));

        FireJemblem.engine.update(FireJemblem.deltaTime);

        assertEquals("Mani Katti", lynsInventory.getEquippedWeapon().getName());
    }

    @Test
    public void prfUnequippable() {

        HashMap<UnitStatLabels, Integer> stats = new HashMap<UnitStatLabels, Integer>();

        HashMap<WeaponTypes, WeaponProficiencyLevels> proficiency = new HashMap<>();
        proficiency.put(WeaponTypes.SWORD, WeaponProficiencyLevels.S);

        Entity swordmaster = new UnitEntityBuilder()
                .setClass(ClassTypes.SWORDMASTER)
                .setInventory(new Item.Weapon(new ManiKatti()), new Item.Weapon(new WoDao()))
                .setName("Guy")
                .setPosition(0, 0)
                .setSprite(null)
                .setStats(stats)
                .setWeaponProficiency(proficiency)
                .build();

        Inventory swordmasterInventory = mappers.getMapperFor(Inventory.class).get(swordmaster);

        FireJemblem.engine.update(FireJemblem.deltaTime);

        assertEquals("Wo Dao", swordmasterInventory.getEquippedWeapon().getName());
    }
}
