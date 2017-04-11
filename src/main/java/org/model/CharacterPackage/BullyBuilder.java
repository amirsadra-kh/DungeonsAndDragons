package main.java.org.model.CharacterPackage;

/**
 * A BullyBuilder class for the ability of a character
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-30
 */
public class BullyBuilder extends AbilityScoreBuilder {
    /**
     * A Bully builder
     * Ability Scores in decreasing order of importance:
     * strength, dexterity, constitution
     */
    @Override
    void buildAbilityScores() {
        // Set the abilities
        this.ability.setConstitution(this.dices[1]);
        this.ability.setDexterity(this.dices[0]);
        this.ability.setStrength(this.dices[2]);

        // Set the modifiers of those abilities
        this.ability.setConstitutionModifier();
        this.ability.setDexterityModifier();
        this.ability.setStrengthModifier();
    }
}
