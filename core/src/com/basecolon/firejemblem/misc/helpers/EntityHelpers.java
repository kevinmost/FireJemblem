package com.basecolon.firejemblem.misc.helpers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import com.basecolon.firejemblem.ashley.entity.EntityBuilder;

import java.util.HashMap;
import java.util.Map;

public class EntityHelpers {

    private static Map mappers = new HashMap();

    public static <E extends EntityBuilder> Mappers mappersFor(E entity) {
        if (mappers.containsKey(entity)) {
            return (Mappers) mappers.get(entity);
        }
        Mappers newMapper = new Mappers(entity);
        //noinspection unchecked
        mappers.put(entity, newMapper);
        return newMapper;
    }

    public static <E extends EntityBuilder> Family familyFor(E entity) {
        return Family.all(entity.getRequiredComponents()).get();
    }

    public static class Mappers {
        private Map<Class<? extends Component>, ComponentMapper<? extends Component>> mappers = new HashMap<>();

        protected <E extends EntityBuilder> Mappers(E entity) {
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
