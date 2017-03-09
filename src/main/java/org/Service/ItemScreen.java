package main.java.org.Service;

import main.java.org.model.EnhancementTypes;
import main.java.org.model.Item;
import main.java.org.model.ItemEnum;

import java.util.*;

/**
 * Created by Sadra on 2/23/17.
 */
public class ItemScreen {

    public  Item askUserToCreateOrEditItem() {
        System.out.println("Please enter E for editing and C for creating the Item");
        String entered = readLine();
        while (entered.charAt(0) != 'E' && entered.charAt(0) != 'C') {
            System.out.println("Entered value was not valid \n Please enter E for editing and C for creating the Item");
            entered = readLine();
        }

        if(entered.charAt(0) == 'E'){
            return edit();
        }else if (entered.charAt(0) == 'C'){
            return create();
        }

        return null;

    }

    private  Item edit() {
        return null;
    }

    private  Item create() {
        String item = getItemEnum();
        String enhancement = getEnhancementType(item);
        int enhancementAmount = getEnhancementAmount();

        Item itemToCreate = new Item(getItemEnumfromString(item), getEnhancementEnumfromString(enhancement), enhancementAmount);

        ObjectSaver objectSave= new ObjectSaver();
        objectSave.SaveItem(itemToCreate);

        return itemToCreate;

    }

    public String getItemEnum() {
        System.out.println("Please enter your item Type from the provided list below:");
        for(ItemEnum e:ItemEnum.values())
        {
            System.out.println(e.ordinal() + ". " + e.name());
        }
        String item = readLine();
        ArrayList<String> itemsArray = new ArrayList<>();
        for (ItemEnum e: ItemEnum.values()){
            itemsArray.add(e.ordinal(), e.name());
        }
        while(!itemsArray.contains(item)) {
            System.out.println("The Entered Item is not valid! \nPlease enter your item Type from the provided list below:");
            for(ItemEnum e:ItemEnum.values())
            {
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

        if (item.equals("HELMET") || item.equals("ARMOR") || item.equals("SHIELD")){
            System.out.println(EnhancementTypes.ARMORCLASS);
        }
        else if (item.equals("RING")){
            System.out.println(EnhancementTypes.ARMORCLASS+"\n"+EnhancementTypes.CONSTITUTION+"\n"+EnhancementTypes.STRENGTH);
        }
        else if (item.equals("BELT")){
            System.out.println(EnhancementTypes.CONSTITUTION+"\n"+EnhancementTypes.STRENGTH);
        }
        else if (item.equals("BOOTS")){
            System.out.println(EnhancementTypes.ARMORCLASS+"\n"+EnhancementTypes.DEXTERITY);
        }
        else{
            System.out.println(EnhancementTypes.ATTACKBONUS+"\n"+EnhancementTypes.DAMAGEBONUS);
        }
        String enhancement = readLine();
        ArrayList<String> EnhancementArray = new ArrayList<>();
        for (EnhancementTypes e: EnhancementTypes.values()){
            EnhancementArray.add(e.ordinal(), e.name());
        }
        while(!EnhancementArray.contains(enhancement)) {
            System.out.println("The Enetered Enhancement is not valid! \nPlease enter your Enhancement from the provided list below:");
            for(EnhancementTypes e:EnhancementTypes.values())
            {
                System.out.println(e.ordinal() + ". " + e.name());
            }
            enhancement = readLine();
        }
        System.out.println("ENHANCEMENT RECEIVED SUCCESSFULLY");
        return enhancement;
    }

    public int getEnhancementAmount(){
        int enhancementAmount = 0;
        Scanner scn = new Scanner(System.in);
        try {
            System.out.println("Please give the enhancement amount (from 1-5):" + '\n');
            enhancementAmount = scn.nextInt();
            while (enhancementAmount > 5 || enhancementAmount < 1) {
                System.out.println("Invalid input! Please enter between 1 to 5." + '\n');
                enhancementAmount = scn.nextInt();
            }
        }
        catch (InputMismatchException exception){
            System.out.println("Input is not an integer.");
        }
        return enhancementAmount;
    }


    String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

     ItemEnum getItemEnumfromString(String item){
         List<ItemEnum> items =Arrays.asList(ItemEnum.values());
         for(ItemEnum i:items){
             if(i.name().equalsIgnoreCase(item)){
                 return i;
             }
         }
        return null;

    }

    EnhancementTypes getEnhancementEnumfromString (String enhancement){
         List<EnhancementTypes> bonus = Arrays.asList(EnhancementTypes.values());
         for (EnhancementTypes i:bonus){
             if (i.name().equalsIgnoreCase(enhancement)){
                 return i;
             }
         }
         return null;
    }


}
