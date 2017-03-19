package main.java.org.Service;

import main.java.org.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * This class is is to Item objects
 *
 * @author Sadra
 * @version 2.0
 * @since 2017-03-01
 */
public class ItemScreen {
    private ReadInput readInput = new ReadInput();

    /**
     *  A method for interacting with the user to create or edit an item.
     * @return an item
     */
    public Item askUserToCreateOrEditItem() {
        int choice = 0;

        // Let user choose an action - Create or Edit an Item
        System.out.println("Choose one of the following by entering the number associated with the choice:");
        System.out.println("1. Create an Item\n2. Edit an Item\n3. Back to Main Menu");
        while(choice == 0)
            choice = readInput.readIntHandling(choice);

        // If the user enters an invalid input, they will be asked again
        while (choice < 1 || choice > 3) {
            System.out.println("Your input is invalid, please try again");
            choice = readInput.readIntHandling(choice);
        }

        switch (choice) {
            case 1:
                return create();
            case 2:
                return edit();
            case 3:
                return null;
        }

        return null;
    }

    /**
     * This method is for interacting with the user to edit an item
     * @return an edited Item object
     */
    private Item edit() {
        Item item = new Item();
        String itemName = "";
        boolean itemExists = false;

        while("".equalsIgnoreCase(itemName) || GameConstantsInterface.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(itemName) || itemExists == false) {
            System.out.println("Please enter the name of the Item you would like to edit:");
            new ObjectLoader().showItemNames("src/main/java/org/resources/items/");
            itemName = readLine();

            // Check if an item with the name chosen does not exists
            if (item.loadItem(itemName) != null) {
                itemExists = true;
            } else {
                System.out.println("An Item with this name does not exists!");
            }
        }


        item = item.loadItem(itemName);

        System.out.println("Your Item had the followings:" + item.toString());
        //String itemEnum = getItemEnum();
        String enhancement = getEnhancementType(item.getItem().name());
        Item itemToCreate = new Item(itemName, getItemEnumfromString(item.getItem().name()), getEnhancementEnumfromString(enhancement), getEnhancementAmount());
        itemToCreate.saveItem();

        return itemToCreate;
    }

    /**
     * This method is for interacting with the user to create a new item
     * @return a new Item object.
     */
    private Item create() {
        Item itemObj = new Item();
        String itemName = "";
        boolean itemExists = true;

        while("".equalsIgnoreCase(itemName) || GameConstantsInterface.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(itemName) || itemExists) {
            System.out.println("Enter the name of the new item: ");
            itemName = readLine();

            // Check if an item with the name chosen already exists
            if (itemObj.loadItem(itemName) == null) {
                itemExists = false;
            } else {
                System.out.println("An Item with this name already exists!");
            }
        }

        String item = getItemEnum();
        String enhancement = getEnhancementType(item);
        int enhancementAmount = getEnhancementAmount();
        Item itemToCreate = new Item(itemName, getItemEnumfromString(item), getEnhancementEnumfromString(enhancement), enhancementAmount);

        itemToCreate.saveItem();

        return itemToCreate;
    }

    /**
     * This method is to interact with the user to get the item type from the user
     * @return a string with the item type
     */
    public String getItemEnum() {
        // Get input from user
        System.out.println("Please enter your item Type from the provided list below:");
        for (ItemEnum e : ItemEnum.values()) {
            System.out.println(e.ordinal() + ". " + e.name());
        }

        ArrayList<String> itemsArray = new ArrayList<>();
        for (ItemEnum e : ItemEnum.values()) {
            itemsArray.add(e.ordinal(), e.name());
        }
        String item = readLine().toUpperCase();

        while (!itemsArray.contains(item)) {
            System.out.println("The Entered Item is not valid! \nPlease enter your item Type ");
            item = readLine().toUpperCase();
        }

        System.out.println("ITEM RECEIVED SUCCESSFULLY!");
        return item;
    }

