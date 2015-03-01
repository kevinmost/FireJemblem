package com.basecolon.firejemblem.ashley.component;

import com.badlogic.ashley.core.Component;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public abstract class MappedComponent<K,V> extends Component {
    protected Map<K,V> map;

    public MappedComponent(Map<K,V> components) {
        map = components;
    }

    public MappedComponent(Pair<K,V>... components) {
        map = new HashMap<>();
        for (Pair<K, V> component : components) {
            map.put(component.getKey(), component.getValue());
        }
    }

    public V get(K key) {
        V v = map.get(key);
        if (v != null) return v;
        return defaultReturnValue(key);
    }

    protected abstract V defaultReturnValue(K key);
}
