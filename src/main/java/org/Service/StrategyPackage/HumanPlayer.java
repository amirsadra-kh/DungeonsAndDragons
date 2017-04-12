package main.java.org.Service.StrategyPackage;

import main.java.org.Service.AdjacentObjectsFinder;
import main.java.org.Service.Calculation;
import main.java.org.Service.MapDirectionValidator;
import main.java.org.model.*;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.Map;

import java.awt.*;
import java.awt.List;
import java.util.*;
import java.util.ArrayList;

/**
 * This strategy is for a player character controlled by the user. It requires user
 * interaction for determining where the player moves, what NPC it attacks, and
 * what other interactions it will do during a turn.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 09.04.2017
 */
public class HumanPlayer implements BehaviourStrategy {
    ReadInput readInput = new ReadInput();
    /**
     * A method for moving a player character - by choice
     * @param character the one who has a turn now - the player character
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     * @param map the map the character is on
     */
    @Override
    public Point move(Character character, Character player, Point objective, Map map, Campaign campaign) {
        if(character.getBurning()) {
            // TODO decrease monster's hitpoints here based on getBurningDamage in burning decorator
        }

        // Ask user 3 times where they want to move using R, L, U and D
        for(int i = 0; i < 3; i ++) {
            //This prints and ask user to enter the directions
            System.out.println(GameConstantsInterface.ENTER_DIRECTION);
            String direction = readInput.readCoordinate();
            if(!checkIfMoveIsValid(direction, map, character, campaign)) {
                i--;
                System.out.println("Invalid Move!");
            } else {
                character.setCurrentPosition(setNewPosition(direction, character.getCurrentPosition()));
            }
            if(i < 2) {
                System.out.println("Do you want to make another move? (Y/N)");
                boolean answer = readInput.askUserIfAgain();
                if (!answer)
                    i = 3;
            }
        }

        AdjacentObjectsFinder finder = new AdjacentObjectsFinder();
        if(finder.checkForChest(character.getCurrentPosition(), map)) {
            System.out.println(ColorConstants.ANSI_CYAN +"Player found a chest!!" +ColorConstants.ANSI_RESET);
            // The objective of the map has been met
            map.setCanGoNextLevel(true);
            System.out.println("Backpack inventory before chest: " +character.getBackPackInventoryItems().toString());
            interact(character, map.getChest(), map);
            System.out.println("Backpack inventory after chest: " +character.getBackPackInventoryItems().toString());
        }

        Character friendly = finder.checkForFriendly(character.getCurrentPosition(), map);
        if(friendly != null)
            askUserAttackOrInteract(character, friendly, map);

        Character monster = finder.checkForMonster(character.getCurrentPosition(), map);
        if(monster != null) {
            System.out.println(ColorConstants.ANSI_RED +"You just found an aggressive character!" +ColorConstants.ANSI_RESET);
            attack(character, monster);
        }

        // return the new coordinate
        return character.getCurrentPosition();
    }

    /**
     * A method to ask the user if they want to interact or attack the friendly character.
     * @param player
     * @param friendly
     * @param map
     */
    private void askUserAttackOrInteract(Character player, Character friendly, Map map) {
        System.out.println(ColorConstants.ANSI_CYAN +"You just found a friendly character!" +ColorConstants.ANSI_RESET);
        System.out.println("Would you like to\n1.Interact\n2.Attack");
        int choice = readInput.readIntHandling(0);
        while (choice < 1 || choice > 2) {
            System.out.println("Your input is invalid, please try again");
            choice = readInput.readIntHandling(choice);
        }
        if (choice == 1) {
            interactWithFriendly(player, friendly, map);
        } else {
            // When a friendly character is attacked, they become aggressive
            friendly.setBehaviourStrategy(new AggressiveNPC());
            attack(player, friendly);
        }
    }

    /**
     * A method for the humanPlayer to interact with a friendly character
     * @param player the humanPlayer
     * @param friendly the friendly character
     * @param map teh map currently being played
     */
    private void interactWithFriendly(Character player, Character friendly, Map map) {
        // Set the backpacks of both characters
        BackPackInventory friendlyCharacterBackpack = friendly.getBackPackInventory();
        BackPackInventory playerBackPack = player.getBackPackInventory();

        // Get the items from the backpacks of both characters
        java.util.List<Item> playerItems = playerBackPack.getItems();
        java.util.List<Item> friendlyCharacterItems = friendlyCharacterBackpack.getItems();

        // Get the item from user that the humanPlayer wants to give away
        System.out.println("Choose an item to exchange with an item from friendly character: \n"+playerItems.toString());
        System.out.println("Choose the number of the item, the first is number 1 and so on");
        int itemToGive = Integer.parseInt(readInput.readLine());

        // Store and remove the item from the humanPlayer's backpack
        Item temp1 = playerItems.get(itemToGive);
        playerItems.remove(itemToGive);

        // Get a random item from the friendly character and remove it from the friendly character's backpack
        int index = new Random().nextInt(friendlyCharacterItems.size());
        Item itemToReceive = friendlyCharacterItems.get(index);
        friendlyCharacterItems.remove(index);

        // Add the item from  friendly to humanPlayer's backpack
        playerItems.add(itemToReceive);
        playerBackPack.setItems(playerItems);

        // Add the item from  humanPlayer to friendly's backpack;
        friendlyCharacterItems.add(temp1);
        friendlyCharacterBackpack.setItems(friendlyCharacterItems);

        // Set the new backpack inventory of both characters
        player.setBackPackInventory(playerBackPack);
        friendly.setBackPackInventory(friendlyCharacterBackpack);

        // Log for user
        System.out.println("Friendly character's backpack after interaction: " +friendly.getBackPackInventoryItems());
        System.out.println("Human player's backpack after interaction: " +player.getBackPackInventoryItems());

        // Save the characters with their new inventory
        player.saveCharacter();
        friendly.saveCharacter();
    }

