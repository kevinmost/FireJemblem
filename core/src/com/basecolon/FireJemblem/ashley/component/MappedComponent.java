package com.basecolon.FireJemblem.ashley.component;

import com.badlogic.ashley.core.Component;

import java.util.HashMap;
import java.util.Map;

public abstract class MappedComponent<K,V> extends Component {
    Map<K,V> map = new HashMap<>();

    public V get(K key) {
        V v = map.get(key);
        if (v != null) return v;
        return defaultReturnValue();
    }

    public abstract V defaultReturnValue();
}
