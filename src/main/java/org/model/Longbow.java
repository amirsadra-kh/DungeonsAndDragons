package main.java.org.model;

import main.java.org.Service.Calculation;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.DecoratorPackage.Weapon;

/**
 * A ranged weapon implemented with d8 - Longbow
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 08.04.2017
 */
public class Longbow extends Weapon {
    private int damage = 0;
    // The normal range of a longbow is 150 ft
    // The normal range is 2 cells in any direction
    private int normalRange = 2;
    // The maximum range of a longbow is 600 ft or 4xNormal range but 8 cells is too much since the maps are small
    // The maximum range is 4 cells in any direction
    private int maxRange = 4;

    /**
     * A method to set the damage of the longbow using d8
     * It also outputs the log of how the damage is calculated
     */
    public void setDamage(Character character) {
        Calculation roll = new Calculation();
        int d8 = roll.getDice8();
        int dexterityMod = character.getAbility().getDexterityModifier();
        // Print log window info about damage
        System.out.println("------------------------Log Window-------------------------");
        System.out.println(ColorConstants.ANSI_RED +"Damage roll of Longbow (d8): " +d8 +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_GREEN +character.getCharName() +" strength modifier: "
                +dexterityMod +ColorConstants.ANSI_RESET);
        // Calculate total damage
        this.damage = d8 + dexterityMod;
        System.out.println(ColorConstants.ANSI_RED +"Total: " +this.damage +ColorConstants.ANSI_RESET);
    }

    /**
     * A method to get the damage of the longbow
     * @return damage integer
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * A method to get the normal range of the longbow
     * @return the normal range
     */
    public int getNormalRange() {
        return this.normalRange;
    }

    /**
     * A method to get the maximum range of the longbow
     * @return the maximum range
     */
    public int getMaxRange() {
        return this.maxRange;
    }
}
