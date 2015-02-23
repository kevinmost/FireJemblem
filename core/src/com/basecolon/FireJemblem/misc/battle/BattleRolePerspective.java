package com.basecolon.firejemblem.misc.battle;

public enum BattleRolePerspective {
    ME,
    THEM,
    BOTH;

    public BattleRolePerspective other() {
        if (this == ME) return THEM;
        if (this == THEM) return ME;
        throw new IllegalStateException("Cannot get the \"other\" of \"BOTH\"");
    }

    public boolean matches(BattleRolePerspective other) {
        return this == BOTH || other == BOTH || this == other;
    }
}
