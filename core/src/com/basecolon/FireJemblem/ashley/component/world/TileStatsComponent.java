package com.basecolon.firejemblem.ashley.component.world;

import com.badlogic.ashley.core.Component;
import com.basecolon.firejemblem.constants.component.unit.classes.ClassTypes;
import com.basecolon.firejemblem.constants.component.world.TileConstants;

import java.util.HashMap;
import java.util.Map;

public class TileStatsComponent extends Component {
    // TODO: Functionality to place light runes on tiles would probably go here.
    public int def;
    public int avoid;
    public int heal;
    private Map<ClassTypes, Integer> specialMoveCost = new HashMap<>();
    private int moveCost;

    public TileStatsComponent(TileConstants constants) {
        this.def = constants.def;
        this.avoid = constants.avoid;
        this.heal = constants.heal;
        this.moveCost = constants.moveCost;
        this.specialMoveCost = constants.specialMoveCost;
    }

    // TODO: Remove me, not component-y enough
    public int getMoveCost(ClassTypes unitClass) {
        Integer specialMoveCost = this.specialMoveCost.get(unitClass);
        if (specialMoveCost == null) {
            return this.moveCost;
        }
        return specialMoveCost;
    }
}
