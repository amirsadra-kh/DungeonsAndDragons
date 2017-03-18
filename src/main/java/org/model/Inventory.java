package main.java.org.model;

import java.util.ArrayList;
import java.util.List;
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
    protected List<Item> items;
    private List<Item> state;

    /**
     * A Constructor for Inventory
     */
    public void Inventory() {
    }

    /**
     * A method to get the items in the inventory
     * @return a set of inventory items
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * A method to set the items in the inventory
     * @param character that has items
     */
    public void setItems(Character character) {
        List<Item> backpack = character.getBackPackInventory();
        Set<Item> itemsWearing = character.getItemsWearing();
        items = backpack;
        items.addAll(itemsWearing);
    }

    @Override
    public String toString() {
        String inventoryString;
        inventoryString = "Items: " +this.items;
        return inventoryString;
    }

    /**
     * A method to get the state of the inventory
     * @return state of the inventory
     */
    public List<Item> getState() {
        return this.state;
    }

    /**
     * A method to set the state of the inventory
     * @param state for the inventory
     */
    public void setState(List<Item>  state) {
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
