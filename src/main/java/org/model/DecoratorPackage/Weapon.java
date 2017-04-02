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
public abstract class Weapon extends Item {
    public abstract void getEnhancement();
}
