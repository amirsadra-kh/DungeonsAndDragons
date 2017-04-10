package main.java.org.model.DecoratorPackage;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.Service.StrategyPackage.FriendlyNPC;

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

    /**
     * A method that adds the special enhancements to a string.
     * @return a string with special enhancements
     */
    public String getSpecialEnhance() {
        return super.getSpecialEnhance() + ", Pacifying";
    }

    /**
     * A method for setting the target's strategy to friendly
     *
     * @param target
     */
    public void setPacifying(Character target) {
        FriendlyNPC friendly = new FriendlyNPC();
        target.setBehaviourStrategy(friendly);
    }
}
