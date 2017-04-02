package main.java.org.model.DecoratorPackage;

/**
 * A Pacifying Decorator
 * Target adopts the “Friendly NPC” character strategy.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class Pacifying extends WeaponEnhanceDecorator {
    /**
     * When creating a decorated Weapon, pass Weapon to be decorated
     * as a parameter.
     *
     * @param decoratedWeapon
     */
    public Pacifying(Weapon decoratedWeapon) {
        super(decoratedWeapon);
    }
}
