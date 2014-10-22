package com.basecolon.FireJemblem.ashley.entity.world;

import com.badlogic.ashley.core.Entity;
import com.basecolon.FireJemblem.ashley.component.item.ItemIdentity;
import com.basecolon.FireJemblem.ashley.component.world.Transform;
import com.basecolon.FireJemblem.constants.component.world.TileConstants;

public class TileEntity extends Entity {
    // TODO Replace this maybe?
    public ItemIdentity itemIdentity;
    public Transform transform;
    public TileConstants tileConstants;
}
