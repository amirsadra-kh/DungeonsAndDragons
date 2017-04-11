package main.java.org.Service;

import main.java.org.model.CharacterPackage.Inventory;

import java.util.Observable;
import java.util.Observer;


/**
 * A concrete Inventory observer class
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class InventoryObserver implements Observer {
    /**
     * A method for updating the Inventory Observer
     *
     * @param o observable
     * @param arg Object being observed - Inventory of a character
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Character is wearing & has in his backpack: " +arg.toString());
    }
}
