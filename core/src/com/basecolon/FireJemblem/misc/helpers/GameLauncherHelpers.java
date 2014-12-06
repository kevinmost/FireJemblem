package com.basecolon.FireJemblem.misc.helpers;

import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.entity.unit.UnitEntityBuilder;
import com.basecolon.FireJemblem.constants.component.item.weapon.PhysicalWeaponTemplate;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.FireJemblem.misc.items.Item;
import com.basecolon.FireJemblem.misc.items.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels.*;

public class GameLauncherHelpers {
    // TODO: These are just methods to aid testing, we need something more robust later to make "modular" characters
    public static Entity createLyn() {
        Map<WeaponTypes, WeaponProficiencyLevels> proficiency = new HashMap<WeaponTypes, WeaponProficiencyLevels>() {{
            put(WeaponTypes.SWORD, WeaponProficiencyLevels.D);
        }};
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

        List<Item> items = new ArrayList<Item>() {{
            add(new Weapon(PhysicalWeaponTemplate.IRON_SWORD));
        }};

        return new UnitEntityBuilder()
                .setClass(ClassTypes.LORD_LYN)
                .setName("Lyn")
                .setPosition(1, 1)
                .setWeaponProficiency(proficiency)
                .setStats(stats)
                .setInventory(items)
                .setSprite(null)
                .build()
                ;
    }

    public static Entity createHector() {
        Map<UnitStatLabels, Integer> stats = new HashMap<UnitStatLabels, Integer>() {{
            put(MAX_HP, 19);
            put(STRENGTH, 7);
            put(SKILL, 4);
            put(SPEED, 5);
            put(LUCK, 3);
            put(DEFENSE, 8);
            put(RESISTANCE, 0);
            put(CON, 13);
            put(MOVE, 5);
        }};

        Map<WeaponTypes, WeaponProficiencyLevels> proficiency = new HashMap<WeaponTypes, WeaponProficiencyLevels>() {{
            put(WeaponTypes.AXE, WeaponProficiencyLevels.C);
        }};

        List<Item> items = new ArrayList<Item>() {{
            add(new Weapon(PhysicalWeaponTemplate.HAND_AXE));// TODO: Put a Hand Axe here once we implement it
        }};

        return new UnitEntityBuilder()
                .setClass(ClassTypes.LORD_HECTOR)
                .setInventory(items)
                .setName("Hector")
                .setPosition(1, 2)
                .setSprite(null)
                .setStats(stats)
                .setWeaponProficiency(proficiency)
                .build()
                ;
    }
}
