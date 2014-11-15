package com.basecolon.FireJemblem.ashley.entity.unit;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.basecolon.FireJemblem.ashley.component.NameComponent;
import com.basecolon.FireJemblem.ashley.component.SpriteComponent;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;
import com.basecolon.FireJemblem.ashley.component.unit.UnitWeaponProficiency;
import com.basecolon.FireJemblem.ashley.entity.EntityBuilder;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponProficiencyLevels;
import com.basecolon.FireJemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.FireJemblem.constants.component.unit.UnitStatLabels;

import java.util.Map;

public class UnitEntityBuilder extends EntityBuilder {

    @Required(componentsSetByThisMethod = UnitStats.class)
    public UnitEntityBuilder setStats(Map<UnitStatLabels, Integer> stats) {
        put(UnitStats.class, new UnitStats(stats));
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

    @Required(componentsSetByThisMethod = UnitWeaponProficiency.class)
    public UnitEntityBuilder setWeaponProficiency(Map<WeaponTypes, WeaponProficiencyLevels> proficiency) {
        put(UnitWeaponProficiency.class, new UnitWeaponProficiency(proficiency));
        return this;
    }
}
