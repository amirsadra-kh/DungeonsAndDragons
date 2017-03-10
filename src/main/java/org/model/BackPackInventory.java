package main.java.org.model;

import java.util.List;

/**
 * A class for the backpack inventory of a character
 * Backpack object.
 *
 * @author Maysam Mokarian
 * @version 2.0
 * @since 02.08.2017
 */
public class BackPackInventory {
    private List<ItemEnum> items;

    public List<ItemEnum> getItems() {
        return items;
    }

    public void setItems(List<ItemEnum> items) {
        this.items = items;
    }
}
