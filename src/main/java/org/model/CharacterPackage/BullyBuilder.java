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
     * Strength, Dexterity, Constitution
     */
    @Override
    void buildAbilityScores() {
        this.ability.setConstitution(this.dices[0]);
        this.ability.setDexterity(this.dices[1]);
        this.ability.setStrength(this.dices[2]);
    }
}
