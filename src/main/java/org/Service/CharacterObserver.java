package main.java.org.Service;

import main.java.org.model.Character.Character;

import java.util.Observable;
import java.util.Observer;

/**
 * A concrete Character observer class
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class CharacterObserver implements Observer{
    /**
     * A method to update the Character Observer
     *
     * @param o observable
     * @param arg Object being observed - Ability of a character
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Character's ability: " +arg.toString());

    }
}
