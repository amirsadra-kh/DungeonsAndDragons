package main.java.org.model.DecoratorPackage;

import main.java.org.model.*;
import main.java.org.model.CharacterPackage.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract Weapon class defines the functionality of any Weapon
 * as an extension to a regular item
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
public class Weapon extends Item {
    private String specialEnhancement = "Weapon";
    private int damage = 0;
    private int normalRange = 0;
    private int maxRange = 0;
    private String type;

    /**
     * A method to get the special enhancement of the weapon
     * TODO get this string to save with the weapon item
     * @return WeaponEnhanceDecorator
     */
    public String getSpecialEnhance() {
        return this.specialEnhancement;
    }

    /**
     * A method to set the damage and range according to the type of weapon
     * @param character
     */
    public void setDamage(Character character) {
        if(this.type.equalsIgnoreCase("longbow")) {
            Longbow longbow = new Longbow();
            longbow.setDamage(character);
            this.damage = longbow.getDamage();
            this.normalRange = longbow.getNormalRange();
            this.maxRange = longbow.getMaxRange();
        } else {
            Longsword longsword = new Longsword();
            longsword.setDamage(character);
            this.damage = longsword.getDamage();
        }
        this.damage += this.getEnhance();
        // Finish the log window of the bonus
        System.out.println(ColorConstants.ANSI_RED +"Enhancement of the weapon: +" +this.getEnhance()
                +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"Total AttackBonus or DamageBonus of the weapon: "
                +damage +ColorConstants.ANSI_RESET);
        System.out.println("------------------------------------------------------------");
    }

    /**
     * A setter for the type of the weapon; longsword or longbow
     * @param type
     */
    public void setType(String type)  {
        this.type = type;
    }

    /**
     * A getter for the type of the weapon
     * @return longsword or longbow
     */
    public String getType() {
        return this.type;
    }

    /**
     * A method to get the damage, AttackBonus or DamageBonus, of the weapon
     * @return
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * A method to get the normal range of the weapon
     * @return the normal range
     */
    public int getNormalRange() {
        return this.normalRange;
    }

    /**
     * A method to get the maximum range of the weapon
     * @return the maximum range
     */
    public int getMaxRange() {
        return this.maxRange;
    }
}
