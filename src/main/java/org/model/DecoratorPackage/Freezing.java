package main.java.org.model.DecoratorPackage;

/**
 * Freezing Decorator.
 * Target cannot move for a number of turns equal to the enhancement
 * bonus of the weapon.
 *
 * @author
 * @version
 * @since
 */
public class Freezing  extends WeaponEnhanceDecorator {
    /**
     * Wraps a Weapon object inside an object of
     * WeaponEnhancementDecorator's subclasses
     *
     * @param decoratedWeapon
     */
    public Freezing(Weapon decoratedWeapon) {
        super(decoratedWeapon);
    }

    /**
     * A method that adds the special enhancements to a string.
     * @return a string with special enhancements
     */
    public String getSpecialEnhance() {
        return super.getSpecialEnhance() + ", Freezing";
    }
}
