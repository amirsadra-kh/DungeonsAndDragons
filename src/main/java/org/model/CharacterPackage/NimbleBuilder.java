package main.java.org.model.CharacterPackage;

/**
 * A NimbleBuilder class for the ability of a character
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-30
 */
public class NimbleBuilder extends AbilityScoreBuilder {
    /**
     * A Nimble builder
     * Ability Scores in decreasing order of importance:
     * Dexterity, Constitution, Strength
     */
    @Override
    void buildAbilityScores() {
        this.ability.setConstitution(this.dices[0]);
        this.ability.setDexterity(this.dices[1]);
        this.ability.setStrength(this.dices[2]);
    }
}
