package com.basecolon.FireJemblem.ashley.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.constants.FireJemblem;

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
        for (Component component : components.values()) {
            e.add(component);
        }
        FireJemblem.engine.addEntity(e);
        components.clear();
        return e;
    }

    protected void put(Class<? extends Component> clazz, Component component) {
        components.put(clazz, component);
    }

    private void ensureAllRequiredElementsSet() {
        for (Class requiredComponent : getRequiredComponents()) {
            if (!components.containsKey(requiredComponent)) {
                throw new RequiredEntityComponentsNotSetException(this.getClass(), "set"+requiredComponent.getSimpleName());
            }
        }
    }

    public abstract <C extends Component> Class[] getRequiredComponents();
    public abstract <C extends Component> Class<C>[] getAllComponents();


    public class RequiredEntityComponentsNotSetException extends RuntimeException {
        RequiredEntityComponentsNotSetException(Class<? extends EntityBuilder> entityBeingBuilt, String requiredMethod) {
            super(String.format("When building an Entity with %s, you must invoke the %s method", entityBeingBuilt.getSimpleName(), requiredMethod));
        }
    }
}
