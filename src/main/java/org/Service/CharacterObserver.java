package main.java.org.Service;

import main.java.org.model.CharacterPackage.Character;

/**
 * A concrete CharacterPackage observer class
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class CharacterObserver extends ObserverObject{
    /**
     * A method to initialize the CharacterPackage observer
     * @param character
     */
    public CharacterObserver(Character character){
        this.character = character;
        this.character.attach(this);
    }

    /**
     * A method to update the CharacterPackage Observer
     */
    @Override
    public void update() {
        System.out.println("CharacterPackage's ability: " +this.character.getState());

    }
}
