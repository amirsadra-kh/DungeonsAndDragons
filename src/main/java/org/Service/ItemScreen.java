package main.java.org.Service;

import main.java.org.model.EnhancementTypes;
import main.java.org.model.GameConstants;
import main.java.org.model.Item;
import main.java.org.model.ItemEnum;

import java.util.*;

/**
 * This class is is to Item objects
 *
 * @author Sadra
 * @version 1.0
 * @since 2017-03-01
 */
public class ItemScreen {

    /**
     * A method for reading an integer input from user and handling a wrong input
     *
     * @param num an input from the user
     * @return the integer if it was in fact an integer
     */
    private int readInt(int num){
        try{
            num = Integer.parseInt(readLine());
        } catch (NumberFormatException e){
            System.out.println(GameConstants.NOT_A_NUMBER);
            System.out.println(GameConstants.CHOSEN_ITEM_NOT_VALID);
        }
        return num;
    }

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
            choice = readInt(choice);

        // If the user enters an invalid input, they will be asked again
        while (choice < 1 || choice > 3) {
            System.out.println("Your input is invalid, please try again");
            choice = readInt(choice);
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
     * TODO add comment here
     * @return
     */
    private Item edit() {
        Item item = new Item();
        System.out.println("Please enter the name of the Item you would like to edit:");
        new ObjectLoader().showItemNames("src/main/java/org/resources/items/");
        item = item.loadItem(readLine());
        System.out.println("Your Item had the followings:" + item.toString());
        //String itemEnum = getItemEnum();
        String enhancement = getEnhancementType(item.getItem().name());
        Item itemToCreate = new Item(getItemEnumfromString(item.getItem().name()), getEnhancementEnumfromString(enhancement), getEnhancementAmount());
        itemToCreate.saveItem();

        return itemToCreate;
    }

    /**
     * TODO add comment here
     * @return
     */
    private Item create() {
        String item = getItemEnum();
        String enhancement = getEnhancementType(item);
        int enhancementAmount = getEnhancementAmount();

        Item itemToCreate = new Item(getItemEnumfromString(item), getEnhancementEnumfromString(enhancement), enhancementAmount);

        itemToCreate.saveItem();

        return itemToCreate;

    }

    /**
     * TODO add comment here
     * @return
     */
    public String getItemEnum() {
        // Get input from user
        System.out.println("Please enter your item Type from the provided list below:");
        for (ItemEnum e : ItemEnum.values()) {
            System.out.println(e.ordinal() + ". " + e.name());
        }
        String item = readLine();

        ArrayList<String> itemsArray = new ArrayList<>();
        for (ItemEnum e : ItemEnum.values()) {
            itemsArray.add(e.ordinal(), e.name());
        }
//        TODO fix this, this fails and run in an infinite loop.
        while (!itemsArray.contains(item)) {
            System.out.println("The Entered Item is not valid! \nPlease enter your item Type from the provided list below:");
            for (ItemEnum e : ItemEnum.values()) {
                System.out.println(e.ordinal() + ". " + e.name());
            }
        }
        System.out.println("ITEM RECEIVED SUCCESSFULLY!");
        return item;
    }

    /**
     * TODO add comment here!!
     * @param item
     * @return
     */
    private String getEnhancementType(String item) {
        // Get the user to choose which enhancement type to define
        System.out.println("Please enter your Enhancement from the provided list below:");

        // TODO ask user here for enhancement number to decrease number of steps for user
        // TODO also user should choose a number for all enhancement types related to an item and not just one.
        if (item.equals("HELMET") || item.equals("ARMOR") || item.equals("SHIELD")) {
            System.out.println(EnhancementTypes.ARMORCLASS);
        } else if (item.equals("RING")) {
            System.out.println("" +EnhancementTypes.ARMORCLASS + "\n" + EnhancementTypes.CONSTITUTION + "\n" + EnhancementTypes.STRENGTH);
        } else if (item.equals("BELT")) {
            System.out.println(EnhancementTypes.CONSTITUTION + "\n" + EnhancementTypes.STRENGTH);
        } else if (item.equals("BOOTS")) {
            System.out.println(EnhancementTypes.ARMORCLASS + "\n" + EnhancementTypes.DEXTERITY);
        } else {
            System.out.println(EnhancementTypes.ATTACKBONUS + "\n" + EnhancementTypes.DAMAGEBONUS);
        }
        String enhancement = readLine();
        ArrayList<String> EnhancementArray = new ArrayList<>();
        for (EnhancementTypes e : EnhancementTypes.values()) {
            EnhancementArray.add(e.ordinal(), e.name());
        }
        while (!EnhancementArray.contains(enhancement)) {
            System.out.println("The Enetered Enhancement is not valid! \nPlease enter your Enhancement from the provided list below:");
            for (EnhancementTypes e : EnhancementTypes.values()) {
                System.out.println(e.ordinal() + ". " + e.name());
            }
            enhancement = readLine();
        }
        System.out.println("ENHANCEMENT RECEIVED SUCCESSFULLY");
        return enhancement;
    }

    /**
     * TODO add comment here!!
     * @return
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
     * TODO add comment here!!
     * @return
     */
    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * TODO add comment here!!
     * @param item
     * @return
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
     * TODO add comment here!!
     * @param enhancement
     * @return
     */
    private EnhancementTypes getEnhancementEnumfromString(String enhancement) {
        List<EnhancementTypes> bonus = Arrays.asList(EnhancementTypes.values());
        for (EnhancementTypes i : bonus) {
            if (i.name().equalsIgnoreCase(enhancement)) {
                return i;
            }
        }
        return null;
    }


}
