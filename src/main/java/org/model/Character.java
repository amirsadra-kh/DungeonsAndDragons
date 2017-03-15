package main.java.org.model;

import java.awt.*;
import java.util.*;
import main.java.org.Service.Observer;
import java.util.Set;

/**
 * This class is the character object
 * TODO we should be able to equipped the character with items in inventory
 * @author Parisa Nikzad
 * @version 1.0
 * @since 2017-02-23
 */
public class Character {
    private BackPackInventory backPackInventory;

    private Point currentPosition = new Point(0,0);
    private Ability ability;
    private boolean isPlayerCharacter;
    private String charName;
    private Set<Item> itemsWearing;
    private int level;

    private java.util.List<Observer> observers = new ArrayList<>();
    private Ability state;

    public Character() {
    }

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
     * set Level of the Character
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Get the level of the character
     * @return level of the character
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * A method for initializing the ability for the character
     *
     * @param ability object to be used to set the ability of this character
     */
    public void setAbility(Ability ability) {
        ability.getArmorClass(0);
        ability.setDamageBonus(0);
        ability.setAttackBonus(0);
        ability.level = 1;
        this.level = 1;
        this.ability = ability;
    }

    /**
     * A method for setting the items the character is wearing.
     *
     * @param items a set of items the user has chosen
     */
    public void setItemsWearing(Set<Item> items) {
        this.itemsWearing = items;
    }

    /**
     * A method for getting the items the character is wearing.
     *
     * @return the items the character is wearing.
     */
    public Set<Item> getItemsWearing() {
        return this.itemsWearing;
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

    public Set<Item> getBackPackInventory() {
        return backPackInventory.getItems();
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
    public Ability getState() {
        return this.state;
    }

    /**
     * A method to set the state of the inventory
     * @param state of the inventory
     */
    public void setState(Ability state) {
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
