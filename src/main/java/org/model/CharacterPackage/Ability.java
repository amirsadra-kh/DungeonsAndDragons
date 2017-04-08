package main.java.org.model.CharacterPackage;

import main.java.org.Service.Calculation;

import javax.xml.bind.annotation.XmlElement;
import java.lang.*;

/**
 * A class for the ability object
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-23
 */
public class Ability {
    private Calculation score = new Calculation();
    private int strength;
    private int strengthModifier;
    private int constitution;
    private int constitutionModifier;
    private int dexterity;
    private int dexterityModifier;

    private int armorClass;
    private int attackBonus;
    private int damageBonus;


    /**
     * a modifier method for the three ability scores
     * @param value This parameter is the value to calculate the modifier.
     */
    public int setModifier(int value) {
        float floatValue = (float) value;
        int modifying = 0;

        floatValue = (floatValue - 10 )/2;
        if(floatValue < 0){
            modifying = 0;
        }else{
            modifying = (int)Math.ceil(floatValue);
        }
        return modifying;
    }

    /**
     * A getter for the strength
     * @return return the strength value
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * A setter for strength
     * @param value set the strength value
     */
    public void setStrength(int value) {
        this.strength = value;

    }

    /**
     * A method to set the strength modifier
     */
    public void setStrengthModifier() {
        strengthModifier = setModifier(this.strength);
    }

    /**
     * A getter for the strength modifier
     * @return the strength modifier
     */
    @XmlElement
    public int getStrengthModifier() {
        return this.strengthModifier;
    }

    /**
     * A getter for constitution
     * @return the constitution  value
     */
    public int getConstitution() {
        return this.constitution;
    }

    /**
     * A getter for constitution
     * @param value the constitution value
     */
    public void setConstitution(int value) {
        this.constitution = value;
    }

    /**
     * A method to set the constitution modifier
     */
    public void setConstitutionModifier() {
        constitutionModifier = setModifier(this.constitution);
    }

    /**
     * A getter for the constitution modifier
     * @return the constitution modifier
     */
    @XmlElement
    public int getConstitutionModifier() {
        return this.constitutionModifier;
    }

    /**
     * A getter for dexterity
     * @return the value of dexterity of  character
     */
    public int getDexterity() {
        return this.dexterity;
    }

    /**
     * A setter for dexterity
     * @param value set the dexterity value
     */
    public void setDexterity(int value) {
        this.dexterity = value;
    }

    /**
     * A method to set the dexterity modifier
     */
    public void setDexterityModifier() {
        dexterityModifier = setModifier(this.dexterity);
    }

    /**
     * A getter for the dexterity modifier
     * @return the dexterity modifier int value
     */
    @XmlElement
    public int getDexterityModifier() {
        return this.dexterityModifier;
    }

    /**
     * A method to get the armorclass
     * @return the armorclass value
     */
    @XmlElement
    public int getArmorClass() {
        return this.armorClass;
    }

    /**
     * This method set the ArmorClass base on dexterity modifier and predefined armorClass
     *
     * @param armorClass
     */
    public void setArmorClass(int armorClass) {
        this.armorClass = 10 + this.dexterityModifier + armorClass;
    }

    /**
     * A getter for the attackBonus
     * @return an integer value for the attackBonus
     */
    public int getAttackBonus() {
        return this.attackBonus;
    }

    /**
     * Set the attackBonus
     * @param level, the level of the character
     */
    public void setAttackBonus(int level, int itemEnhancement) {
        this.attackBonus = level + itemEnhancement;
    }

    /**
     * A method to get the damageBonus
     * @return the damageBonus
     */
    public int getDamageBonus() {
        return this.damageBonus;
    }

    /**
     * Set the DamageBonus base on the strength modifier and any Bonus to items that are active.
     * @param itemBonus
     */
    public void setDamageBonus(int itemBonus) {

        this.damageBonus = this.strengthModifier + itemBonus;
    }

    /**
     * Overriding the toString method of ability
     * @return a String with info about the ability
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ability{strength = ").append(this.strength).append(", strength modifier = ");
        sb.append(this.strengthModifier).append(", constitution=").append(this.constitution);
        sb.append(", constitution modifier = ").append(this.constitutionModifier).append(", dexterity=");
        sb.append(this.dexterity).append(", dexterity modifier = ").append(this.dexterityModifier);
        sb.append(", armorClass=").append( this.armorClass).append( ", attackBonus=");
        sb.append(this.attackBonus).append(", damageBonus=").append(this.damageBonus).append('}');
        return sb.toString();
    }
}
