package main.java.org.model;

import main.java.org.model.ItemEnum;

import java.awt.*;

public class Item {

    ItemEnum item;
    Point coordinate;

    public Item(ItemEnum item, Point coordinate) {
        this.item = item;
        this.coordinate = coordinate;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public ItemEnum getItem() {
        return item;
    }

    public void setItem(ItemEnum item) {
        this.item = item;
    }
}
