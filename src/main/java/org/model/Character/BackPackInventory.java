package main.java.org.model.Character;

import main.java.org.model.Item;

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
    // The maximum number of items allowed in the backpack
    private final int MAX_ITEMS = 10;
    protected List<Item> items = new ArrayList<>();

    /**
     * A method to get the items in the backpack
     * @return a set of backpack items
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * A method to set the items in the backpack
     * @param items the items chosen by user
     */
    public void setItems(List<Item> items) {
        this.items = new ArrayList<>();
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

        while (itr.hasNext()) {
            lastElement = itr.next();
        }

        return lastElement;
    }

    @Override
    public String toString() {
        return "BackPackInventory{" +
                "items=" + items +
                ", MAX_ITEMS=" + MAX_ITEMS +
                '}';
    }
}
