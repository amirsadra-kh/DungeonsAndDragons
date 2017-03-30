package main.java.org.Service;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.CharacterPackage.Inventory;

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
