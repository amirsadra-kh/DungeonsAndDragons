package main.java.org.model.DecoratorPackage;

import main.java.org.model.CharacterPackage.Character;

/**
 * A Burning Decorator
 * Target takes (5x enchantment bonus) damage for the 3 next turns.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class Burning  extends WeaponEnhanceDecorator {
    private int enhancementBonus = 5;
    private int turns = 3;
    /**
     * When creating a decorated Weapon, pass Weapon to be decorated
     * as a parameter.
     *
     * @param decoratedWeapon
     */
    public Burning(Weapon decoratedWeapon) {
        super(decoratedWeapon);
    }

    /**
     * A method that adds the special enhancements to a string.
     * @return a string with special enhancements
     */
    public String getSpecialEnhance() {
        return super.getSpecialEnhance() + ", Burning";
    }

    /**
     * A method to get the burning enhancement bonus amount
     * @return enhancement bonus (weapon enhancement * 5)
     */
    public int getEnhancementBonus() {
        this.enhancementBonus *= super.getEnhance();
        return this.enhancementBonus;
    }

    /**
     * A method to set the burning boolean of a character
     * @param character
     */
    public void setBurning(Character character) {
        if(this.turns > 0) {
            character.setBurning(true);
            turns--;
        } else {
            character.setBurning(false);
            this.turns = 3;
        }
    }
}
