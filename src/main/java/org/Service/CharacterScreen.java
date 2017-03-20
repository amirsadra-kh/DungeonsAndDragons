package main.java.org.Service;
import main.java.org.model.*;
import main.java.org.model.Character;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.IOException;

/**
 * This class is to interact with user to create the character, save it and edit it.
 *
 * @author Parisa Nikzad/Freyja Jokulsdottir
 * @version 1.0
 * @since 2017-02-23
 */
public class CharacterScreen {
    private ReadInput readInput = new ReadInput();

    /**
     * The interactino screen with the user to create or edit a character
     *
     * @throws Exception
     */
    public void CharacterScreen() throws Exception {
        int choice = 0;


        // Let user choose an action - Create or Edit a Character
        System.out.println("Choose one of the following by entering the number associated with the choice:");
        System.out.println("1. Create a Character\n2. Edit a Character\n3. Back to Main Menu");
        while(choice == 0)
            choice = readInput.readIntHandling(choice);

        // If the user enters an invalid input, they will be asked again
        while (choice < 1 || choice > 3) {
            System.out.println("Your input is invalid, please try again");
            choice = readInput.readIntHandling(choice);
        }

        switch (choice) {
            case 1:
                createCharacterScreen();
                break;
            case 2:
                editCharacterScreen();
                break;
            case 3:
                backToMain();
                break;
            }
    }

