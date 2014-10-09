package com.basecolon.FireJemblem.deprecated.items.usables;

import com.basecolon.FireJemblem.deprecated.items.Item;
import com.basecolon.FireJemblem.deprecated.units.Unit;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public abstract class Usable extends Item {
    /**
     * @deprecated use {@link Unit@use(Usable)} instead
     */
    @Deprecated
    public abstract void useOn(Unit usingUnit);
}
