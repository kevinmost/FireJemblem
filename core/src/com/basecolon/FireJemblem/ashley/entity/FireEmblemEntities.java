package com.basecolon.FireJemblem.ashley.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.basecolon.FireJemblem.ashley.component.unit.Inventory;
import com.basecolon.FireJemblem.ashley.component.unit.UnitIdentity;
import com.basecolon.FireJemblem.ashley.component.unit.UnitStats;

import java.util.Arrays;
import java.util.List;

/**
 * An {@link Entity} is just a bag of components. It never even gets instantiated directly by us.
 * All we really need to do is define what {@link Component}s make up the Entity
 * @author kevinmost
 * @date 10/9/14
 */
public enum FireEmblemEntities {
    UNIT(Arrays.asList(
            UnitStats.class,
            UnitIdentity.class,
            Inventory.class
    )),

    ;

    FireEmblemEntities(List<Class<? extends Component>> components) {
        // We define our components in a List in our enum constructor, but then it is immediately turned into an array.
        // We do this because we can't create a generic array in Java, so we create a generic List and then turn it into an array
        // Of course, we need to add the following line to suppress compiler warnings
        //noinspection unchecked
        this.components = components.toArray(new Class[components.size()]);
    }
    private Class<? extends Component>[] components;

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
