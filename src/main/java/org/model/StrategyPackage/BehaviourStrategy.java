package main.java.org.model.StrategyPackage;

/**
 * The classes that implement a concrete strategy should implement this.
 * The Character class uses this to use a concrete strategy for the behaviour of the character
 *
 * @author
 * @version
 * @since
 */
public interface BehaviourStrategy {
    /**
     * Method whose implementation varies depending on the strategy adopted
     */
    void execute();
}
