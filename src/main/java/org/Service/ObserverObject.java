package main.java.org.Service;

import main.java.org.model.Character.Character;
import main.java.org.model.Character.Inventory;

/**
 * A Observer parent class
 */
public abstract class ObserverObject {
    protected Character character;
    protected Inventory inventory;

    /**
     * An abstract upadate method
     */
    public abstract void update();
}
