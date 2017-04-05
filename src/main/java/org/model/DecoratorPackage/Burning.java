package main.java.org.model.DecoratorPackage;

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
}
