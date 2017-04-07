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
     * dexterity, constitution, strength
     */
    @Override
    void buildAbilityScores() {
        // Set the abilities
        this.ability.setConstitution(this.dices[1]);
        this.ability.setDexterity(this.dices[2]);
        this.ability.setStrength(this.dices[0]);

        // Set the modifiers of those abilities
        this.ability.setConstitutionModifier();
        this.ability.setDexterityModifier();
        this.ability.setStrengthModifier();
    }
}
