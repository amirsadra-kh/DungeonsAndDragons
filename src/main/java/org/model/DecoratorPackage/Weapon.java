package main.java.org.model.DecoratorPackage;

import main.java.org.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract Weapon class defines the functionality of any Weapon
 * as an extension to a regular item
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class Weapon extends Item {
    List<WeaponEnhanceDecorator> specialEnhancement = new ArrayList<>();

    /**
     * A method to set the special enhancement of the weapon
     * @param specialEnhance
     */
    public void setSpecialEnhance(WeaponEnhanceDecorator specialEnhance) {
        this.specialEnhancement.add(specialEnhance);
    }

    /**
     * A method to get the special enhancement of the weapon
     * @return WeaponEnhanceDecorator
     */
    public List<WeaponEnhanceDecorator> getSpecialEnhance() {
        return this.specialEnhancement;
    }
}
