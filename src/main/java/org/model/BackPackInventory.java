package main.java.org.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class for the backpack inventory of a character
 * Backpack object.
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 02.08.2017
 */
public class BackPackInventory {
    protected List<Item> items = new ArrayList<>();
    // The maximum number of items allowed in the backpack
    private final int MAX_ITEMS = 10;

    /**
     * A method to get the items in the backpack
     * @return a set of backpack items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * A method to set the items in the backpack
     * @param items the items chosen by user
     */
    public void setItems(List<Item> items) {
        this.items = items;
        // Remove last items added in case there were more than MAX items added
        while(this.items.size() > MAX_ITEMS){
            Item lastItem = getLastItem();
            this.items.remove(lastItem);
        }
        if(this.items.size() == MAX_ITEMS){
            System.out.println("The backpack is full!! Cannot add more items!");
        }
    }

    /**
     * A method to get the last item in the backpack
     * @return the last item in the backpack
     */
    private Item getLastItem() {
        Iterator<Item> itr = this.items.iterator();
        Item lastElement = itr.next();

        while(itr.hasNext()) {
            lastElement = itr.next();
        }

        return lastElement;
    }

    /**
     * A method to remove an item from the backpack
     * @param item to be removed
     */
    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * A method for adding item to backpack
     * @param item to be added to the backpack
     */
    public void addItem(Item item) {
        // The backpack only allows for 10 items add a time
        if(items.size() >= MAX_ITEMS)
            System.out.println("Sorry the backpack is full!");
        else {
            this.items.add(item);
            System.out.println("The item has been added to the backpack!");
        }
    }
}
