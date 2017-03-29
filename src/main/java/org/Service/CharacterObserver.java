package main.java.org.Service;

import main.java.org.model.Character.Character;

/**
 * A concrete Character observer class
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class CharacterObserver extends ObserverObject{
    /**
     * A method to initialize the Character observer
     * @param character
     */
    public CharacterObserver(Character character){
        this.character = character;
        this.character.attach(this);
    }

    /**
     * A method to update the Character Observer
     */
    @Override
    public void update() {
        System.out.println("Character's ability: " +this.character.getState());

    }
}
