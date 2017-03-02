package main.java.org.Service;

import main.java.org.model.EnhancementTypes;
import main.java.org.model.Item;
import main.java.org.model.ItemEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class is is to Item objects
 *
 * @author Sadra
 * @version 1.0
 * @since 2017-03-01
 */
public class ItemScreen {

    public Item askUserToCreateOrEditItem() {
        System.out.println("Please enter E for editing and C for creating the Item");
        String entered = readLine();
        while (entered.charAt(0) != 'E' && entered.charAt(0) != 'C') {
            System.out.println("Entered value was not valid \n Please enter E for editing and C for creating the Item");
            entered = readLine();
        }

        if (entered.charAt(0) == 'E') {
            return edit();
        } else if (entered.charAt(0) == 'C') {
            return create();
        }

        return null;

    }

    private Item edit() {
        Item item = new Item();
        System.out.println("Please enter the name of the Item you would like to edit:");
        new ObjectLoader().showItemNames("src/main/java/org/resources/items/");
        item = item.loadItem(readLine());
        System.out.println("Your Item had the followings:" + item.toString());
        //String itemEnum = getItemEnum();
        String enhancement = getEnhancementType(item.getItem().name());
        Item itemToCreate = new Item(getItemEnumfromString(item.getItem().name()), getEnhancementEnumfromString(enhancement));
        itemToCreate.saveItem();

        return itemToCreate;
    }

    private Item create() {
        String item = getItemEnum();
        String enhancement = getEnhancementType(item);

        Item itemToCreate = new Item(getItemEnumfromString(item), getEnhancementEnumfromString(enhancement));

        itemToCreate.saveItem();

        return itemToCreate;

    }

    public String getItemEnum() {
        System.out.println("Please enter your item Type from the provided list below:");
        for (ItemEnum e : ItemEnum.values()) {
            System.out.println(e.ordinal() + ". " + e.name());
        }
        String item = readLine();
        ArrayList<String> itemsArray = new ArrayList<>();
        for (ItemEnum e : ItemEnum.values()) {
            itemsArray.add(e.ordinal(), e.name());
        }
        while (!itemsArray.contains(item)) {
            System.out.println("The Entered Item is not valid! \nPlease enter your item Type from the provided list below:");
            for (ItemEnum e : ItemEnum.values()) {
                System.out.println(e.ordinal() + ". " + e.name());
            }
        }
        System.out.println("ITEM RECEIVED SUCCESSFULLY!");
        return item;
    }

    private String getEnhancementType(String item) {

        System.out.println("Please enter your Enhancement from the provided list below:");
//        for(EnhancementTypes e:EnhancementTypes.values())
//        {
//            System.out.println(e.ordinal() + ". " + e.name());
//        }

        if (item.equals("HELMET") || item.equals("ARMOR") || item.equals("SHIELD")) {
            System.out.println(EnhancementTypes.ARMORCLASS);
        } else if (item.equals("RING")) {
            System.out.println(EnhancementTypes.ARMORCLASS + "\n" + EnhancementTypes.CONSTITUTION + "\n" + EnhancementTypes.STRENGTH);
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


    String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    ItemEnum getItemEnumfromString(String item) {
        List<ItemEnum> items = Arrays.asList(ItemEnum.values());
        for (ItemEnum i : items) {
            if (i.name().equalsIgnoreCase(item)) {
                return i;
            }
        }
        return null;

    }

    EnhancementTypes getEnhancementEnumfromString(String enhancement) {
        List<EnhancementTypes> bonus = Arrays.asList(EnhancementTypes.values());
        for (EnhancementTypes i : bonus) {
            if (i.name().equalsIgnoreCase(enhancement)) {
                return i;
            }
        }
        return null;
    }


}
