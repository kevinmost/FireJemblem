package com.basecolon.FireJemblem;

import com.basecolon.FireJemblem.constants.classes.ClassTypes;
import com.basecolon.FireJemblem.constants.stats.UnitStatLabels;
import com.basecolon.FireJemblem.constants.weapons.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;
import com.basecolon.FireJemblem.entities.items.Item;
import com.basecolon.FireJemblem.entities.items.ItemFactory;
import com.basecolon.FireJemblem.entities.items.usables.Usable;
import com.basecolon.FireJemblem.entities.items.usables.healing.Vulnerary;
import com.basecolon.FireJemblem.entities.items.weapons.Weapon;
import com.basecolon.FireJemblem.entities.items.weapons.axes.IronAxe;
import com.basecolon.FireJemblem.entities.items.weapons.swords.IronSword;
import com.basecolon.FireJemblem.entities.items.weapons.swords.KillingEdge;
import com.basecolon.FireJemblem.entities.items.weapons.swords.ManiKatti;
import com.basecolon.FireJemblem.entities.units.Unit;
import com.basecolon.FireJemblem.entities.units.containers.inventory.Inventory;
import com.basecolon.FireJemblem.entities.units.containers.inventory.InventorySlots;
import com.basecolon.FireJemblem.entities.units.containers.stats.UnitStats;
import com.basecolon.FireJemblem.entities.units.containers.weapons.WeaponProficiency;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class MainTester {

    @Test
    public void testUsableItem() {
        Unit lyn = initializeLyn();
        Inventory lynsInventory = lyn.getInventory();

        // Pull out Lyn's vulnerary
        Usable vulnerary = lynsInventory.getItemAsUsable(InventorySlots.SLOT_4);

        // Store Lyn's stats
        UnitStats lynsStats = lyn.getStats();

        // Lyn was injured!
        lynsStats.setCurrentHP(1);

        System.out.println(lyn.getName() + " was injured! Current HP: " + lynsStats.getCurrentHP() + "/" + lynsStats.getStats().get(UnitStatLabels.HP));
        System.out.println("Fortunately, " + lyn.getName() + " has a " + vulnerary.getName() + " with " + vulnerary.getDurability() + " uses left on it");

        // Lyn uses her vulnerary
        vulnerary.useOn(lyn);

        System.out.println(lyn.getName() + " used her vulnerary! Current HP: " + lynsStats.getCurrentHP() + "/" + lynsStats.getStats().get(UnitStatLabels.HP));
        System.out.println(lyn.getName() + "'s vulnerary now has " + vulnerary.getDurability() + "/" + vulnerary.getMaxDurability() + " uses left");
    }

    @Test
    public void testIfCharacterCanWield() {
        Unit lyn = initializeLyn();
        Inventory lynsInventory = lyn.getInventory();

        Weapon maniKatti = lynsInventory.getItemAsWeapon(InventorySlots.SLOT_1);
        Weapon killingEdge = lynsInventory.getItemAsWeapon(InventorySlots.SLOT_2);
        Weapon ironSword = lynsInventory.getItemAsWeapon(InventorySlots.SLOT_3);
        Weapon ironAxe = lynsInventory.getItemAsWeapon(InventorySlots.SLOT_5);

        // Lyn should be able to use her Mani Katti considering it is her special weapon
        System.out.println(lyn.getName() + (lyn.canIWield(maniKatti) ? " can " : " cannot ") + "use the " + maniKatti.getName());

        // Lyn shouldn't be able to use the Killing Edge, she is not C rank
        System.out.println(lyn.getName() + (lyn.canIWield(killingEdge) ? " can " : " cannot ") + "use the " + killingEdge.getName());

        // Lyn should be able to use the Iron Sword, she is high enough rank
        System.out.println(lyn.getName() + (lyn.canIWield(ironSword) ? " can " : " cannot ") + "use the " + ironSword.getName());

        // Lyn should not be able to use the Iron Axe, she is a Mercenary
        System.out.println(lyn.getName() + (lyn.canIWield(ironAxe) ? " can " : " cannot " ) + "use the " + ironAxe.getName());
    }

    @Test
    public void testWeaponDurabilityDecrease() {
        Unit lyn = initializeLyn();
        Inventory lynsInventory = lyn.getInventory();

        // Get Lyn's slot 1 item
        Weapon maniKatti = lynsInventory.getItemAsWeapon(InventorySlots.SLOT_1);

        // Print some info about her sword
        System.out.println(lyn.getName() + "'s first item is " + maniKatti.getName() + "");
        System.out.println("It has a might of " + maniKatti.getMight()  + " and a durability of " + maniKatti.getDurability());
        System.out.println();

        // Do some stuff to the sword
        System.out.println(lyn.getName() + " attacks an enemy!");
        maniKatti.lowerDurability(); // Let's assume the sword hit something!

        // Now her sword should have less durability
        System.out.println(lyn.getName() + " just got a hit! Now her " + maniKatti.getName() + " has a durability of " + maniKatti.getDurability());
        System.out.println();
    }

    private Unit initializeLyn() {
        final Map<InventorySlots, Class<? extends Item>> itemsToGiveLyn = new HashMap<InventorySlots, Class<? extends Item>>() {{
            put(InventorySlots.SLOT_1, ManiKatti.class); // Give Lyn her preferred weapon
            put(InventorySlots.SLOT_2, KillingEdge.class); // Give Lyn a regular sword that she isn't skilled enough to use  yet
            put(InventorySlots.SLOT_3, IronSword.class); // Give Lyn a regular sword she can use
            put(InventorySlots.SLOT_4, Vulnerary.class); // Give Lyn a healing Usable
            put(InventorySlots.SLOT_5, IronAxe.class); // Give Lyn an item she can't use until she promotes
        }};

        return new Unit(
                null, // TODO Give Lyn a portrait
                "Lyn", // Lyn's name
                1, // Lyn's level
                0, // Lyn's current XP
                ClassTypes.MERCENARY, // Lyn's class
                new UnitStats(16, 4, -1, 7, 9, 5, 2, 0), // Lyn's stats
                new Inventory() {{
                    // Give Lyn all of the items we defined above
                    for (Map.Entry<InventorySlots, Class<? extends Item>> itemToGiveLyn : itemsToGiveLyn.entrySet()) {
                        setItem(itemToGiveLyn.getKey(), ItemFactory.create(itemToGiveLyn.getValue()));
                    }
                }},
                new WeaponProficiency() // Lyn's weapon proficiencies
                        .setWeaponProficiencyLevel(WeaponTypes.SWORD, WeaponProficiencyLevels.D) // Lyn can use swords
                        .setPreferredWeapon(ManiKatti.class) // Lyn can also use the Mani Katti specifically
        );
    }
}
