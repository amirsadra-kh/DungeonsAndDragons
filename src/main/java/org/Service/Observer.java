package main.java.org.Service;

import main.java.org.model.Inventory;
import main.java.org.model.Character;

/**
 * An observer class
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public abstract class Observer {
    protected Character character;
    protected Inventory inventory;

    /**
     * A method to update the observers.
     */
    public abstract void update();
}
