package com.basecolon.firejemblem.ashley.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.basecolon.firejemblem.constants.FireJemblem;

import java.util.HashMap;
import java.util.Map;

public abstract class EntityBuilder {
    private Map<Class<? extends Component>, Component> components = new HashMap<>();


    /**
     * Checks if your entity has been built properly (if all required elements were set). If it is,
     * the entity is built up component-by-component. The entity is then added to the engine for you.
     * @return The entity you just built, <i>after</i> it has been added to the engine
     */
    public Entity build() {
        ensureAllRequiredElementsSet();
        Entity e = new Entity();
        components.values().forEach(e::add);
        FireJemblem.engine.addEntity(e);
        return e;
    }

    protected <C extends Component> void put(Class<C> clazz, C component) {
        components.put(clazz, component);
    }

    private void ensureAllRequiredElementsSet() {
        for (Class requiredComponent : getRequiredComponents()) {
            if (!components.containsKey(requiredComponent)) {
                throw new RequiredEntityComponentsNotSetException();
            }
        }
    }

    /**
     * All of the Components returned by this method must be set, or the builder will throw an exception
     */
    public abstract <C extends Component> Class<C>[] getRequiredComponents();

    /**
     * All of the Components that can be set with this entity's builder, including both optional and required ones
     */
    public abstract <C extends Component> Class<C>[] getAllComponents();


    public class RequiredEntityComponentsNotSetException extends RuntimeException {
        public RequiredEntityComponentsNotSetException() {
            super("All components marked as required not set!");
        }
    }
}
