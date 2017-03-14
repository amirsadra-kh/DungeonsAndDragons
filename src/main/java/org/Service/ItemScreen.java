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
        String itemName = "";
        boolean itemExists = false;

        while("".equalsIgnoreCase(itemName) || GameConstants.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(itemName) || itemExists == false) {
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
     * TODO add comment here
     * @return
     */
    private Item create() {
        Item itemObj = new Item();
        String itemName = "";
        boolean itemExists = true;

        while("".equalsIgnoreCase(itemName) || GameConstants.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(itemName) || itemExists) {
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
     * TODO add comment here
     * @return
     */
    public String getItemEnum() {
        ArrayList<String> itemsArray = new ArrayList<>();

        // Get input from user
        System.out.println("Please enter your item Type from the provided list below:");
        for (ItemEnum e : ItemEnum.values()) {
            System.out.println(e.ordinal() + ". " + e.name());
            itemsArray.add(e.ordinal(), e.name());
        }
        String item = readLine();

        // Check if the input of the enum is valid
        while(!itemsArray.contains(item)) {
            System.out.println("The Entered Item is not valid! \nPlease enter your item Type from the provided list below:");
            for (ItemEnum e : ItemEnum.values()) {
                System.out.println(e.ordinal() + ". " + e.name());
            }
            item = readLine();

            for (ItemEnum e : ItemEnum.values()) {
                itemsArray.add(e.ordinal(), e.name());
            }

            // Exist while loop is a correct enum is chosen
            if(itemsArray.contains(item))
                break;
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
        ArrayList<String> EnhancementArray = new ArrayList<>();
        // Get the user to choose which enhancement type to define
        System.out.println("Please enter your Enhancement from the provided list below:");

        if (item.equals("HELMET") || item.equals("ARMOR") || item.equals("SHIELD")) {
            System.out.println(EnhancementTypes.ARMORCLASS);
            EnhancementArray.add("ARMORCLASS");
        } else if (item.equals("RING")) {
            System.out.println("" +EnhancementTypes.ARMORCLASS + "\n" + EnhancementTypes.CONSTITUTION + "\n" + EnhancementTypes.STRENGTH);
            EnhancementArray.add("ARMORCLASS");
            EnhancementArray.add("CONSTITUTION");
            EnhancementArray.add("STRENGTH");
        } else if (item.equals("BELT")) {
            System.out.println(EnhancementTypes.CONSTITUTION + "\n" + EnhancementTypes.STRENGTH);
            EnhancementArray.add("CONSTITUTION");
            EnhancementArray.add("STRENGTH");
        } else if (item.equals("BOOTS")) {
            System.out.println(EnhancementTypes.ARMORCLASS + "\n" + EnhancementTypes.DEXTERITY);
            EnhancementArray.add("ARMORCLASS");
            EnhancementArray.add("DEXTERITY");
        } else {
            System.out.println(EnhancementTypes.ATTACKBONUS + "\n" + EnhancementTypes.DAMAGEBONUS);
            EnhancementArray.add("ATTACKBONUS");
            EnhancementArray.add("DAMAGEBONUS");
        }
        String enhancement = readLine();

        while (!EnhancementArray.contains(enhancement)) {
            System.out.println("The Entered Enhancement is not valid! \nPlease enter your Enhancement from the provided list below:");
            for (int i = 0; i < EnhancementArray.size(); i++) {
                System.out.println(EnhancementArray.get(i));
            }
            enhancement = readLine();

            // Exit while loop if the input is correct.
            if(EnhancementArray.contains(enhancement)){
                break;
            }
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
