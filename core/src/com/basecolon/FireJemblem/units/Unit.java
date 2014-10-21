package com.basecolon.FireJemblem.units;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.basecolon.FireJemblem.combat.SkirmishBuilder;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.items.usables.Usable;
import com.basecolon.FireJemblem.items.weapons.Weapon;
import com.basecolon.FireJemblem.units.containers.inventory.Inventory;
import com.basecolon.FireJemblem.units.containers.stats.UnitStats;
import com.basecolon.FireJemblem.units.containers.weapons.WeaponProficiency;

import java.util.List;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class Unit extends Entity {

    private Texture characterPortrait;

    private String name;

    private int level;
    private int experience;
    private ClassTypes classType;

    private UnitStats stats;

    private Inventory inventory = new Inventory();
    private WeaponProficiency proficiency = new WeaponProficiency();

    public Unit(Texture characterPortrait, String name, int level, int experience, ClassTypes classType, UnitStats stats, Inventory inventory, WeaponProficiency proficiency) {
        this.characterPortrait = characterPortrait;
        this.name = name;

        this.level = level;
        this.experience = experience;
        this.classType = classType;

        this.stats = stats;

        this.inventory = inventory;
        this.proficiency = proficiency;
    }

    public void gainExperience(int experience) {
        this.experience += experience;

        while(this.experience > 100) {
            levelUp();
        }
    }
    public int getLevel() {
        return level;
    }
    public void levelUp() {
        // TODO Display stats screen
        level++;
        experience -= 100;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public String getName() {
        return name;
    }
    public ClassTypes getClassType() {
        return classType;
    }
    public WeaponProficiency getProficiency() {
        return proficiency;
    }
    public UnitStats getStats() {
        return stats;
    }

    /**
     * Checks if this Unit can use a given Weapon
     * @param weapon The weapon to check
     * @return true if character can use weapon, false if not
     */
    public boolean canIWield(Weapon weapon) {

        // Get the rank of this weapon we are checking
        WeaponProficiencyLevels weaponsRequiredRank = weapon.getRank();

        // If the weapon is a "Prf" weapon, we have to do special checking
        if (weapon.getRank() == WeaponProficiencyLevels.PRF) {
            // Simply check if the character's list of preferred weapons contains this weapon, and return true if it does
            List<Class<? extends Weapon>> charactersPreferredWeapons = this.getProficiency().getPreferredWeapons();
            return charactersPreferredWeapons.contains(weapon.getClass());
        }
        else {
            // Get the type of this weapon (ex: Sword, lance, ...)
            WeaponTypes weaponsType = weapon.getType();

            // Get this character's proficiency with that weapon
            WeaponProficiencyLevels characterProficiencyInWeaponType = this.getProficiency().getWeapons().get(weaponsType);

            // Return true if this character's skill is good enough to use this weapon
            return characterProficiencyInWeaponType.getNumericRank() >= weaponsRequiredRank.getNumericRank();
        }
    }

    public void use(Usable use) {
        //noinspection deprecation
        use.useOn(this);
        use.lowerDurability();
    }

    public SkirmishBuilder attack(Unit unit) {
        return new SkirmishBuilder(this, unit);
    }
}
