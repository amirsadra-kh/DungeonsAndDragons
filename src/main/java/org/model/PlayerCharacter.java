package main.java.org.model;

import java.io.Serializable;

public class PlayerCharacter extends Character implements Serializable {

    BackPackInventory backPackInventory;

    public Character create(final BackPackInventory backPackInventory) {
        PlayerCharacter character = new PlayerCharacter();
        character.setBackPackInventory(backPackInventory);
        return character;
    }

    public void attack() {

    }

    public BackPackInventory getBackPackInventory() {
        return backPackInventory;
    }

    public void setBackPackInventory(BackPackInventory backPackInventory) {
        this.backPackInventory = backPackInventory;
    }
}
