package com.basecolon.FireJemblem;

import com.basecolon.FireJemblem.constants.classes.ClassTypes;
import com.basecolon.FireJemblem.constants.weapons.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;
import com.basecolon.FireJemblem.entities.units.Unit;
import com.basecolon.FireJemblem.entities.units.containers.inventory.Inventory;
import com.basecolon.FireJemblem.entities.units.containers.inventory.InventorySlots;
import com.basecolon.FireJemblem.entities.units.containers.weapons.WeaponProficiency;
import com.basecolon.FireJemblem.entities.weapons.Weapon;
import com.basecolon.FireJemblem.entities.weapons.WeaponFactory;
import com.basecolon.FireJemblem.entities.weapons.swords.IronSword;
import com.basecolon.FireJemblem.entities.weapons.swords.KillingEdge;
import com.basecolon.FireJemblem.entities.weapons.swords.ManiKatti;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class MainTester {
    public static void main(String[] args) {
        testIfCharacterCanWield();
    }

    public static void testIfCharacterCanWield() {
        Unit lyn = initializeLyn();

        Inventory lynsInventory = lyn.getInventory();

        Weapon maniKatti = lynsInventory.getItem(InventorySlots.SLOT_1);
        Weapon killingEdge = lynsInventory.getItem(InventorySlots.SLOT_2);
        Weapon ironSword = lynsInventory.getItem(InventorySlots.SLOT_3);

        // Lyn should be able to use her Mani Katti considering it is her special weapon
        System.out.println(lyn.getName() + (lyn.canIUse(maniKatti) ? " can " : " cannot ") + "use the " + maniKatti.getName());

        // Lyn shouldn't be able to use the Killing Edge, she is not C rank
        System.out.println(lyn.getName() + (lyn.canIUse(killingEdge) ? " can " : " cannot ") + "use the " + killingEdge.getName());

        // Lyn should be able to use the Iron Sword, she is high enough rank
        System.out.println(lyn.getName() + (lyn.canIUse(ironSword) ? " can " : " cannot ") + "use the " + ironSword.getName());
    }

    public static void testWeaponDurabilityDecrease() {
        Unit lyn = initializeLyn();

        // Get Lyn's inventory and pull the SLOT_1 item from it
        Inventory lynsInventory = lyn.getInventory();
        Weapon lynsSlot1Item = lynsInventory.getItem(InventorySlots.SLOT_1);

        // Print some info about her sword
        System.out.println(lyn.getName() + "'s first item is " + lynsSlot1Item.getName() + "");
        System.out.println("It has a might of " + lynsSlot1Item.getMight()  + " and a durability of " + lynsSlot1Item.getCurrentDurability());
        System.out.println();

        // Do some stuff to the sword
        System.out.println(lyn.getName() + " attacks an enemy!");
        lynsSlot1Item.degradeWeapon(); // Let's assume the sword hit something!

        // Now her sword should have less durability
        System.out.println(lyn.getName() + " just got a hit! Now her " + lynsSlot1Item.getName() + " has a durability of " + lynsSlot1Item.getCurrentDurability());
        System.out.println();
    }

    private static Unit initializeLyn() {
        return new Unit(
                null, // TODO Give Lyn a portrait
                "Lyn", // Lyn's name
                10, // Lyn's level
                0, // Lyn's current XP
                ClassTypes.MERCENARY, // Lyn's class
                new Inventory() // Lyn's Inventory
                        .setItem(
                                InventorySlots.SLOT_1,
                                WeaponFactory.create(ManiKatti.class)) // Give Lyn a Mani Katti (this is a special weapon only she can use)
                        .setItem(
                                InventorySlots.SLOT_2,
                                WeaponFactory.create(KillingEdge.class)) // Give Lyn a Killing Edge (she can't use it though, she only has a D in swords)
                        .setItem(
                                InventorySlots.SLOT_3,
                                WeaponFactory.create(IronSword.class)) // Give Lyn an Iron Sword
                ,
                new WeaponProficiency() // Lyn's weapon proficiencies
                        .setWeaponProficiencyLevel(WeaponTypes.SWORD, WeaponProficiencyLevels.D) // Lyn can use swords
                        .setPreferredWeapon(ManiKatti.class) // Lyn can also use the Mani Katti specifically
        );
    }
}
