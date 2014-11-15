package com.basecolon.FireJemblem.ashley.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class EntityBuilder {
    protected Map<Class<? extends Component>, Component> components = new HashMap<>();

    public Entity build() {
        ensureAllRequiredElementsSet();
        Entity e = new Entity();
        for (Component component : components.values()) {
            e.add(component);
        }
        components.clear();
        return e;
    }

    protected void put(Class<? extends Component> clazz, Component component) {
        components.put(clazz, component);
    }

    private void ensureAllRequiredElementsSet() {
        Method[] declaredMethods = this.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(Required.class)) {
                for (Class<? extends Component> requiredComponent : declaredMethod.getAnnotation(Required.class).componentsSetByThisMethod()) {
                    if (!components.containsKey(requiredComponent)) {
                        throw new RequiredEntityComponentsNotSetException(this.getClass(), declaredMethod.getName());
                    }
                }
            }
        }
    }

    /**
     * Setter methods decorated with this annotation must be set by the client prior to building.
     * If a method is not invoked by the client in the building process and it has this annotation, the
     * {@link #build()} method will throw an exception
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    protected @interface Required {
        Class<? extends Component>[] componentsSetByThisMethod();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    protected @interface Optional {
        Class<? extends Component>[] componentsSetByThisMethod();
    }

    class RequiredEntityComponentsNotSetException extends RuntimeException {
        RequiredEntityComponentsNotSetException(Class<? extends EntityBuilder> entityBeingBuilt, String requiredMethod) {
            super(String.format("When building an Entity with %s, you must invoke the %s method", entityBeingBuilt.getSimpleName(), requiredMethod));
        }
    }
}
