package main.java.org.model.DecoratorPackage;

/**
 * A Weapon factory
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 06.04.2017
 */
public class WeaponFactory {
    /**
     * A weapon factory constructor
     */
    private WeaponFactory() {}

    /**
     * A method to set the WeaponEnhancementDecorator of a weapon item.
     * @param weapon to be set
     * @param choice of decorator
     * @return weapon with enhancement
     */
    static public Weapon setSpecialEnhancement(Weapon weapon, int choice) {
        switch (choice) {
            // Freezing
            case 1:
                weapon = new Freezing(weapon);
                System.out.println("FREEZING ADDED SUCCESSFULLY!");
                break;
            // Burning
            case 2:
                weapon = new Burning(weapon);
                System.out.println("BURNING ADDED SUCCESSFULLY!");
                break;
            // Slaying
            case 3:
                weapon = new Slaying(weapon);
                System.out.println("SLAYING ADDED SUCCESSFULLY!");
                break;
            // Frightening
            case 4:
                weapon = new Frightening(weapon);
                System.out.println("FRIGHTENING ADDED SUCCESSFULLY!");
                break;
            // Pacifying
            case 5:
                weapon = new Pacifying(weapon);
                System.out.println("PACIFYING ADDED SUCCESSFULLY!");
                break;
        }

        return weapon;
    }
}
