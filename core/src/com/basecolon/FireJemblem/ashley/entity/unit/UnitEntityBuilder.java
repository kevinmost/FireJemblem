package com.basecolon.FireJemblem.ashley.entity.unit;

import com.badlogic.ashley.core.Component;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UnitEntityBuilder extends EntityBuilder {

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

    public UnitEntityBuilder setName(String name) {
        put(NameComponent.class, new NameComponent(name));
        return this;
    }

    public UnitEntityBuilder setSprite(Sprite sprite) {
        put(SpriteComponent.class, new SpriteComponent(sprite));
        return this;
    }

    public UnitEntityBuilder setInventory(List<Item> list) {
        put(Inventory.class, new Inventory(list));
        return this;
    }
    public UnitEntityBuilder setInventory(Item... list) {
        put(Inventory.class, new Inventory(Arrays.asList(list)));
        return this;
    }
    public UnitEntityBuilder setInventory() {
        put(Inventory.class, new Inventory(new ArrayList<>()));
        return this;
    }

    public UnitEntityBuilder setWeaponProficiency(Map<WeaponTypes, WeaponProficiencyLevels> proficiency) {
        put(UnitWeaponProficiency.class, new UnitWeaponProficiency(proficiency));
        return this;
    }

    public UnitEntityBuilder setCondition(ConditionComponent.Condition condition) {
        put(ConditionComponent.class, new ConditionComponent(condition));
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C extends Component> Class<C>[] getRequiredComponents() {
        List<Class<? extends Component>> requiredComponents = new ArrayList<Class<? extends Component>>() {{
            add(UnitStats.class);
            add(UnitClass.class);
            add(PositionComponent.class);
            add(NameComponent.class);
            add(SpriteComponent.class);
            add(Inventory.class);
            add(UnitWeaponProficiency.class);
        }};
        return requiredComponents.toArray(new Class[requiredComponents.size()]);
    }

    @Override
    public <C extends Component> Class<C>[] getAllComponents() {
        return getRequiredComponents();
    }
}
