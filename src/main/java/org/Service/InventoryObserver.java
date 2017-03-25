package main.java.org.Service;

import main.java.org.model.Inventory;
import main.java.org.model.Item;

import java.util.List;
import java.util.Observable;


/**
 * A concrete Inventory observer class
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class InventoryObserver extends ObserverObject {
    /**
     * A method to initialize the Inventory observer
     * @param inventory
     */
    public InventoryObserver(Inventory inventory){
        this.inventory = inventory;
        this.inventory.attach(this);
    }

    /**
     * A method for updating the Inventory Observer
     */
    @Override
    public void update() {
        System.out.println("Character is wearing & has in his backpack: " +this.inventory.toString());
    }
}
