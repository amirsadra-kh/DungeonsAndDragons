package main.java.org.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import main.java.org.Service.Observer;

/**
 * A inventory subject of the observer
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class Inventory {
    private List<Observer> observers = new ArrayList<>();
    private int state;
    private BackPackInventory backpack;
    private Set<Item> itemsWearing;

    /**
     * A method for getting the inventory from a character.
     *
     * @param character either the player or a monster from a map
     */
    public void Inventory(Character character) {
        this.itemsWearing = character.getItemsWearing();
        this.backpack = character.getBackPackInventory();
    }

    /**
     * A method to get the state of the inventory
     * @return state of the inventory
     */
    public int getState() {
        return this.state;
    }

    /**
     * A method to set the state of the inventory
     * @param state of the inventory
     */
    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    /**
     * A method to attach the observer to the inventory
     * @param observer
     */
    public void attach(Observer observer){
        this.observers.add(observer);
    }

    /**
     * A method to notify all the observers.
     */
    public void notifyAllObservers(){
        for (Observer observer : this.observers) {
            observer.update();
        }
    }
}
