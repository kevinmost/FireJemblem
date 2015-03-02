package com.basecolon.firejemblem.misc.items;

import com.badlogic.ashley.core.Entity;

public interface Usable {
    public void onUse();

    public boolean isUsableOn(Entity entity);
}
