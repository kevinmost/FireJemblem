package com.basecolon.firejemblem;

import com.badlogic.ashley.core.Entity;
import com.basecolon.firejemblem.ashley.component.unit.DecoratorComponent;
import com.basecolon.firejemblem.ashley.component.unit.InventoryComponent;
import com.basecolon.firejemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.firejemblem.ashley.system.unit.EquippedItemSystem;
import com.basecolon.firejemblem.constants.FireJemblem;
import com.basecolon.firejemblem.constants.component.item.weapon.template.PhysicalWeaponTemplate;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.firejemblem.misc.helpers.EntityHelpers;
import com.basecolon.firejemblem.misc.helpers.GameLauncherHelpers;
import com.basecolon.firejemblem.misc.items.Weapon;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

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

        InventoryComponent lynsInventory = mappers.getMapperFor(InventoryComponent.class).get(lyn);
        List<InventoryComponent.InventoryItem> lynsItems = lynsInventory.items;

        lynsItems.clear();

        lynsItems.add(new InventoryComponent.InventoryItem(new Weapon(PhysicalWeaponTemplate.WO_DAO)));

        assertEquals(1, lynsItems.size());
        assertEquals("Wo Dao", lynsItems.get(0).item.getName());

        lynsItems.add(new InventoryComponent.InventoryItem(new Weapon(PhysicalWeaponTemplate.IRON_SWORD)));

        FireJemblem.engine.update(FireJemblem.deltaTime);

        assertEquals(2, lynsItems.size());
        assertEquals("Iron Sword", lynsItems.get(1).item.getName());

        assertEquals("Iron Sword", lynsInventory.getEquippedWeapon().getName());


    }

    @Test
    public void prfEquip() {
        Entity lyn = GameLauncherHelpers.createLyn();

        InventoryComponent lynsInventory = mappers.getMapperFor(InventoryComponent.class).get(lyn);
        List<InventoryComponent.InventoryItem> lynsItems = lynsInventory.items;

        lynsItems.clear();

        lynsItems.add(new InventoryComponent.InventoryItem(new Weapon(PhysicalWeaponTemplate.MANI_KATTI)));
        lynsItems.add(new InventoryComponent.InventoryItem(new Weapon(PhysicalWeaponTemplate.IRON_SWORD)));

        FireJemblem.engine.update(FireJemblem.deltaTime);

        assertEquals("Mani Katti", lynsInventory.getEquippedWeapon().getName());
    }

    @Test
    public void prfUnequippable() {
        HashMap<UnitStatLabels, Integer> stats = new HashMap<>();

        HashMap<WeaponTypes, WeaponProficiencyLevels> proficiency = new HashMap<>();
        proficiency.put(WeaponTypes.SWORD, WeaponProficiencyLevels.S);

        Entity swordmaster = new UnitEntityBuilder()
                .setClass(ClassTypes.SWORDMASTER)
                .setInventory(new Weapon(PhysicalWeaponTemplate.MANI_KATTI), new Weapon(PhysicalWeaponTemplate.WO_DAO))
                .setName("Guy")
                .setPosition(0, 0)
                .setSprite(null)
                .setHealth(15)
                .setStats(stats)
                .setWeaponProficiency(proficiency)
                .setDecoratorComponent(new DecoratorComponent())
                .build();

        InventoryComponent swordmasterInventory = mappers.getMapperFor(InventoryComponent.class).get(swordmaster);

        FireJemblem.engine.update(FireJemblem.deltaTime);

        assertEquals("Wo Dao", swordmasterInventory.getEquippedWeapon().getName());
    }
}
