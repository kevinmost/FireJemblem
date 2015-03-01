package com.basecolon.firejemblem.misc.battle;

public enum BattleRole {
    ATTACKER, DEFENDER,
    BOTH // Only used to say that a certain decorator should be used regardless of the role that the character being decorated is playing
    ;

    /**
     * @return The opposite role. Defender if invoked on attacker, and attacker if invoked on defender.
     */
    public BattleRole other() {
        if (this == ATTACKER) return DEFENDER;
        if (this == DEFENDER) return ATTACKER;
        throw new IllegalStateException("Cannot get the \"other\" element of \"BOTH\"");
    }

    public boolean matches(BattleRole other) {
        return this == BOTH || other == BOTH || this == other;
    }
}
