package com.basecolon.FireJemblem;

import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.FireJemblem.ashley.system.unit.EquippedItemSystem;
import com.basecolon.FireJemblem.constants.FireJemblem;
import com.basecolon.FireJemblem.constants.component.item.weapon.PhysicalWeaponTemplate;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.FireJemblem.misc.helpers.EntityHelpers;
import com.basecolon.FireJemblem.misc.helpers.GameLauncherHelpers;
import com.basecolon.FireJemblem.misc.items.Weapon;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class UnitManipulationTest {
    private EntityHelpers.Mappers mappers;
    private EquippedItemSystem equippedItemSystem;

    @Before
    public void setup() {
        mappers = EntityHelpers.mappersFor(new UnitEntityBuilder());
        equippedItemSystem = new EquippedItemSystem();
    }

    @Test
    public void equipItem() {
        Entity lyn = GameLauncherHelpers.createLyn();

        Inventory lynsInventory = mappers.getMapperFor(Inventory.class).get(lyn);

        lynsInventory.removeItem(0);
        lynsInventory.addItem(new Weapon(PhysicalWeaponTemplate.WO_DAO));

        assertEquals(1, lynsInventory.getItems().size());
        assertEquals("Wo Dao", lynsInventory.getItems().get(0).getName());

        lynsInventory.addItem(new Weapon(PhysicalWeaponTemplate.IRON_SWORD));

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

        lynsInventory.addItem(new Weapon(PhysicalWeaponTemplate.MANI_KATTI));
        lynsInventory.addItem(new Weapon(PhysicalWeaponTemplate.IRON_SWORD));

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
                .setInventory(new Weapon(PhysicalWeaponTemplate.MANI_KATTI), new Weapon(PhysicalWeaponTemplate.WO_DAO))
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