    /**
     * A method to check if the move is valid
     * @param direction
     * @param map
     * @return
     */
    private boolean checkIfMoveIsValid(String direction, Map map, Character humanPlayer, Campaign campaign) {
        MapDirectionValidator validate = new MapDirectionValidator(campaign, map);
        return validate.directionIsValidToMove(direction, map, humanPlayer.getCurrentPosition());
    }

    /**
     * A method to get the new position after a valid move
     * @param direction headed direction
     * @param current position point
     * @return
     */
    private Point setNewPosition(String direction, Point current) {
        MapDirectionValidator validate = new MapDirectionValidator();
        return validate.getNextCellToMove(direction, current);
    }

    /**
     * A method for the player to attack another character
     * @param player
     * @param attackedChar
     */
    @Override
    public void attack(Character player, Character attackedChar) {
        Calculation roll = new Calculation();
        int d20 = roll.getDice20();
        int attackBonus = player.getAbility().getAttackBonus();
        int attackRoll = d20 + attackBonus;
        attackLog(d20, attackBonus, attackedChar.getAbility().getArmorClass());
        if(attackRoll > attackedChar.getAbility().getArmorClass()) {
            System.out.println(ColorConstants.ANSI_GREEN +"HIT!!" +ColorConstants.ANSI_RESET);
            int d8 = roll.getDice8();
            int strengthMod = player.getAbility().getStrengthModifier();
            int damageRoll = d8 + strengthMod;
            int monstersHP = attackedChar.getHitPoints();
            damageLog(d8, strengthMod, monstersHP);
            attackedChar.decreaseHitPoint(damageRoll);
        } else {
            System.out.println(ColorConstants.ANSI_RED +"MISS!!" +ColorConstants.ANSI_RESET);
        }
        System.out.println("------------------------------------------------------------");
    }

    /**
     * A method for the player to interact with a chest, a friendly characters backpack or a dead monster's backpack
     * @param player
     * @param chest
     */
    @Override
    public void interact(Character player, BackPackInventory chest, Map map) {
        ArrayList<Item> loot = new ArrayList<>();
        if (chest != null) {
            loot.addAll(chest.getItems());
        }
        java.util.List<Item> playerBackpack = player.getBackPackInventoryItems();
        if (playerBackpack == null) {
            player.setBackPackInventory(new BackPackInventory());
        }
        while (loot.size() > 0) {
            if (player.getBackPackInventoryItems().size() < 10) {
                player.getBackPackInventoryItems().add(loot.remove(loot.size() - 1));
            } else {
                break;
            }
        }
        map.getChest().setItems(loot);
    }

    /**
     * A method for showing the attempt for an attack log
     * @param d20 the d20 roll
     * @param attackBonus the character's attack bonus
     * @param monsterAC the monster's armor class
     */
    private void attackLog(int d20, int attackBonus, int monsterAC) {
        int attackRoll = d20 + attackBonus;
        System.out.println("------------------------Log Window-------------------------");
        System.out.println(ColorConstants.ANSI_RED +"ATTACK!" +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"D20 roll: " +d20 +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"AttackBonus: " +attackBonus +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_GREEN +"Total: " +attackRoll +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"Monster's ArmorClass: " +monsterAC +ColorConstants.ANSI_RESET);
    }

    /**
     * A method for showing the attack log after it has been attempted successfully
     * @param d8 the dice for longsword and longbow
     * @param strengthMod the strength modifier of the character
     * @param monsterHP the hit points of the monster
     */
    private void damageLog(int d8, int strengthMod, int monsterHP) {
        int damageRoll = d8 + strengthMod;
        System.out.println(ColorConstants.ANSI_RED +"DAMAGE!" +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"D8 roll: " +d8 +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"StrengthModifier: " +strengthMod +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_GREEN +"Total: " +damageRoll +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"Monster's Hit Points Before Attack: " +monsterHP +ColorConstants.ANSI_RESET);
        monsterHP = monsterHP - damageRoll;
        if(monsterHP < 0)
            monsterHP = 0;
        System.out.println(ColorConstants.ANSI_RED +"Monster's Hit Points After Attack: " +monsterHP +ColorConstants.ANSI_RESET);
    }
}