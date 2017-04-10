package main.java.org.model;

import main.java.org.Service.Calculation;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.DecoratorPackage.Weapon;

/**
 * A melee weapon implemented with d8 - Longsword
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 08.04.2017
 */
public class Longsword extends Weapon {
    private int damage = 0;

    /**
     * A method to set the damage of the longsword using d8
     * It also outputs the log of how the damage is calculated
     */
    public void setDamage(Character character) {
        Calculation roll = new Calculation();
        int d8 = roll.getDice8();
        int strengthMod = character.getAbility().getStrengthModifier();
        // Print log window info about damage
        log(character, d8, strengthMod);
        // Calculate total damage
        this.damage = d8 + strengthMod;
        System.out.println(ColorConstants.ANSI_RED +"Total: " +this.damage +ColorConstants.ANSI_RESET);
    }

    /**
     * A method for the log window for the damage roll and strength modifier
     * @param character
     * @param d8
     * @param strengthMod
     */
    private void log(Character character, int d8, int strengthMod) {
        System.out.println("------------------------Log Window-------------------------");
        System.out.println(ColorConstants.ANSI_RED +"Damage roll of Longsword (d8): " +d8 +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_GREEN +character.getCharName() +" strength modifier: "
                +strengthMod +ColorConstants.ANSI_RESET);
    }

    /**
     * A method to get the damage of the longbow
     * @return damage integer
     */
    public int getDamage() {
        return this.damage;
    }
}
