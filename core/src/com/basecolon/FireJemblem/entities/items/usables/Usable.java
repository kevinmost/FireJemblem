package com.basecolon.FireJemblem.entities.items.usables;

import com.basecolon.FireJemblem.entities.items.Item;
import com.basecolon.FireJemblem.entities.units.Unit;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public abstract class Usable extends Item {
    public abstract void use(Unit usingUnit);
}
