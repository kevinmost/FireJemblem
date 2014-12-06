package com.basecolon.FireJemblem.ashley.component;

import com.badlogic.ashley.core.Component;

import java.util.Map;

public abstract class MappedComponent<K,V> extends Component {
    protected Map<K,V> map;

    public MappedComponent(Map<K,V> components) {
        map = components;
    }

    public V get(K key) {
        V v = map.get(key);
        if (v != null) return v;
        return defaultReturnValue(key);
    }

    protected abstract V defaultReturnValue(K key);
}
