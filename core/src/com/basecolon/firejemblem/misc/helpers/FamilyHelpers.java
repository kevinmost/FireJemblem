package com.basecolon.firejemblem.misc.helpers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Family;
import com.basecolon.firejemblem.ashley.entity.EntityBuilder;

public class FamilyHelpers {
    @SuppressWarnings("unchecked")
    public static <EB extends EntityBuilder>Family getFamilyOf(Class<EB> entity) {
        try {
            Class<? extends Component>[] requiredComponents = entity.newInstance().getRequiredComponents();
            return Family.all(requiredComponents).get();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
