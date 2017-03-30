package main.java.org.model.CharacterPackage;

/**
 * Director of the Builder pattern
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-30
 */
public class AbilityScoreDirector {
    /**
     * The Character is to use a specific fighter type to build the ability scores
     */
    private AbilityScoreBuilder builder;

    /**
     * A setter for the builder
     * @param newAbilityScoreBuilder
     */
    public void setBuilder(AbilityScoreBuilder newAbilityScoreBuilder) {
        builder = newAbilityScoreBuilder;
    }

    /**
     * A method for creating a new ability and
     * to set it according to the builder type -
     * fighter type
     */
    public void constructAbility() {
        builder.createNewAbility();
        builder.buildAbilityScores();
    }

    /**
     * A method to get the new ability
     * @return new ability after it has been built
     */
    public Ability getAbility() {
        return builder.getAbility();
    }
}
