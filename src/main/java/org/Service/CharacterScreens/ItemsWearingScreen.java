package main.java.org.Service.CharacterScreens;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.DecoratorPackage.Weapon;
import main.java.org.model.Item;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.ReadInput;

import java.util.HashSet;

/**
 * A class to interact with the user for the character's wearing items
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-23
 */
public class ItemsWearingScreen {
    ReadInput readInput = new ReadInput();

    /**
     * A main method for setting the items wearing of a new character
     * @param character
     */
    public void createItemsWearing(Character character){
        HashSet<Item> wearingItems = new HashSet<>();

        // Get the user to choose items for the character to wear
        addItemsInteraction(wearingItems, character);
    }

    /**
     * The main method for editing the itemsWearing of a character.
     * @param character
     */
    public void editItemsWearing(Character character){
        HashSet<Item> wearingItems = new HashSet<>();

        // Add items to wearing items?
        addItemsInteraction(wearingItems, character);

        // Remove items from wearing items?
        wearingItems = chooseItemsToRemove(wearingItems);

        // Set the new items after removing
        character.setItemsWearing(wearingItems);
    }

    /**
     * Ask the user if they want to add to the wearing items.
     * @param wearingItems
     * @param character
     */
    private void addItemsInteraction(HashSet<Item> wearingItems, Character character) {
        System.out.println("Would you like to add to the wearing items for the character? Y/N");
        while (true) {
            String answer = readInput.readLine().trim().toLowerCase();
            if (answer.equals("y")) {
                System.out.println("The character's item wearing choices: ");
                wearingItems = chooseItemsToAdd(wearingItems);
                character.setItemsWearing(wearingItems);
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
        }
    }

    /**
     * A method to interact with the user to choose an item to add to wearing items
     * @param wearingItems
     * @return new wearingItems set
     */
    private HashSet<Item> chooseItemsToAdd(HashSet<Item> wearingItems) {
        int size = 7;
        boolean yn = true;
        String answer = "";

        // Show user the items to pick from
        for (int i = 1; i <= size && yn; i++) {
            System.out.println("Please enter the name of the item that you want to add to the wearing items from the list below:");
            new ObjectLoader().showItemNames("src/main/java/org/resources/items/");
            String itemName = readInput.readLine();
            Item item = new Item();
            item = item.loadItem(itemName);

            // Check if the item entered exists
            if (item == null) {
                Weapon itemW = new Weapon();
                item = (Item) itemW.loadItem(itemName);

                if (item == null) {
                    System.out.println("This item does not exist");
                    i = i - 1;
                }
            }
            int itemsSize = wearingItems.size();

            wearingItems.add(item);

            if(itemsSize == wearingItems.size())
                System.out.println("The character cannot wear two items of the same type!\nItem was not added!");

            // Check if the user wants to add more items.
            System.out.println("do you  want to add the another item ? Y/N");
            yn = readInput.askUserIfAgain();
        }

        return wearingItems;
    }

    /**
     * A method to interact with the user to choose items to remove from wearing item.
     * @param wearingItems
     * @return new wearingItems set
     */
    private HashSet<Item> chooseItemsToRemove(HashSet<Item> wearingItems) {
        String answer = "";
        boolean yes = true;

        System.out.println("Would you like to remove from the items the character is wearing? Y/N");
        while (yes) {
            answer = readInput.readLine().trim().toLowerCase();
            if (answer.equals("y")) {
                System.out.println("Choose an item from the list below of the items wearing: ");
                for(Item item : wearingItems)
                    System.out.println(item.getName());
                if(wearingItems.size() == 0) {
                    System.out.println("No items to remove!");
                    return wearingItems;
                }
                String itemName = readInput.readLine();
                Item item = new Item();
                item = item.loadItem(itemName);

                // Check if the item entered exists
                if (item == null) {
                    System.out.println("This item does not exist");
                    return wearingItems;
                } else {
                    wearingItems.remove(item);
                    return wearingItems;
                }
            } else if (answer.equals("n")) {
                return wearingItems;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
            // Check if the user wants to add more items.
            System.out.println("do you  want to add the another item ? Y/N");
            yes = readInput.askUserIfAgain();
        }
        return wearingItems;
    }
}
