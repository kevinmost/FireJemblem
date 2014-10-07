package com.basecolon.FireJemblem.entities.weapons;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class WeaponFactory {
    public static <SomeWeapon extends Weapon> SomeWeapon create(Class<SomeWeapon> weapon) {
        try {
            return (SomeWeapon) Class.forName(weapon.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
