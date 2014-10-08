package com.basecolon.FireJemblem;

import com.basecolon.FireJemblem.constants.classes.ClassTypes;
import com.basecolon.FireJemblem.constants.stats.UnitStatLabels;
import com.basecolon.FireJemblem.constants.weapons.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;
import com.basecolon.FireJemblem.entities.items.ItemFactory;
import com.basecolon.FireJemblem.entities.items.ItemCastException;
import com.basecolon.FireJemblem.entities.items.usables.Usable;
import com.basecolon.FireJemblem.entities.items.usables.healing.Vulnerary;
import com.basecolon.FireJemblem.entities.items.weapons.Weapon;
import com.basecolon.FireJemblem.entities.items.weapons.swords.IronSword;
import com.basecolon.FireJemblem.entities.items.weapons.swords.KillingEdge;
import com.basecolon.FireJemblem.entities.items.weapons.swords.ManiKatti;
import com.basecolon.FireJemblem.entities.units.Unit;
import com.basecolon.FireJemblem.entities.units.containers.inventory.Inventory;
import com.basecolon.FireJemblem.entities.units.containers.inventory.InventorySlots;
import com.basecolon.FireJemblem.entities.units.containers.stats.UnitStats;
import com.basecolon.FireJemblem.entities.units.containers.weapons.WeaponProficiency;
import org.junit.Test;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class MainTester {

    @Test
    public void testUsableItem() throws ItemCastException {
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
    public void testIfCharacterCanWield() throws ItemCastException {
        Unit lyn = initializeLyn();

        Inventory lynsInventory = lyn.getInventory();

        Weapon maniKatti = lynsInventory.getItemAsWeapon(InventorySlots.SLOT_1);
        Weapon killingEdge = lynsInventory.getItemAsWeapon(InventorySlots.SLOT_2);
        Weapon ironSword = lynsInventory.getItemAsWeapon(InventorySlots.SLOT_3);

        // Lyn should be able to use her Mani Katti considering it is her special weapon
        System.out.println(lyn.getName() + (lyn.canIWield(maniKatti) ? " can " : " cannot ") + "use the " + maniKatti.getName());

        // Lyn shouldn't be able to use the Killing Edge, she is not C rank
        System.out.println(lyn.getName() + (lyn.canIWield(killingEdge) ? " can " : " cannot ") + "use the " + killingEdge.getName());

        // Lyn should be able to use the Iron Sword, she is high enough rank
        System.out.println(lyn.getName() + (lyn.canIWield(ironSword) ? " can " : " cannot ") + "use the " + ironSword.getName());
    }

    @Test
    public void testWeaponDurabilityDecrease() throws ItemCastException {
        Unit lyn = initializeLyn();

        // Get Lyn's inventory and pull the SLOT_1 item from it
        Inventory lynsInventory = lyn.getInventory();
        Weapon lynsSlot1Item = lynsInventory.getItemAsWeapon(InventorySlots.SLOT_1);

        // Print some info about her sword
        System.out.println(lyn.getName() + "'s first item is " + lynsSlot1Item.getName() + "");
        System.out.println("It has a might of " + lynsSlot1Item.getMight()  + " and a durability of " + lynsSlot1Item.getDurability());
        System.out.println();

        // Do some stuff to the sword
        System.out.println(lyn.getName() + " attacks an enemy!");
        lynsSlot1Item.lowerDurability(); // Let's assume the sword hit something!

        // Now her sword should have less durability
        System.out.println(lyn.getName() + " just got a hit! Now her " + lynsSlot1Item.getName() + " has a durability of " + lynsSlot1Item.getDurability());
        System.out.println();
    }

    private Unit initializeLyn() {
        return new Unit(
                null, // TODO Give Lyn a portrait
                "Lyn", // Lyn's name
                1, // Lyn's level
                0, // Lyn's current XP
                ClassTypes.MERCENARY, // Lyn's class
                new UnitStats(16, 4, -1, 7, 9, 5, 2, 0), // Lyn's stats
                new Inventory() // Lyn's Inventory
                        .setItem(
                                InventorySlots.SLOT_1,
                                ItemFactory.create(ManiKatti.class)) // Give Lyn a Mani Katti (this is a special weapon only she can use)
                        .setItem(
                                InventorySlots.SLOT_2,
                                ItemFactory.create(KillingEdge.class)) // Give Lyn a Killing Edge (she can't use it though, she only has a D in swords)
                        .setItem(
                                InventorySlots.SLOT_3,
                                ItemFactory.create(IronSword.class)) // Give Lyn an Iron Sword
                        .setItem(
                                InventorySlots.SLOT_4,
                                ItemFactory.create(Vulnerary.class)) // Give Lyn a Vulnerary too
                ,
                new WeaponProficiency() // Lyn's weapon proficiencies
                        .setWeaponProficiencyLevel(WeaponTypes.SWORD, WeaponProficiencyLevels.D) // Lyn can use swords
                        .setPreferredWeapon(ManiKatti.class) // Lyn can also use the Mani Katti specifically
        );
    }
}