    /**
     * The interaction screen with user to create a new character
     */
    public void createCharacterScreen() throws Exception {
        ObjectLoader ol = new ObjectLoader();
        String charName = "";
        HashSet<Item> wearingItem = new HashSet<>();

        Character character = new Character();
        character.newCharacter();
        Ability ability = new Ability();
        character.setAbility(ability);
        boolean wearing = false;
        boolean charExist = true;

        // Check if a character with the name chosen already exists and prompt for a new name if it does
        while("".equalsIgnoreCase(charName) || GameConstantsInterface.CHOSEN_ITEM_NOT_VALID.equalsIgnoreCase(charName) || charExist) {
            System.out.println("Enter the name of the new Character (No spaces): ");
            charName = readInput.readStringHandling(charName);

            // Check if a campaign with the name chose already exists
            if(character.loadCharacter(charName) == null) {
                charExist = false;
            }
            else {
                System.out.println("A Character with this name already exists!");
            }
        }

        character.setCharName(charName);

        System.out.println("The hit points of the character before items have been added: " +character.getHitPoints());

        // Set the backpack for this character
        BackPackInventory backpack = new BackPackInventory();
        // Ask user what they want to have in the backpack and use that as an input to the method below.
        List<Item> backpackItems = new ArrayList<>();
        System.out.println("Would you like to choose the backpack items for the character? Y/N");
        while (true) {
            String answer = readInput.readLine().trim().toLowerCase();
            if (answer.equals("y")) {
                System.out.println("The backpack inventory choices: ");
                userChooseItems(backpackItems, ability, wearing, character);
                backpack.setItems(backpackItems);
                character.setBackPackInventory(backpack);
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
        }

        // Get the user to choose items for the character to wear
        System.out.println("Would you like to choose the wearing items for the character? Y/N");
        while (true) {
            String answer = readInput.readLine().trim().toLowerCase();
            if (answer.equals("y")) {
                wearing = true;
                List<Item> listWearingItems = new ArrayList<>(wearingItem);
                System.out.println("The character's item wearing choices: ");
                userChooseItems(listWearingItems, ability, wearing, character);
                wearingItem = new HashSet<>(listWearingItems);
                break;
            } else if (answer.equals("n")) {
                break;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
        }

        character.setItemsWearing(wearingItem);

        System.out.println("The character wear " + wearingItem);

        System.out.println(ability.toString());
        System.out.println(character.charString());

        // Save the character
        character.saveCharacter();
    }

    /**
     * The interaction screen with user to edit an existing character.
     *
     * @throws Exception
     */
    public void editCharacterScreen() throws Exception {
        String charName = "";
        int choice = 0;
        Character character = new Character();

        System.out.println("Please enter the name of the character you would like to edit");
        charName = readInput.readStringHandling(charName);

        while(character.loadCharacter(charName) == null) {
            System.out.println("No such character exists, try again: ");
            charName = readInput.readStringHandling(charName);
        }

        // Load the character for editing
        character = character.loadCharacter(charName);

        // What does the user want to edit about the character?
        System.out.println("Which of these would you like to edit?");
        System.out.println("1. Ability\n2. Wearing Item\n3. BackPack\n4. Exit");
        choice = readInput.readIntHandling(choice);


        switch (choice) {
            case 1:
                editAbility(character, charName);
                System.out.println();
                CharacterScreen();
                break;
            case 2:
                editWearingItem(character);
                System.out.println();
                CharacterScreen();
                break;
            case 3:
                editBackPack(character);
                System.out.println();
                CharacterScreen();
                break;
            case 4:
                backToMain();
        }
    }

    /**
     * A method to interact with the user for editing the ability of a character
     *
     * @param character the existing character to be edited
     * @return the edited character object
     */
    private void editAbility(Character character, String charName) {
        int level = 0;
        int choice = 0;
        Ability ability = character.getAbility();

        // Edit the level of the character
        System.out.println("Please enter the level you would like the character to be at (0 if not changed): ");
        level = readInput.readIntHandling(level);
        if(level != 0)
            character.setLevel(level);

        // Change the strength of the character
        System.out.println("Please enter the strength you would like the character to have (0 if not changed): ");
        choice = readInput.readIntHandling(choice);
        if(choice != 0)
            ability.setStrength(choice);

        // Change the constitution of the character
        choice = 0;
        System.out.println("Please enter the constitution you would like the character to have (0 if not changed): ");
        choice = readInput.readIntHandling(choice);
        if(choice != 0)
            ability.setConstitution(choice);

        // Change the dexterity of the character
        choice = 0;
        System.out.println("Please enter the dexterity you would like the character to have (0 if not changed): ");
        choice = readInput.readIntHandling(choice);
        if(choice != 0)
            ability.setDexterity(choice);

        // Set the new ability for the character
        character.setAbility(ability);

        System.out.println("The other ability points depend on what the character has on item!\n");

        // Save changes?
        chooseToSave(charName, character);
    }

    /**
     * A method to interact with the user for editing the items that character is wearing
     *
     * @param character the existing character to be edited
     * @return the edited character object
     */

    private void editWearingItem(Character character) {
        boolean wearing = true;
        HashSet<Item> wearingItem = character.getItemsWearing();
        Ability ability = character.getAbility();

        // Get the user to choose items for the character to wear
        List<Item> listWearingItems = new ArrayList<>(wearingItem);
        addToItems(listWearingItems, ability, character, wearing);
        wearingItem = character.getItemsWearing();

        // Get the user to choose items for the character to wear
        listWearingItems = new ArrayList<>(wearingItem);
        removeFromItems(listWearingItems, character, wearing);;

        // Save changes?
        chooseToSave(character.getCharName(), character);
    }

    /**
     * A method for editing the items in the character's backpack.
     * @param character
     * @return character with a changed backpack
     */
    private void editBackPack(Character character) {
        boolean wearing = false;
        List<Item> backpackItems = character.getBackPackInventoryItems();
        // TODO fix backpack item loading
        for(Item item : backpackItems)
            System.out.println(item.getName());
        Ability ability = character.getAbility();

        // Get the user to choose items for the character's backpack
        addToItems(backpackItems, ability, character, wearing);

        // Get the user to choose items to remove from the backpack
        removeFromItems(backpackItems, character, wearing);

        // Save changes?
        chooseToSave(character.getCharName(), character);
    }

    /**
     * A method that asks the user if they want to save their changes.
     *
     * @param charName the name of the character to be saved
     * @param character the character object to be saved
     */
    private void chooseToSave(String charName, Character character) {
        // Save changes?
        String save = "";
        System.out.println("Would you like to save your changes?Y/N");
        save = readInput.readStringHandling(save);
        while(!save.equals("Y") && !save.equals("N") && !save.equals("y") && !save.equals("n")) {
            System.out.println("Invalid input! Please try again: ");
            save = readInput.readStringHandling(save);
        }
        if(save.equals("Y") || save.equals("y"))
            character.saveCharacter();
    }

    /**
     * A method to go back to the main menu
     */
    private void backToMain(){
        new GameGenerator();
    }

    /**
     * A method to interact with user to add to character's wearing or backpack items
     * @param items the list of items the character is wearing or has in the backpack
     * @param ability the ability of the character
     * @param character the character
     * @param wearing a boolean to indicate if it is a wearingItem list or a backpackItem list
     * @return the new list of wearing items or the backpack items.
     */
    private void addToItems(List<Item> items, Ability ability, Character character, boolean wearing) {
        String wearingOrBackpack = "";
        if(wearing == true)
            wearingOrBackpack = "wearing";
        else
            wearingOrBackpack = "backpack";

        // Get the user to choose items for the character to wear
        System.out.println("Would you like to add to the " +wearingOrBackpack +" items for the character? Y/N");
        while (true) {
            String answer = readInput.readLine().trim().toLowerCase();
            if (answer.equals("y")) {
                System.out.println("The character's item " +wearingOrBackpack +" choices: ");
                userChooseItems(items, ability, wearing, character);
                if(wearing) {
                    HashSet<Item> wearingItem = new HashSet<>(items);
                    // Set the new wearing items after adding items
                    character.setItemsWearing(wearingItem);
                }
                else if(!wearing) {
                    // Set the new backpack of the character after adding items
                    BackPackInventory backpack = new BackPackInventory();
                    backpack.setItems(items);
                    character.setBackPackInventory(backpack);
                }
                return;
            } else if (answer.equals("n")) {
                if(wearing) {
                    HashSet<Item> wearingItem = new HashSet<>(items);
                    // Set the new wearing items after adding items
                    character.setItemsWearing(wearingItem);
                }
                else if(!wearing) {
                    // Set the new backpack of the character after adding items
                    BackPackInventory backpack = new BackPackInventory();
                    backpack.setItems(items);
                    character.setBackPackInventory(backpack);
                }
                return;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
        }
    }

    /**
     * A method to interact with the user to remove items from wearingItem list or backpack list
     * @param items the character's list of items wearing
     * @param wearing a boolean to indicate if it is a wearingItem list or a backpackItem list
     * @return a new wearingItem or backpackItem list with items removed
     */
    private void removeFromItems(List<Item> items, Character character, boolean wearing) {
        String wearingOrBackpack = "";
        if(wearing == true)
            wearingOrBackpack = "wearing";
        else
            wearingOrBackpack = "backpack";

        List<String> itemNames = new ArrayList<>();

        // Get the user to choose items for the character to wear
        System.out.println("Would you like to remove from the " +wearingOrBackpack +" items for the character? Y/N");
        while (true) {
            String answer = readInput.readLine().trim().toLowerCase();
            if (answer.equals("y")) {
                System.out.println("Choose an item from the list below of the " +wearingOrBackpack +" items: ");
                for(Item item : items)
                    System.out.println(item.getName());
                if(items.size() == 0) {
                    System.out.println("No items to remove!");
                    return;
                }
                String itemName = readInput.readLine();
                Item item = new Item();
                item = item.loadItem(itemName);
                items.remove(item);
                if(wearing) {
                    HashSet<Item> wearingItem = new HashSet<>(items);
                    // Set the new wearing items after removing items
                    character.setItemsWearing(wearingItem);
                }
                else if(!wearing) {
                    BackPackInventory backpack = new BackPackInventory();
                    // Set the new backpack of a character after removing items.
                    backpack.setItems(items);
                    character.setBackPackInventory(backpack);
                }
                return;
            } else if (answer.equals("n")) {
                if(wearing) {
                    HashSet<Item> wearingItem = new HashSet<>(items);
                    // Set the new wearing items after removing items
                    character.setItemsWearing(wearingItem);
                }
                else if(!wearing) {
                    BackPackInventory backpack = new BackPackInventory();
                    // Set the new backpack of a character after removing items.
                    backpack.setItems(items);
                    character.setBackPackInventory(backpack);
                }
                return;
            } else {
                System.out.println("Sorry, I didn't catch that. Please answer y/n");
            }
        }
    }

    /**
     * A method to get the user to choose items
     * Called when the user choose items to wear and when the user chooses items to have in the backpack.
     *
     * @param items a list of items o be added to - wearing or backpack
     * @param ability to be modified according to wearing items.
     */
    private void userChooseItems(List<Item> items, Ability ability, boolean wearing, Character character) {
        String answer;
        boolean yn = true;
        ArrayList<ItemEnum> keys = new ArrayList<ItemEnum>();
        ItemEnum key;
        int size = 0;
        
        if(wearing)
            size = 8;
        else
            size = 10;

        for (int i = 1; i <= size && yn ; i++) {
            System.out.println("Please enter the name of the item no." +i
                    +"  that you want the character to have from the list below:");
            Item item = new Item();
            new ObjectLoader().showItemNames("src/main/java/org/resources/items/");
            item = item.loadItem(readInput.readLine().toUpperCase());
            key = item.getItem();

            if(item == null){
                System.out.println("This item does not exist");
                i = i-1;
            }
            else if( keys.contains(key) && wearing){
                System.out.println("You cannot wear the same type of item");
                i = i-1;
            }else {
                if(yn && wearing) {
                    switch (item.getEnhancementType()) {
                        case STRENGTH:
                            ability.setStrength(ability.getStrength() + item.getEnhance());
                            ability.setDamageBonus(item.getEnhance() - 1);
                            character.setHitPoints();
                            break;
                        case CONSTITUTION:
                            ability.setConstitution(ability.getConstitution() + item.getEnhance());
                            break;
                        case DEXTERITY:
                            ability.setDexterity(ability.getConstitution() + item.getEnhance());
                            break;
                        case ARMORCLASS:
                            ability.getArmorClass(ability.getArmorClass() + item.getEnhance() - 10);
                            break;
                        case ATTACKBONUS:
                            ability.setAttackBonus(ability.getAttackBonus() + item.getEnhance());
                            break;
                        case DAMAGEBONUS:
                            ability.setDamageBonus(item.getEnhance());
                            break;
                        case HITPOINTS:
                            break;
                        case LEVEL:
                            break;
                    }
                }

                items.add(item);
                keys.add(item.getItem());

                System.out.println("do you  want to add the another item ? Y/N");

                while (true) {
                    answer = readInput.readLine().trim().toLowerCase();
                    if (answer.equals("y")) {
                        yn = true;
                        break;
                    } else if (answer.equals("n")) {
                        yn = false;
                        break;
                    } else {
                        System.out.println("Sorry, I didn't catch that. Please answer y/n");
                    }
                }
            }
        }
    }
}