package main.java.org.model;

import java.awt.*;
import java.util.*;
import main.java.org.Service.Observer;

/**
 * This class is the character object
 * TODO we should be able to equipped the character with items in inventory
 * @author Parisa Nikzad
 * @version 1.0
 * @since 2017-02-23
 */
public class Character {
    BackPackInventory backPackInventory;
    private Point currentPosition = new Point(0,0);
    private Ability ability;
    private boolean isPlayerCharacter;
    private String charName;

    private java.util.List<Observer> observers = new ArrayList<>();
    private int state;

    public Point getCurrentPosition() {
        return this.currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Ability getAbility() {
        return ability;
    }

    /**
     * A method for setting the ability for the character
     *
     * @param ability object to be used to set the ability of this character
     */
    public void setAbility(Ability ability) {
        ability.setArmorClass(0);
        ability.setDamageBonus(0);
        ability.setAttackBonus(0);
        ability.setLevel(1);
        this.ability = ability;
    }

    /**
     * A method for creating a new character
     *
     * @param backPackInventory to be connected with the new character
     * @return the new character
     */
    public Character create(final BackPackInventory backPackInventory) {
        Character character = new Character();
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

    public boolean isPlayerCharacter() {
        return isPlayerCharacter;
    }

    public void setPlayerCharacter(boolean playerCharacter) {
        isPlayerCharacter = playerCharacter;
    }

    public void setCharName(String name) {
        this.charName = name;
    }

    public void charString() {
        String character = this.charName +"," +this.ability.toString();
    }

    /**
     * A method to get the state of the inventory
     * @return state of the inventory
     */
    public int getState() {
        return this.state;
    }

    /**
     * A method to set the state of the inventory
     * @param state of the inventory
     */
    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    /**
     * A method to attach the observer to the inventory
     * @param observer
     */
    public void attach(Observer observer){
        this.observers.add(observer);
    }

    /**
     * A method to notify all the observers.
     */
    public void notifyAllObservers(){
        for (Observer observer : this.observers) {
            observer.update();
        }
    }
}
