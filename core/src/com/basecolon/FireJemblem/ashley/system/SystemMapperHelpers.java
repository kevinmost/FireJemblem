package com.basecolon.FireJemblem.ashley.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.basecolon.FireJemblem.ashley.entity.EntityBuilder;

import java.util.HashMap;
import java.util.Map;

public class SystemMapperHelpers {

    public static <E extends EntityBuilder> Mappers mappersFor(E entity) {
        return new Mappers(entity);
    }


    public static class Mappers {
        private Map<Class<? extends Component>, ComponentMapper<? extends Component>> mappers = new HashMap<>();

        public <E extends EntityBuilder> Mappers(E entity) {
            for (Class<Component> component : entity.getAllComponents()) {
                mappers.put(component, ComponentMapper.getFor(component));
            }
        }

        @SuppressWarnings("unchecked")
        public <C extends Component> ComponentMapper<C> getMapperFor(Class<C> component) {
            return (ComponentMapper<C>) mappers.get(component);
        }
    }
}
