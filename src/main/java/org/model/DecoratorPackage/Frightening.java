package main.java.org.model.DecoratorPackage;

import main.java.org.Service.StrategyPackage.FrighteningStrategy;
import main.java.org.model.CharacterPackage.Character;

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
    public Frightening(final Weapon decoratedWeapon) {
        super(decoratedWeapon);
    }

    /**
     * A method that adds the special enhancements to a string.
     * @return a string with special enhancements
     */
    public String getSpecialEnhance() {
        return super.getSpecialEnhance() + ", Frightening";
    }

    /**
     * A method to set the behaviour strategy of the target to frightened
     * @param target who has been hit
     * @param attacker character who attacked with frightened strategy
     */
    public void frighten(final Character target, final Character attacker) {
        final FrighteningStrategy frighten = new FrighteningStrategy();
        frighten.setUp(this.getEnhance(), target.getBehaviourStrategy(), attacker);
        target.setBehaviourStrategy(frighten);
    }
}
