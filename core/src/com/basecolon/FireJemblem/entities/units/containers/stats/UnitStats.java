package com.basecolon.FireJemblem.entities.units.containers.stats;

/**
 * @author kevinmost
 * @date 10/7/14
 */
public class UnitStats {
    private int currentHP;

    private int hp;
    private int str;
    private int mag;
    private int skl;
    private int spd;
    private int lck;
    private int def;
    private int res;

    public UnitStats(int hp, int str, int mag, int skl, int spd, int lck, int def, int res) {
        this.hp = hp;
        this.str = str;
        this.mag = mag;
        this.skl = skl;
        this.spd = spd;
        this.lck = lck;
        this.def = def;
        this.res = res;

        currentHP = this.hp;
    }


}