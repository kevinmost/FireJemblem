package com.basecolon.FireJemblem.ashley.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.basecolon.FireJemblem.ashley.component.item.ItemIdentity;
import com.basecolon.FireJemblem.ashley.component.item.ItemStats;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.component.unit.UnitIdentity;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;
import com.basecolon.FireJemblem.ashley.component.world.Networked;
import com.basecolon.FireJemblem.ashley.component.world.Selectable;
import com.basecolon.FireJemblem.ashley.component.world.Transform;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An {@link Entity} is just a bag of components. It never even gets instantiated directly by us.
 * All we really need to do is define what {@link Component}s make up the Entity
 * @author kevinmost
 * @date 10/9/14
 */
public enum FireEmblemEntities {
    TILE(
            ItemIdentity.class,
            Transform.class
    ),
    UNIT(
            UnitStats.class,
            UnitIdentity.class,
            Inventory.class,
            Transform.class
    ),
    FRIEND_UNIT(
            UNIT,
            Selectable.class
    ),
    ENEMY_UNIT(
            UNIT,
            Networked.class
    ),
    ITEM(
            ItemIdentity.class,
            ItemStats.class
    )
    ;

    /**
     * Allows an entity to be defined as an enum consisting of components
     * @param components The components to use to create this entity
     */
    @SafeVarargs
    FireEmblemEntities(Class<? extends Component>... components) {
        setComponents(components);
    }

    /**
     * Alternate constructor that allows you to define the fact that an entity should "inherit" off its parent entity
     * Essentially takes all of the components of the inheritedEntity and adds all of the given components to it
     * @param inheritedEntity The base entity
     * @param components The components specific to this "child" entity
     */
    @SafeVarargs
    FireEmblemEntities(FireEmblemEntities inheritedEntity, Class<? extends Component>... components) {
        List<Class<? extends Component>> inheritedEntityAsList = Arrays.asList(inheritedEntity.getComponents());
        Collections.addAll(inheritedEntityAsList, components);
        
        setComponents(components);
    }

    private Class<? extends Component>[] components;

    private void setComponents(Class<? extends Component>[] components) {
        this.components = components;
    }

    /**
     * Get all of the components that make up this enum element. Returns as an array because the {@link Family#getFor(Class[])} method takes varargs, not a List
     * @return An array containing all of the components that make up this enum
     */
    public Class<? extends Component>[] getComponents() {
        return components;
    }

    /**
     * A convenience method that you can use to get a family of {@link Entity}s that require just these {@link Component}s
     * @return The {@link Family} of Entities that is made up by these Components
     */
    public Family getFamilyOf() {
        return Family.getFor(this.getComponents());
    }
}
