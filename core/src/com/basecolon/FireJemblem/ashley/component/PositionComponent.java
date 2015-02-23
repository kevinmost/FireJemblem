package com.basecolon.firejemblem.ashley.component;

import com.badlogic.ashley.core.Component;
import com.basecolon.firejemblem.constants.component.world.TileConstants;

public class PositionComponent extends Component {
    public int x;
    public int y;

    public PositionComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TileConstants getTileTypeAtLocation() {
        // TODO: Implement logic to find this on the system grid
        return TileConstants.PLAIN;
    }
}
