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
    String specialEnhancement = "Weapon";

    /**
     * A method to get the special enhancement of the weapon
     * TODO get this string to save with the weapon item
     * @return WeaponEnhanceDecorator
     */
    public String getSpecialEnhance() {
        return this.specialEnhancement;
    }
}
