package main.java.org.model.CharacterPackage;

import main.java.org.model.DecoratorPackage.CharacterStrategy;
import main.java.org.model.Item;
import main.java.org.model.StrategyPackage.BehaviourStrategy;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

/**
 * This class is the character object
 *
 * @author Parisa Nikzad
 * @version 1.0
 * @since 2017-02-23
 */
@XmlRootElement
public class Character extends Observable {
    private BackPackInventory backPackInventory;
    private Inventory inventory = new Inventory();
    private Point currentPosition;
    private Ability ability;
    private boolean isPlayerCharacter;
    private String charName;
    private HashSet<Item> itemsWearing = new HashSet<>();
    private int level;
    private String fighterType;
    private BehaviourStrategy behaviourStrategy;
    private CharacterStrategy characterStrategy;
    private boolean turn;
    private boolean burning;

    // A base line for the hit points
    RollDice dice10 = new RollDice(10);
    public int dice;
    private int hitPoints;

    // For the observer
    private List<Observer> observers = new ArrayList<>();
    private Ability state;

    /**
     * An empty constructor
     */
    public Character() {}

    /**
     * A method to get the fighter type of a character
     * @return fighterType String
     */
    @XmlElement
    public String getFighterType() { return this.fighterType; }

    /**
     * A method to create a new character.
     */
    public void newCharacter() {
        this.isPlayerCharacter = false;
        this.level = 1;
        this.dice = dice10.roll();
        this.hitPoints = dice;
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
        return this.ability;
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
     * This method set the HitPoints based on the strengthModifier.
     */
    public void setHitPoints() {
        int strengthModifier  = this.ability.getStrengthModifier();
        this.hitPoints = strengthModifier + this.dice;
    }

    /**
     * This method is for getting the HitPoints of a character
     *
     * @return the hitPoints of a character
     */
    @XmlElement
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * A method for decreasing the hitPoints in a fight
     * @param newHitPoint
     */
    public void decreaseHitPoint(int newHitPoint){
        if (this.hitPoints > 0){
            this.hitPoints -= newHitPoint;
        }
        else
            System.out.println(this.charName +" has died!");
    }

    public void increaseHitPoint(int newHitPoint){
        this.hitPoints = this.hitPoints + newHitPoint;
    }

    /**
     * A method for initializing the ability for the character
     *
     * @param ability object to be used to set the ability of this character
     */
    public void setAbility(Ability ability) {
        ability.setArmorClass(0);
        ability.setDamageBonus(0);
        ability.setAttackBonus(this.level, 0);
        this.ability = ability;
        this.setState(ability);
    }

    /**
     * A method for setting the items the character is wearing.
     *
     * @param items a set of items the user has chosen
     */
    public void setItemsWearing(HashSet<Item> items) {
        this.itemsWearing = items;
        for(Item i : items)
            updateAbility(i);

        this.inventory.setWearingItems(this.itemsWearing);
        List<Item> inventoryItems = this.inventory.getItems();
        this.inventory.setState(inventoryItems);
    }

    /**
     * A method to update the ability when the user adds or remove items wearing
     * @param item
     */
    private void updateAbility(Item item) {
        switch (item.getEnhancementType()) {
            case STRENGTH:
                this.ability.setStrength(this.ability.getStrength() + item.getEnhance());
                this.ability.setDamageBonus(item.getEnhance() - 1);
                setHitPoints();
                break;
            case CONSTITUTION:
                this.ability.setConstitution(this.ability.getConstitution() + item.getEnhance());
                break;
            case DEXTERITY:
                this.ability.setDexterity(this.ability.getConstitution() + item.getEnhance());
                break;
            case ARMORCLASS:
                this.ability.setArmorClass(this.ability.getArmorClass() + item.getEnhance() - 10);
                break;
            case ATTACKBONUS:
                this.ability.setAttackBonus(this.level, item.getEnhance());
                break;
            case DAMAGEBONUS:
                this.ability.setDamageBonus(item.getEnhance());
                break;
            case HITPOINTS:
                break;
            case LEVEL:
                break;
        }
    }

    /**
     * Plugs in a specific behaviour strategy to be used
     * @param behaviourStrategy
     */
    public void setBehaviourStrategy(BehaviourStrategy behaviourStrategy) {
        this.behaviourStrategy = behaviourStrategy;
    }

    /**
     * A method that executes different behaviour strategy depending on what
     * behaviour strategy was plugged in upon instantiation.
     */
    public void executeBehaviourStrategy() {
        this.behaviourStrategy.execute();
    }

    /**
     * Plugs in a specific character strategy to be used based on the weapon
     * enhancement
     *
     * @param characterStrategy
     */
    public void setCharacterStrategy(CharacterStrategy characterStrategy) {
        this.characterStrategy = characterStrategy;
    }

    /**
     * A method that executes different character strategy depending on what
     * character strategy was plugged in upon weapon enhancement.
     * @param enhancement the weapon enahancement integer
     */
    public void executeCharacterStrategy(int enhancement) {
        this.characterStrategy.execute(this, enhancement);
    }

    /**
     * A setter for the turn to tell if it's the character's turn or not
     * @param turn
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    /**
     * A getter for turn to tell if it is the character's turn or not
     * @return turn a boolean value
     */
    public boolean getTurn() {
        return this.turn;
    }

    /**
     * A method to set the burning boolean
     * @param b
     */
    public void setBurning(boolean b) {
        this.burning = b;
    }

    /**
     * A method to get the burning boolean
     * @return burning boolean
     */
    public boolean getBurning() {
        return this.burning;
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
    public BackPackInventory getBackPackInventory() {
        if(this.backPackInventory.getItems().size() != 0){
            return this.backPackInventory;
        }
        BackPackInventory backpack = new BackPackInventory();
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        backpack.setItems(items);
        return backpack;
    }

    /**
     * A method for getting the items in the backpack inventory
     * @return a list of items
     */
    public List<Item> getBackPackInventoryItems() {
        if(this.backPackInventory!=null){
            return this.backPackInventory.getItems();
        }
        else
            return null;
    }

    /**
     * This method is for setting the backpack inventory of a character
     *
     * @param backPackInventory the backpack inventory to set the backpack to
     */
    public void setBackPackInventory(BackPackInventory backPackInventory) {
        this.backPackInventory = backPackInventory;
        List<Item> backpack = this.backPackInventory.getItems();
        this.inventory.setBackpackItems(backpack);
        List<Item> inventoryItems = this.inventory.getItems();
        this.inventory.setState(inventoryItems);
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
        return "Name: " +this.charName +"\nmodel.Ability.Ability: " +this.ability.toString() +"\nHit points: " +this.hitPoints;
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
     * A method to notify all the observers.
     */
    public void notifyAllObservers(){
        for (Observer observer : this.observers) {
            observer.update(this, this.ability);
        }
    }

    @Override
    public String toString() {
        return "Character {" +
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
