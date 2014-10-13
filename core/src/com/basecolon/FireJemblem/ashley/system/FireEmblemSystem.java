package com.basecolon.FireJemblem.ashley.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.basecolon.FireJemblem.ashley.entity.FireEmblemEntities;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevinmost
 * @date 10/12/14
 */
public abstract class FireEmblemSystem extends IteratingSystem {

    private final Map<Class<? extends Component>, ComponentMapper<? extends Component>> components = new HashMap<>();


    /**
     * This constructor should only be accessible from within this package, so that {@link SystemFactory} can create an instance of it
     * @param entity The Entity that defines the Family that this System controls
     */
    protected FireEmblemSystem(FireEmblemEntities entity) {
        // Create this IteratingSystem for the family of these FireEmblemEntities
        super(entity.getFamilyOf());
        // Create the ComponentMappers for every Component of these FireEmblemEntities
        for (Class<? extends Component> component : entity.getComponentsClasses()) {
            components.put(component, ComponentMapper.getFor(component));
        }
    }

    /**
     * Get the {@link ComponentMapper} for this {@link EntitySystem}
     * @param componentClass The class of the {@link Component} that you want the {@link ComponentMapper} for
     * @return The desired {@link ComponentMapper}
     */
    public ComponentMapper<? extends Component> getComponent(Class<? extends Component> componentClass) {
        return components.get(componentClass);
    }
}
