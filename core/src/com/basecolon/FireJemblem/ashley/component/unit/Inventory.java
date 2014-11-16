package com.basecolon.FireJemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;
import com.basecolon.FireJemblem.misc.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends Component {

    public List<Item> items = new LimitedList<>(5);

    public Inventory(LimitedList<Item> items) {
        this.items = items;
    }

    /**
     * This class acts exactly like an ArrayList, but throws an exception if the initial capacity is exceeded for any reason
     * It also only returns its initial capacity when "size" is reported
     */
    public static class LimitedList<T> extends ArrayList<T> {

        private final int maxSize;

        private LimitedList(int initialCapacity) {
            super(initialCapacity);
            maxSize = initialCapacity;
        }

        @Override
        public boolean add(T t) {
            if (this.size() >= maxSize) {
                throw new IllegalStateException("List is already at its maximum size of " + maxSize);
            }
            return super.add(t);
        }

        public static <T> LimitedList<T> fromList(List<T> l) {
            return fromList(l, 5);
        }
        private static <T> LimitedList<T> fromList(List<T> l, int maxSize) {
            if (l.size() > maxSize) throw new IllegalStateException("You must specify a maxSize larger than the starting list");
            LimitedList<T> ll = new LimitedList<>(maxSize);
            ll.addAll(l);
            return ll;
        }
    }
}
