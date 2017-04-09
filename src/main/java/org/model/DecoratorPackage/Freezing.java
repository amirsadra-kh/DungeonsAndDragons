package main.java.org.model.DecoratorPackage;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.StrategyPackage.FrozenStrategy;

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

    /**
     * A method to set the behaviour strategy of the target to frozen.
     * @param target
     */
    public void freeze(Character target) {
        FrozenStrategy frozen = new FrozenStrategy();
        frozen.setUp(this.getEnhance(), target.getBehaviourStrategy());
        target.setBehaviourStrategy(frozen);
    }
}
