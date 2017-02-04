package main.java.org.model;

import java.io.Serializable;

public class NonPlayerCharacter extends  Character implements Serializable {

    public Character create(final BackPackInventory backPackInventory) {
        return new NonPlayerCharacter();
    }

    public void attack() {

    }
}
