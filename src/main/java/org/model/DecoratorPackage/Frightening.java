package main.java.org.model.DecoratorPackage;

/**
 * A Frightening Decorator
 * Target runs away from character for a number of turns
 * equal to the enchantment bonus of the weapon.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class Frightening  extends WeaponEnhanceDecorator {
    /**
     * Wraps a Weapon object inside an object of
     * WeaponEnhancementDecorator's subclasses
     *
     * @param decoratedWeapon
     */
    public Frightening(Weapon decoratedWeapon) {
        super(decoratedWeapon);
    }

    /**
     * A method that adds the special enhancements to a string.
     * @return a string with special enhancements
     */
    public String getSpecialEnhance() {
        return super.getSpecialEnhance() + ", Frightening";
    }
}
