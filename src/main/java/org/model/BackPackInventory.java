package main.java.org.model;

import main.java.org.model.ItemEnum;

import java.util.List;

public class BackPackInventory {
    List<ItemEnum> items;

    public List<ItemEnum> getItems() {
        return items;
    }

    public void setItems(List<ItemEnum> items) {
        this.items = items;
    }
}
