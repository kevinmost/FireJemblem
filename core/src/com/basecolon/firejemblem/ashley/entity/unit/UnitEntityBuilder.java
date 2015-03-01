package com.basecolon.firejemblem.ashley.entity.unit;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.firejemblem.ashley.component.HealthComponent;
import com.basecolon.firejemblem.ashley.component.NameComponent;
import com.basecolon.firejemblem.ashley.component.PositionComponent;
import com.basecolon.firejemblem.ashley.component.SpriteComponent;
import com.basecolon.firejemblem.ashley.component.unit.*;
import com.basecolon.firejemblem.ashley.entity.EntityBuilder;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.firejemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.firejemblem.constants.component.unit.UnitStatLabels;
import com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.firejemblem.misc.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UnitEntityBuilder extends EntityBuilder {

    public UnitEntityBuilder setHealth(int max) {
        put(HealthComponent.class, new HealthComponent(max, max));
        return this;
    }

    public UnitEntityBuilder setStats(Map<UnitStatLabels, Integer> stats) {
        put(UnitStatsComponent.class, new UnitStatsComponent(stats));
        return this;
    }

    public UnitEntityBuilder setClass(ClassTypes unitClass) {
        put(UnitClassComponent.class, new UnitClassComponent(unitClass));
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
        put(InventoryComponent.class, new InventoryComponent(list));
        return this;
    }
    public UnitEntityBuilder setInventory(Item... list) {
        put(InventoryComponent.class, new InventoryComponent(Arrays.asList(list)));
        return this;
    }
    public UnitEntityBuilder setInventory() {
        put(InventoryComponent.class, new InventoryComponent(new ArrayList<>()));
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

    public UnitEntityBuilder setDecoratorComponent(DecoratorComponent component) {
        put(DecoratorComponent.class, component);
        return this;
    }


    @SuppressWarnings("unchecked")
    @Override
    public <C extends Component> Class<C>[] getRequiredComponents() {
        List<Class<? extends Component>> requiredComponents = new ArrayList<Class<? extends Component>>() {{
            add(HealthComponent.class);
            add(UnitStatsComponent.class);
            add(UnitClassComponent.class);
            add(PositionComponent.class);
            add(NameComponent.class);
            add(SpriteComponent.class);
            add(InventoryComponent.class);
            add(UnitWeaponProficiency.class);
            add(DecoratorComponent.class);
        }};
        return requiredComponents.toArray(new Class[requiredComponents.size()]);
    }

    @Override
    public <C extends Component> Class<C>[] getAllComponents() {
        List<Class<? extends Component>> allComponents = new ArrayList<Class<? extends Component>>(Arrays.asList(getRequiredComponents())) {{
            add(ConditionComponent.class);
        }};

        return allComponents.toArray(new Class[allComponents.size()]);
    }
}
