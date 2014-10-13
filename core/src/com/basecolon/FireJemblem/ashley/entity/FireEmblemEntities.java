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
import com.basecolon.FireJemblem.ashley.system.FireEmblemSystem;
import com.basecolon.FireJemblem.ashley.system.unit.*;

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
    TILE(TileSystem.class,
            ItemIdentity.class,
            Transform.class
    ),
    UNIT(UnitSystem.class,
            UnitStats.class,
            UnitIdentity.class,
            Inventory.class,
            Transform.class
    ),
    FRIEND_UNIT(FriendUnitSystem.class,
            UNIT,
            Selectable.class
    ),
    ENEMY_UNIT(EnemyUnitSystem.class,
            UNIT,
            Networked.class
    ),
    ITEM(ItemSystem.class,
            ItemIdentity.class,
            ItemStats.class
    )
    ;

    private final Class<? extends FireEmblemSystem> system;
    private final Class<? extends Component>[] components;


    /**
     * Allows an entity to be defined as an enum consisting of components
     * @param components The components to use to create this entity
     */
    @SafeVarargs
    FireEmblemEntities(Class<? extends FireEmblemSystem> system, Class<? extends Component>... components) {
       this.system = system;
       this.components = components;
    }

    /**
     * Alternate constructor that allows you to define the fact that an entity should "inherit" off its parent entity
     * Essentially takes all of the components of the inheritedEntity and adds all of the given components to it
     * @param inheritedEntity The base entity
     * @param components The components specific to this "child" entity
     */
    @SafeVarargs
    FireEmblemEntities(Class<? extends FireEmblemSystem> system, FireEmblemEntities inheritedEntity, Class<? extends Component>... components) {
        this.system = system;

        List<Class<? extends Component>> inheritedEntityAsList = Arrays.asList(inheritedEntity.getComponentsClasses());
        Collections.addAll(inheritedEntityAsList, components);

        //noinspection unchecked
        this.components = (Class<? extends Component>[]) inheritedEntityAsList.toArray();
    }


    /**
     * A convenience method that you can use to get a family of {@link Entity}s that require just these {@link Component}s
     * @return The {@link Family} of Entities that is made up by these Components
     */
    public Family getFamilyOf() {
        return Family.getFor(this.getComponentsClasses());
    }

    /**
     * @return The class of the System that will be used on this Entity
     */
    public Class<? extends FireEmblemSystem> getSystemClass() {
        return system;
    }
    /**
     * Get all of the components that make up this enum element. Returns as an array because the {@link Family#getFor(Class[])} method takes varargs, not a List
     * @return An array containing all of the components that make up this enum
     */
    public Class<? extends Component>[] getComponentsClasses() {
        return components;
    }


}
