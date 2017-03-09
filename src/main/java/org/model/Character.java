package main.java.org.model;

import java.awt.*;
import java.util.Set;

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
    private Set<Item> itemsWearing;

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
}
