package main.java.org.model.DecoratorPackage;

/**
 * Abstract decorator class - extends the Weapon abstract class
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public abstract class WeaponEnhanceDecorator extends Weapon {
    protected final Weapon decoratedWeapon;

    /**
     * Wraps a Weapon object inside an object of
     * WeaponEnhancementDecorator's subclasses
     * @param decoratedWeapon
     */
    public WeaponEnhanceDecorator(Weapon decoratedWeapon) {
        this.decoratedWeapon = decoratedWeapon;
    }


    public void getEnhancement() {
        decoratedWeapon.getSpecialEnhance();
    }
}
