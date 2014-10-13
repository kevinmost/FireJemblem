package com.basecolon.FireJemblem.ashley.system;

import com.basecolon.FireJemblem.ashley.entity.FireEmblemEntities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author kevinmost
 * @date 10/12/14
 */
public class SystemFactory {

    public static <FESystem extends FireEmblemSystem> FESystem getSystem(final FireEmblemEntities entity) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        //noinspection unchecked
        Constructor<FESystem> feSystemConstructor = (Constructor<FESystem>) entity.getSystemClass().getDeclaredConstructor(FireEmblemEntities.class);
        return feSystemConstructor.newInstance(entity);
    }
}
