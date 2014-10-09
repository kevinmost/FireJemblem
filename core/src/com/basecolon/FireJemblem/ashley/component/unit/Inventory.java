package com.basecolon.FireJemblem.ashley.component.unit;

import com.badlogic.ashley.core.Component;
import com.basecolon.FireJemblem.ashley.entity.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevinmost
 * @date 10/8/14
 */
public class Inventory extends Component {
    public List<Item> items = new ArrayList<>(5);
}