    /**
     * This method is for getting the enhancement type from the user
     * @param item String with the item type
     * @return a string with the enhancement type
     */
    private String getEnhancementType(String item) {
        ArrayList<String> EnhancementArray = new ArrayList<>();
        // Get the user to choose which enhancement type to define
        System.out.println("Please enter your Enhancement from the provided list below:");

        if (item.equals("HELMET") || item.equals("ARMOR") || item.equals("SHIELD")) {
            System.out.println(EnhancementTypesEnum.ARMORCLASS);
            EnhancementArray.add("ARMORCLASS");
        } else if (item.equals("RING")) {
            System.out.println("" +EnhancementTypesEnum.ARMORCLASS + "\n" + EnhancementTypesEnum.CONSTITUTION + "\n"
                    + EnhancementTypesEnum.STRENGTH);
            EnhancementArray.add("ARMORCLASS");
            EnhancementArray.add("CONSTITUTION");
            EnhancementArray.add("STRENGTH");
        } else if (item.equals("BELT")) {
            System.out.println(EnhancementTypesEnum.CONSTITUTION + "\n" + EnhancementTypesEnum.STRENGTH);
            EnhancementArray.add("CONSTITUTION");
            EnhancementArray.add("STRENGTH");
        } else if (item.equals("BOOTS")) {
            System.out.println(EnhancementTypesEnum.ARMORCLASS + "\n" + EnhancementTypesEnum.DEXTERITY);
            EnhancementArray.add("ARMORCLASS");
            EnhancementArray.add("DEXTERITY");
        } else {
            System.out.println(EnhancementTypesEnum.ATTACKBONUS + "\n" + EnhancementTypesEnum.DAMAGEBONUS);
            EnhancementArray.add("ATTACKBONUS");
            EnhancementArray.add("DAMAGEBONUS");
        }

        String enhancement = readLine().toUpperCase();
        EnhancementArray = new ArrayList<>();
        for (EnhancementTypesEnum e : EnhancementTypesEnum.values()) {
            EnhancementArray.add(e.ordinal(), e.name());
        }
        while (!EnhancementArray.contains(enhancement)) {
            System.out.println("The Entered Enhancement is not valid! \nPlease enter your Enhancement from the provided list below:");
            for (int i = 0; i < EnhancementArray.size(); i++) {
                System.out.println(EnhancementArray.get(i));
            }
            enhancement = readLine().toUpperCase();

            // Exit while loop if the input is correct.
            if(EnhancementArray.contains(enhancement)){
                break;
            }
            enhancement = readLine().toUpperCase();
        }
        System.out.println("ENHANCEMENT RECEIVED SUCCESSFULLY");
        return enhancement;
    }

    /**
     * A method to get the enhancement amount from user for an item
     * @return an integer between +1 and +5 for the enhancement amount
     */
    private int getEnhancementAmount(){
        int enhancementAmount = 0;
        try {
            System.out.println("Please give the enhancement amount (from 1-5): ");
            enhancementAmount = Integer.parseInt(readLine());
            while (enhancementAmount > 5 || enhancementAmount < 1) {
                System.out.println("Invalid input! Please enter between 1 to 5." + '\n');
                enhancementAmount = Integer.parseInt(readLine());
            }
        }
        catch (InputMismatchException exception){
            System.out.println("Input is not an integer.");
        }
        return enhancementAmount;
    }

    /**
     * A method for reading string input from user
     * @return input String
     */
    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * A method for getting the ItemEnum from a string
     * @param item A String containing the item type
     * @return an ItemEnum gotten from the String inputted
     */
    private ItemEnum getItemEnumfromString(String item) {
        List<ItemEnum> items = Arrays.asList(ItemEnum.values());
        for (ItemEnum i : items) {
            if (i.name().equalsIgnoreCase(item)) {
                return i;
            }
        }
        return null;

    }

    /**
     * A method to get the EnhancementType from a string
     * @param enhancement a String containing an EnhancementType
     * @return an EnhancementType gotten from the String inputted
     */
    private EnhancementTypesEnum getEnhancementEnumfromString(String enhancement) {
        List<EnhancementTypesEnum> bonus = Arrays.asList(EnhancementTypesEnum.values());
        for (EnhancementTypesEnum i : bonus) {
            if (i.name().equalsIgnoreCase(enhancement)) {
                return i;
            }
        }
        return null;
    }


}
