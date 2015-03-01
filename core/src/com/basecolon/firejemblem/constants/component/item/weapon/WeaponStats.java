package com.basecolon.firejemblem.constants.component.item.weapon;

import com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface WeaponStats {
    String name();
    WeaponTypes type();
    WeaponProficiencyLevels level();
    int durability();
    int minRange();
    int maxRange();
    int weight();
    int might();
    int hit();
    int crit();
    ClassTypes.EffectiveDamageGroups[] effectiveAgainst() default {};
    String spritePath();
    String infotext() default "";
}
