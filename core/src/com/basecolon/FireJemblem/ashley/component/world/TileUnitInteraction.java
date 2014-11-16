package com.basecolon.FireJemblem.ashley.component.world;

import com.badlogic.ashley.core.Component;
import com.basecolon.FireJemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.FireJemblem.constants.component.world.TileConstants;

import java.util.HashMap;
import java.util.Map;

public class TileUnitInteraction extends Component {

    public int def;
    public int avoid;
    public int heal;
    public Map<ClassTypes, Integer> moveCost = new HashMap<>();

    public TileUnitInteraction(TileConstants constants) {
        this.def = constants.def;
        this.avoid = constants.avoid;
        this.heal = constants.heal;

        for (ClassTypes aClass : ClassTypes.values()) {
            int thisClassMoveCost;
            if (constants.specialMoveCost.containsKey(aClass)) {
                thisClassMoveCost = constants.specialMoveCost.get(aClass);
            } else {
                thisClassMoveCost = constants.moveCost;
            }
            moveCost.put(aClass, thisClassMoveCost);
        }
    }
}
