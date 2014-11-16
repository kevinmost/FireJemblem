package com.basecolon.FireJemblem.ashley.entity.unit;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.ashley.component.NameComponent;
import com.basecolon.FireJemblem.ashley.component.SpriteComponent;
import com.basecolon.FireJemblem.ashley.component.unit.*;
import com.basecolon.FireJemblem.ashley.entity.EntityBuilder;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.FireJemblem.misc.items.Item;

import java.util.Map;

public class UnitEntityBuilder extends EntityBuilder {


    @Required(componentsSetByThisMethod = UnitStats.class)
    public UnitEntityBuilder setStats(Map<UnitStatLabels, Integer> stats) {
        put(UnitStats.class, new UnitStats(stats));
        return this;
    }


    public UnitEntityBuilder setClass(ClassTypes unitClass) {
        put(UnitClass.class, new UnitClass(unitClass));
        return this;
    }

    public UnitEntityBuilder setPosition(int x, int y) {
        put(PositionComponent.class, new PositionComponent(x, y));
        return this;
    }

    @Required(componentsSetByThisMethod = NameComponent.class)
    public UnitEntityBuilder setName(String name) {
        put(NameComponent.class, new NameComponent(name));
        return this;
    }

    @Required(componentsSetByThisMethod = SpriteComponent.class)
    public UnitEntityBuilder setSprite(Sprite sprite) {
        put(SpriteComponent.class, new SpriteComponent(sprite));
        return this;
    }

    @Required(componentsSetByThisMethod = Inventory.class)
    public UnitEntityBuilder setInventory(Inventory.LimitedList<Item> list) {
        put(Inventory.class, new Inventory(list));
        return this;
    }

    @Required(componentsSetByThisMethod = UnitWeaponProficiency.class)
    public UnitEntityBuilder setWeaponProficiency(Map<WeaponTypes, WeaponProficiencyLevels> proficiency) {
        put(UnitWeaponProficiency.class, new UnitWeaponProficiency(proficiency));
        return this;
    }
}
