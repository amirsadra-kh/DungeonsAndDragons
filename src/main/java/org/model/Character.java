package main.java.org.model;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import main.java.org.Service.Observer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.HashSet;

/**
 * This class is the character object
 *
 * @author Parisa Nikzad
 * @version 1.0
 * @since 2017-02-23
 */
@XmlRootElement
public class Character {
    private BackPackInventory backPackInventory;
    private Point currentPosition;
    private Ability ability;
    private boolean isPlayerCharacter;
    private String charName;
    private HashSet<Item> itemsWearing = new HashSet<>();
    private int level;

    // A base line for the hit points
    RollDice dice10 = new RollDice(10);
    public int dice = dice10.roll();
    private int hitPoints = dice;

    // For the observer
    private java.util.List<Observer> observers = new ArrayList<>();
    private Ability state;

    /**
     * Empty Character Constructor
     */
    public Character() {
    }

    public void newCharacter() {
        this.isPlayerCharacter = false;
        this.level = 1;
    }

    /**
     * A method for getting the current position of the character
     *
     * @return the current position as an x,y point
     */
    public Point getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * A method for setting the current position of a character
     *
     * @param currentPosition an x,y point of the character on a map
     */
    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * A method to get the ability of a character
     *
     * @return the ability of a character
     */
    public Ability getAbility() {
        return ability;
    }

    /**
     * set the level of the Character
     *
     * @param level a level integer to change the level to.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Get the level of the character
     *
     * @return the level of the character
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * This method set the HitPoints based on the Strength modifier.
     */
    public void setHitPoints() {
        Strength strength = new Strength();
        int integerStrength  = this.ability.getStrength();
        strength.set(integerStrength);
        this.hitPoints = strength.modifier() + dice;
    }

    /**
     * This method is for getting the HitPoints of a character
     *
     * @return the hitPoints of a character
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
    public void setItemsWearing(HashSet<Item> items) {
        this.itemsWearing = items;
        setHitPoints();
    }

    /**
     * A method for getting the items the character is wearing.
     *
     * @return the items the character is wearing.
     */
    @XmlElement
    public HashSet<Item> getItemsWearing() {
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

    /**
     * This method is for attacks when the game is being played.
     */
    public void attack() {

    }

    /**
     * This method is for getting the items in the backpack inventory of a character
     *
     * @return a list of items that are in the backpack
     */
    @XmlElement
    public List<Item> getBackPackInventory() {
        if(backPackInventory!=null){
            return backPackInventory.getItems();
        }
        List<Item> items=new ArrayList<>();
         items.add(new Item());
        return items;
    }

    /**
     * This method is for setting the backpack inventory of a character
     *
     * @param backPackInventory the backpack inventory to set the backpack to
     */
    public void setBackPackInventory(BackPackInventory backPackInventory) {
        this.backPackInventory = backPackInventory;
    }

    /**
     * This method is for knowing if the character is the player or a non-player character
     *
     * @return true or false based in if this is the character being played or not
     */
    public boolean isPlayerCharacter() {
        return isPlayerCharacter;
    }

    /**
     * This method is for setting the value of the playerCharacter
     *
     * @param playerCharacter true or false based on if this is th echaracter being played or not.
     */
    public void setPlayerCharacter(boolean playerCharacter) {
        isPlayerCharacter = playerCharacter;
    }

    /**
     * A method for setting the name of the character.
     *
     * @param name of the character
     */
    @XmlElement
    public void setCharName(String name) {
        this.charName = name;
    }

    /**
     * A method for getting the name of a character
     *
     * @return the name of the character
     */
    public String getCharName() { return this.charName; }

    /**
     * A method to get the character object as a string
     *
     * @return a string containing the name, ability and hitPoints of a character
     */
    public String charString() {
        return "Name: " +this.charName +"\nAbility: " +this.ability.toString() +"\nHit points: " +this.hitPoints;
    }

    /**
     * A method for saving a character
     */
    public void saveCharacter()  {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Character.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(this,new FileOutputStream("src/main/java/org/resources/characters/"+this.charName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method for loading an existing character
     *
     * @param name of the character
     * @return an existing character object
     */
    public Character loadCharacter(String name){
        try {
            JAXBContext jc = JAXBContext.newInstance(Character.class);
            Unmarshaller u = null;
            u = jc.createUnmarshaller();
            File f = new File("src/main/java/org/resources/characters/"+name);
            return (Character) u.unmarshal(f);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * A method to get the state of the character's ability
     *
     * @return state of the inventory
     */
    public Ability getState() {
        return this.state;
    }

    /**
     * A method to set the state of the character's ability
     *
     * @param state of the inventory
     */
    public void setState(Ability state) {
        this.state = state;
        notifyAllObservers();
    }

    /**
     * A method to attach the observer to the character's ability
     *
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
