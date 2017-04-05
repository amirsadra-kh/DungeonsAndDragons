package main.java.org.model.DecoratorPackage;

import main.java.org.model.Item;

/**
 * The abstract Weapon class defines the functionality of any Weapon
 * as an extension to a regular item
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class Weapon extends Item {
    WeaponEnhanceDecorator specialEnhancement;

    /**
     * A method to set the special enhancement of the weapon
     * @param specialEnhance
     */
    public void setSpecialEnhance(WeaponEnhanceDecorator specialEnhance) {
        this.specialEnhancement = specialEnhance;
    }

    /**
     * A method to get the special enhancement of the weapon
     * @return WeaponEnhanceDecorator
     */
    public WeaponEnhanceDecorator getSpecialEnhance() {
        return this.specialEnhancement;
    }
}
