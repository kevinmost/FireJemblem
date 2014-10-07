package com.basecolon.FireJemblem;

import com.basecolon.FireJemblem.constants.classes.ClassTypes;
import com.basecolon.FireJemblem.constants.weapons.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.weapons.WeaponTypes;
import com.basecolon.FireJemblem.entities.units.Unit;
import com.basecolon.FireJemblem.entities.units.containers.inventory.Inventory;
import com.basecolon.FireJemblem.entities.units.containers.weapons.WeaponProficiency;
import com.basecolon.FireJemblem.entities.weapons.Weapon;
import com.basecolon.FireJemblem.entities.weapons.swords.ManiKatti;

import java.util.List;
import java.util.Map;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class MainTester {
    public static void main(String[] args) {
        Unit lyn = new Unit(
                null,
                "Lyn",
                10,
                0,
                ClassTypes.MERCENARY,
                new Inventory(),
                new WeaponProficiency()
                    .setWeaponProficiencyLevel(WeaponTypes.SWORD, WeaponProficiencyLevels.D)
                    .setPreferredWeapon(ManiKatti.class)
        );

        System.out.println("Character: " + lyn.getName());
        System.out.println("Level: " + lyn.getLevel());
        System.out.println("Class: " + lyn.getClassType().getClassName());

        Map<WeaponTypes, WeaponProficiencyLevels> lynsWeapons = lyn.getProficiency().getWeapons();
        List<Class<? extends Weapon>> lynsPreferredWeapons = lyn.getProficiency().getPreferredWeapons();

        System.out.print("Weapon classes: ");
        for (Map.Entry<WeaponTypes, WeaponProficiencyLevels> weaponProficiencyLevel : lynsWeapons.entrySet()) {
            System.out.print(weaponProficiencyLevel.getKey() + " => " + weaponProficiencyLevel.getValue() + "; ");
        }
        System.out.println();

        System.out.print("Preferred weapons: ");
        for (Class<? extends Weapon> lynsPreferredWeapon : lynsPreferredWeapons) {
            System.out.print(lynsPreferredWeapon.getSimpleName() + "; ");
        }
        System.out.println();
    }
}
