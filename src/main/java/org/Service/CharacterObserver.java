package main.java.org.Service;

import main.java.org.model.Character;

/**
 * A concrete Character observer class
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-10
 */
public class CharacterObserver extends Observer{
    /**
     * A method to initialize the Character observer
     * @param character
     */
    public CharacterObserver(Character character){
        this.character = character;
        this.character.attach(this);
    }

    @Override
    public void update(){
        System.out.println("Character is wearing: " +character.getState());
    }
}
