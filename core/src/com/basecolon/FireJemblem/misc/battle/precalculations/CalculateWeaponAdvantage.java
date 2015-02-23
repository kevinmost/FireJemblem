package com.basecolon.firejemblem.misc.battle.precalculations;

import com.basecolon.firejemblem.constants.component.item.weapon.WeaponTypes;
import com.basecolon.firejemblem.misc.battle.BattleData;
import com.basecolon.firejemblem.misc.battle.BattleRole;

public class CalculateWeaponAdvantage extends BaseCalculationStage<CalculateWeaponAdvantage.WeaponTriangleBonuses> {
    public CalculateWeaponAdvantage(BattleData data) {
        super(data);
    }

    @Override
    protected WeaponTriangleBonuses calculate(BattleRole role) {
        WeaponTypes.WeaponAdvantage advantage = getWeaponAdvantage(role);
        Integer mightBonus = getMightBonus() * (advantage == WeaponTypes.WeaponAdvantage.ADVANTAGE ? 1 : -1) * getMightBonusMultiplier();
        Integer hitRateBonus = getHitRateBonus() * (advantage == WeaponTypes.WeaponAdvantage.ADVANTAGE ? 1 : -1) * getHitRateBonusMultiplier();

        return new WeaponTriangleBonuses(advantage, mightBonus, hitRateBonus);
    }

    private WeaponTypes.WeaponAdvantage getWeaponAdvantage(BattleRole role) {
        return data.get(role).equippedWeapon.getType().advantageAgainst(data.get(role.other()).equippedWeapon.getType());
    }

    private Integer getMightBonus() {
        return 1;
    }
    private Integer getHitRateBonus() {
        return 15;
    }
    private Integer getMightBonusMultiplier() {
        return 1;
    }
    private Integer getHitRateBonusMultiplier() {
        return 1;
    }

    public static class WeaponTriangleBonuses {
        public final WeaponTypes.WeaponAdvantage weaponAdvantage;
        public final Integer mightBonus;
        public final Integer hitRateBonus;

        public WeaponTriangleBonuses(WeaponTypes.WeaponAdvantage weaponAdvantage, Integer mightBonus, Integer hitRateBonus) {
            this.weaponAdvantage = weaponAdvantage;
            this.mightBonus = mightBonus;
            this.hitRateBonus = hitRateBonus;
        }

        @Override
        public String toString() {
            return weaponAdvantage.name() + " (" + "hit-rate " + (hitRateBonus < 0 ? '-' : '+') +
                    Math.abs(hitRateBonus) + "; might " + (mightBonus < 0 ? '-' : '+') +
                    Math.abs(mightBonus) + ")";
        }
    }
}
