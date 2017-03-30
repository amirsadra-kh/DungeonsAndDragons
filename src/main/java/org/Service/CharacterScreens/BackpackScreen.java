package main.java.org.Service.CharacterScreens;

import main.java.org.Service.ObjectLoader;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.Item;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.ReadInput;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to interact with the user for the character's backpack
 *
 * @author Freyja Jokulsdottir
 * @version 2.0
 * @since 2017-02-23
 */
public class BackpackScreen {
    ReadInput readInput = new ReadInput();

    /**
     * A method for creating a backpackInventory for a new character
     * @param character
     */
    public void createBackpack(Character character){
        List<Item> backpackItems = new ArrayList<>();

        // Add to backpack?
        addItemsInteraction(backpackItems, character);
    }

    /**
     * A main method for editing a backpackInventory of a existing character
     * @param character
     */
    public void editBackpack(Character character){
        BackPackInventory backPackInventory = character.getBackPackInventory();
        List<Item> backpackItems = character.getBackPackInventoryItems();

        // Add to backpack?
        addItemsInteraction(backpackItems, character);

        // Remove from backpack?
        backPackInventory = choosItemToRemove(backpackItems);
        character.setBackPackInventory(backPackInventory);
    }

    /**
     * A method to interact with the user and ask if they want to add an item to backpack
     * @param backpackItems
     * @param character
     */
    private void addItemsInteraction(List<Item> backpackItems, Character character) {
        System.out.println("Would you like to add to the backpack items for the character? Y/N");
        while (true) {
            String answer = readInput.readLine().trim().toLowerCase();
            if (answer.equals("y")) {
                System.out.println("The backpack inventory choices: ");
                BackPackInventory backpack = chooseItemsToAdd(backpackItems);
                character.setBackPackInventory(backpack);
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
        }
    }

    /**
     * A method for choosing the items to adding to the backpack and add them to the backpack
     * @param backpackItems
     * @return a new backpack inventory
     */
    private BackPackInventory chooseItemsToAdd(List<Item> backpackItems) {
        int size = 10;
        boolean yn = true;
        String answer = "";

        // Show user the items to pick from
        for (int i = 1; i <= size && yn; i++) {
            System.out.println("Please enter the name of the item that you want to add to the backpack from the list below:");
            new ObjectLoader().showItemNames("src/main/java/org/resources/items/");
            String itemName = readInput.readLine();
            Item item = new Item();
            item = item.loadItem(itemName);

            // Check if the item entered exists
            if(item == null){
                System.out.println("This item does not exist");
                i = i-1;
            }

            backpackItems.add(item);

            // Check if the user wants to add more items.
            System.out.println("do you  want to add the another item ? Y/N");
            yn = readInput.askUserIfAgain();
        }

        BackPackInventory backPackInventory = new BackPackInventory();
        backPackInventory.setItems(backpackItems);
        return backPackInventory;
    }

    /**
     * A method for interacting with user to remove an item from the backapck
     * @param backpackItems
     * @return backpackInventory
     */
    private BackPackInventory choosItemToRemove(List<Item> backpackItems) {
        String answer = "";
        boolean yes = true;

        System.out.println("Would you like to remove from the items in the backpack? Y/N");
        while (yes) {
            answer = readInput.readLine().trim().toLowerCase();
            if (answer.equals("y")) {
                System.out.println("Choose an item from the list below of the items in the backpack: ");
                for(Item item : backpackItems)
                    System.out.println(item.getName());
                if(backpackItems.size() == 0) {
                    System.out.println("No items to remove!");
                    // Return
                    BackPackInventory backPackInventory = new BackPackInventory();
                    backPackInventory.setItems(backpackItems);
                    return backPackInventory;
                }
                String itemName = readInput.readLine();
                Item item = new Item();
                item = item.loadItem(itemName);

                // Check if the item entered exists
                if (item == null) {
                    System.out.println("This item does not exist");
                    // Return
                    BackPackInventory backPackInventory = new BackPackInventory();
                    backPackInventory.setItems(backpackItems);
                    return backPackInventory;
                } else {
                    backpackItems.remove(item);
                    // Return
                    BackPackInventory backPackInventory = new BackPackInventory();
                    backPackInventory.setItems(backpackItems);
                    return backPackInventory;
                }
            } else if (answer.equals("n")) {
                // Return
                BackPackInventory backPackInventory = new BackPackInventory();
                backPackInventory.setItems(backpackItems);
                return backPackInventory;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
            // Check if the user wants to add more items.
            System.out.println("do you  want to add the another item ? Y/N");
            yes = readInput.askUserIfAgain();
        }

        // Return
        BackPackInventory backPackInventory = new BackPackInventory();
        backPackInventory.setItems(backpackItems);
        return backPackInventory;
    }
}
