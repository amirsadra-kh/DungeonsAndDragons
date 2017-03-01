package main.java.org.model;

/**
 * A class for the ability object
 *
 * @author Parisa Nikzad
 * @version 1.0
 * @since 2017-02-23
 */
public class Ability {
    RollDice dice6 = new RollDice(6);
    RollDice dice10 = new RollDice(10);

    private int hitPoints = dice10.roll();

    private int level;
    private int armorClass;
    private int attackBonus;
    private int damageBonus;
    // These 3 are objects
    private Strength strength = new Strength();
    private Constitution constitution = new Constitution();
    private Dexterity dexterity = new Dexterity();


    /**
     * This method act as a wrapper of the Strength object.
     * @return return the strength object value
     */
    public int getStrength() {
        return strength.get();
    }

    /**
     * This method act as a wrapper of the Strength object.
     * @param value set a new Strength value in the strength object.
     */
    public void setStrength(int value) {
        this.strength.set(value);

    }

    /**
     * This method act as a wrapper of the Constitution object.
     * @return the constituion object value
     */
    public int getConstitution() {
        return this.constitution.get();
    }

    /**
     * This method act as a wrapper of the Constitution object.
     * @param value set a new Constitution value in the strength object.
     */
    public void setConstitution(int value) {
        this.constitution.set(value);
    }
    /**
     * This method act as a wrapper of the Dexterity object.
     * @return the calue of a Dexterity Object
     */
    public int getDexterity() {
        return this.dexterity.get();
    }

    /**
     * This method act as a wrapper of the Dexterity object.
     * @param value set a new Dexterity value in the strength object.
     */
    public void setDexterity(int value) {
        this.dexterity.set(value);
    }

    /**
     * This method set the HitPoint base on the Strength and Modifier.
     * @return integer value of the HitPoints
     * @todo This method should be move where we do an attack, it does not belong in ability.
     */
    public void setHitPoints() {
        this.hitPoints = this.strength.modifier() + this.hitPoints;
    }

    /**
     * @todo This method should be move where we do an attack, it does not belong in ability.
     * @return the hitPoints
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * set Level of the Character
     * @param level
     * @todo we should probably move this method in the Character class.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * This method set the ArmorClass base on dexteirty modifier and predefined armorClass
     * @param armorClass
     */
    public void setArmorClass(int armorClass) {
        this.armorClass = 10 + this.dexterity.modifier() + armorClass;
    }

    /**
     * Set the attackBonus
      * @param attackBonus
     */
    public void setAttackBonus(int attackBonus) {
        this.attackBonus = 1 + attackBonus;
    }

    /**
     * Set the DamageBonus base on the strength modfier and  any Bonus to items that are active.
     * @param itemBonus
     */
    public void setDamageBonus(int itemBonus) {
        this.damageBonus = this.strength.modifier() + itemBonus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ability{strength = ").append(strength.get()).append(", strength modifier = ").append(strength.modifier());
        sb.append(", constitution=").append(constitution.get()).append(", constitution modifier = ").append(constitution.modifier());
        sb.append(", dexterity=").append(dexterity.get()).append(", dexterity modifier = ").append(dexterity.modifier());
        sb.append(", hitPoints=").append(this.hitPoints).append(", level=").append(this.level).append(", armorClass=").append( this.armorClass);
        sb.append( ", attackBonus=").append(this.attackBonus).append(", damageBonus=").append(this.damageBonus).append('}');
        return sb.toString();
    }
}
