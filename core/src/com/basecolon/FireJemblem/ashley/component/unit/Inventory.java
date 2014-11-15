package com.basecolon.FireJemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;

public class Inventory extends Component {
    private String[] items = new String[5];

    public String[] getItems() {
        return items;
    }

    public String getItem(int i) {
        return items[i];
    }

    public void setItem(int i, String s) {
        items[i] = s;
    }
}
