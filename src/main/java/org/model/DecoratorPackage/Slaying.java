package main.java.org.model.DecoratorPackage;

/**
 * A Slaying Decorator
 * Target dies instantly.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class Slaying extends WeaponEnhanceDecorator {
    /**
     * When creating a decorated Weapon, pass Weapon to be decorated
     * as a parameter.
     *
     * @param decoratedWeapon
     */
    public Slaying(Weapon decoratedWeapon) {
        super(decoratedWeapon);
    }

    /**
     * A method that adds the special enhancements to a string.
     * @return a string with special enhancements
     */
    public String getSpecialEnhance() {
        return super.getSpecialEnhance() + ", Slaying";
    }
}
