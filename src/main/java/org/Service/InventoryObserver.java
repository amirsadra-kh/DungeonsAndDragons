package main.java.org.Service;

import main.java.org.model.Inventory;

/**
 * A concrete Inventory observer class
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class InventoryObserver extends Observer {
    /**
     * A method to initialize the Inventory observer
     * @param inventory
     */
    public InventoryObserver(Inventory inventory){
        this.inventory = inventory;
        this.inventory.attach(this);
    }

    @Override
    public void update(){
        System.out.println("Character is wearing & has in his backpack: " +inventory.getState());
    }
}
