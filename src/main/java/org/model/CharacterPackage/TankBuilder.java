package main.java.org.model.CharacterPackage;

/**
 * A TankBuilder class for the ability of a character
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-30
 */
public class TankBuilder extends AbilityScoreBuilder {
    /**
     * A Tank builder
     * Ability Scores in decreasing order of importance:
     * constitution, dexterity, strength
     */
    @Override
    void buildAbilityScores() {
        this.ability.setConstitution(this.dices[2]);
        this.ability.setDexterity(this.dices[1]);
        this.ability.setStrength(this.dices[0]);

        // Set the modifiers of those abilities
        this.ability.setConstitutionModifier();
        this.ability.setDexterityModifier();
        this.ability.setStrengthModifier();
    }
}
