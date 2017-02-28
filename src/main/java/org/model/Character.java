package main.java.org.model;

import java.awt.*;

public class Character {
    BackPackInventory backPackInventory;
    private Point currentPosition = new Point(0,0);
    private Ability ability;
    private boolean isPlayerCharacter;
    private String charName;

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        ability.setArmorClass(0);
        ability.setDamageBonus(0);
        ability.setAttackBonus(0);
        ability.setLevel(1);
        this.ability = ability;
    }
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
        String character = charName +"," +this.ability.toString();
    }
}
