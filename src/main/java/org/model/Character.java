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

    RollDice dice10 = new RollDice(10);

    private int dice = dice10.roll();

    private int hitPoints = dice;

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
     * This method set the HitPoint base on the Strength and Modifier.
     * @return integer value of the HitPoints
     * @todo This method should be move where we do an attack, it does not belong in ability.
     */
    public void setHitPoints() {
        Strength strength = new Strength();
        int integerStrength  = this.ability.getStrength();
        strength.set(integerStrength);
        this.hitPoints = strength.modifier() + dice;
    }

    /**
     * @todo This method should be move where we do an attack, it does not belong in ability.
     * @return the hitPoints
     */
    public int getHitPoints() {
        return this.hitPoints;
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
        setHitPoints();
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

    public String charString() {
        return "Name: " +this.charName +"\nAbility: " +this.ability.toString() +"\nHit points: " +this.hitPoints;
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

    @Override
    public String toString() {
        return "Character{" +
                "backPackInventory=" + backPackInventory +
                ", currentPosition=" + currentPosition +
                ", ability=" + ability +
                ", isPlayerCharacter=" + isPlayerCharacter +
                ", charName='" + charName + '\'' +
                ", itemsWearing=" + itemsWearing +
                ", level=" + level +
                ", dice10=" + dice10 +
                ", dice=" + dice +
                ", hitPoints=" + hitPoints +
                ", observers=" + observers +
                ", state=" + state +
                '}';
    }
}
