package main.java.org.model.CharacterPackage;

import main.java.org.model.Item;

import java.util.*;

/**
 * A inventory subject of the observer
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class Inventory extends Observable {
    private List<Observer> observers = new ArrayList<>();
    protected List<Item> items = new ArrayList<>();
    private List<Item> state = new ArrayList<>();
    private List<Item> backpack = new ArrayList<>();
    private HashSet<Item> wearingItems = new HashSet<>();

    /**
     * A Constructor for model.CharacterPackage.Inventory
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
     * A method for getting the name of the items in the inventory.
     * @return a list of names of the items
     */
    public List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        for(Item item : this.items)
            itemNames.add(item.getName());

        return itemNames;
    }

    /**
     * A method to set the new wearingItems of the inventory
     * @param wearingItems of character
     */
    public void setWearingItems(HashSet<Item> wearingItems) {
        this.wearingItems = wearingItems;
        setItems();
    }

    /**
     * A method to set the new backpack items of the inventory
     * @param backpackItems
     */
    public void setBackpackItems(List<Item> backpackItems) {
        this.backpack = backpackItems;
        setItems();
    }

    /**
     * A method to set the items in the inventory
     */
    private void setItems() {
        this.items = new ArrayList<>();
        this.items.addAll(this.backpack);
        this.items.addAll(this.wearingItems);
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
     * A method to notify all the observers.
     */
    public void notifyAllObservers(){
        for (Observer observer : this.observers) {
            observer.update(this, this);
        }
    }
}
