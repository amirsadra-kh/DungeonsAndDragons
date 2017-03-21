package main.java.org.Service;

import main.java.org.model.BackPackInventory;
import main.java.org.model.Character;
import main.java.org.model.Item;
import main.java.org.model.ReadInput;

import java.util.HashSet;
import java.util.List;

/**
 * A class to interact with the user to change the inventory of the player character if it is being observed
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-03-19
 */
public class InventoryScreen {
    private ReadInput readInput = new ReadInput();
    private Character observerChar;

    /**
     * A method to ask the user if they want to make any changes to the inventory.
     */
    public void InventoryScreen() {
        String choice = "";
        System.out.println("Would you like to move items in inventory?");
        choice = readInput.readStringHandling(choice);
        while (!"Y".equals(choice) && !"N".equals(choice) && !"y".equals(choice) && !"n".equals(choice)) {
            System.out.println("Invalid input! Please try again: ");
            choice = readInput.readStringHandling(choice);
        }

        if ("Y".equals(choice) || "y".equals(choice)) {
            changeInventory();
        }
    }

    /**
     * A method to get the character that is being observed.
     * @param observerChar
     */
    public void setObserverChar(Character observerChar) { this.observerChar = observerChar; }

    /**
     * A method to return the character being observed with new inventory.
     * @return observerChar
     */
    public Character getObserverChar() { return this.observerChar; }

    /**
     * A method to interact with the user to equip or unequip items the
     * player character is wearing or has in their backpack.
     */
    private void changeInventory() {
        int choice = 0;
        Item tempItem;
        List<Item> backpackItems;
        HashSet<Item> wearingItems;
        boolean added;

        System.out.println("Choose one of the following numbers:");
        //Ask user if they want to exchange an item in backpack and wearing
        System.out.println("1. Exchange backpack item with wearing item.");
        // Ask user if they want to take an item from backpack to add to wearing
        System.out.println("2. Take an item from backpack and add to wearing items.");
        // Ask user if they want to take an item from wearing to add to backpack
        System.out.println("3. Take an item from wearing items and add to the backpack.");
        // Ask user if they want to drop an item from wearing
        System.out.println("4. Drop a wearing item.");
        // Ask user if they want to drop an item from backpack
        System.out.println("5. Drop a backpack item.");
        while(choice < 1 || choice > 5)
            choice = readInput.readIntHandling(choice);

        switch (choice) {
            case(1):
                // Exchange backpack item with wearing item
                chooseItemToMove();
                break;
            case(2):
                // Take an item from backpack and add to wearing items
                tempItem = moveItemFromBackPack();
                backpackItems = this.observerChar.getBackPackInventoryItems();
                added = addItemToWearing(tempItem);
                if(added) {
                    backpackItems.remove(tempItem);
                    setNewBackpack(backpackItems);
                }
                break;
            case(3):
                // Take an item from wearing items and add to the backpack
                tempItem = moveItemFromWearing();
                wearingItems = this.observerChar.getItemsWearing();
                added = addItemToBackPack(tempItem);
                if(added) {
                    wearingItems.remove(tempItem);
                    this.observerChar.setItemsWearing(wearingItems);
                }
                break;
            case(4):
                // Drop a wearing item
                tempItem = moveItemFromWearing();
                wearingItems = this.observerChar.getItemsWearing();
                wearingItems.remove(tempItem);
                this.observerChar.setItemsWearing(wearingItems);
                break;
            case(5):
                // Drop a backpack item
                tempItem = moveItemFromBackPack();
                backpackItems = this.observerChar.getBackPackInventoryItems();
                backpackItems.remove(tempItem);
                setNewBackpack(backpackItems);
                break;
        }
    }

    /**
     * A method for interacting with the user for moving items
     */
    private void chooseItemToMove() {
        List<Item> backpack = this.observerChar.getBackPackInventoryItems();
        HashSet<Item> wearingItems = this.observerChar.getItemsWearing();
        Item backpackItem = new Item();
        Item wearingItem = new Item();

        // User chooses an item to move from backpack
        backpackItem = moveItemFromBackPack();
        backpack.remove(backpackItem);

        // User chooses an item to move from the wearing items
        wearingItem = moveItemFromWearing();
        wearingItems.remove(wearingItem);

        // Add the item from wearing to backpack
        backpack.add(wearingItem);
        // Add the item from backpack to wearing
        wearingItems.add(backpackItem);

        // Finalize move
        setNewBackpack(backpack);
        this.observerChar.setItemsWearing(wearingItems);
    }

    /**
     * A method to get the user to choose which item should be moved from wearing items
     * @return an item to be moved
     */
    private Item moveItemFromWearing() {
        String input = "";
        Item item = new Item();
        HashSet<Item> wearingItems = this.observerChar.getItemsWearing();

        System.out.println("Choose an item from the list below of items wearing to move: ");
        for (Item i : wearingItems)
            System.out.println(i.getName());
        input = readInput.readLine();

        // TODO Check if item exists and load it

        return item;
    }

    /**
     * A method to add an item to wearing items
     * @param item to add to wearing items
     * @return a boolean indicating if item was added or not
     */
    private boolean addItemToWearing(Item item) {
        HashSet<Item> wearingItems = this.observerChar.getItemsWearing();
        boolean successfullyAdded = false;
        int currentSize = wearingItems.size();

        wearingItems.add(item);
        // Check if the item was added - same type and it won't be added
        if(currentSize == wearingItems.size()) {
            System.out.println("The character already has an item of this type! Remove that first!");
        }
        else
            successfullyAdded = true;

        return successfullyAdded;
    }

    /**
     * A method to interact with user to get the item from backpack to move
     * @return an item to move
     */
    private Item moveItemFromBackPack() {
        String input = "";
        Item item = new Item();
        List<Item> backpack = this.observerChar.getBackPackInventoryItems();

        System.out.println("Choose an item from the list below of items in backpack to move: ");
        for (Item i : backpack)
            System.out.println(i.getName());
        input = readInput.readLine();

        // TODO Check if item exists and load it

        return item;
    }

    /**
     * A method to add an item to the backpack
     * @param item to be added to the backpack
     * @return a boolean to tell if the item was added or not
     */
    private boolean addItemToBackPack(Item item) {
        List<Item> backpack = this.observerChar.getBackPackInventoryItems();
        boolean successfullyAdded = false;

        if(backpack.size() < 10) {
            backpack.add(item);
            setNewBackpack(backpack);
            successfullyAdded = true;
        }
        else {
            System.out.println("The backpack is full! Drop an item to add more items!");
        }
        return successfullyAdded;
    }

    /**
     * A method to set the new backpackInventory of a character.
     * @param backpackItems
     */
    private void setNewBackpack(List<Item> backpackItems) {
        BackPackInventory backPackInventory = new BackPackInventory();
        backPackInventory.setItems(backpackItems);
        this.observerChar.setBackPackInventory(backPackInventory);
    }
}
