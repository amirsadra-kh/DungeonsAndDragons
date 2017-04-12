package main.java.org.Service.StrategyPackage;

import main.java.org.Service.AdjacentObjectsFinder;
import main.java.org.Service.Calculation;
import main.java.org.model.*;
import main.java.org.model.CharacterPackage.BackPackInventory;
import main.java.org.model.CharacterPackage.Character;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This strategy is for enemy NPCs. An aggressive NPC will always run toward the
 * player character and attack it. If it comes near a chest or other NPC while doing
 * so, it will loot the chest and attack the NPC.
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 09.04.2017
 */
public class AggressiveNPC implements BehaviourStrategy {

    /**
     * A method for moving a hostile character - move towards player
     * @param monster the one who has a turn now
     * @param player the player character of the map
     * @param objective the position of the objective of the map - chest or exit
     * @param map the map the character is on
     */
    @Override
    public Point move(Character monster, Character player, Point objective, Map map, Campaign campaign) {
        if(monster.getBurning()) {
            // TODO decrease monster's hitpoints here based on getBurningDamage in burning decorator
        }
        Point playerPoint = player.getCurrentPosition();
        Point monsterPoint = monster.getCurrentPosition();

        // Add all validated points for target to move to a list of points
        FrighteningStrategy frighten = new FrighteningStrategy();
        ArrayList<Point> possiblePoints = frighten.addValidatedPoints(map, monsterPoint);


        // Find a point furthest away from attacker
        Point min = getMinDistance(possiblePoints, playerPoint);
        monster.setCurrentPosition(min);

        // Check if there is a chest to loot
        AdjacentObjectsFinder finder = new AdjacentObjectsFinder();
        if(finder.checkForChest(monster.getCurrentPosition(), map) && monster.getBackPackInventoryItems() != null) {
            System.out.println("Monster found a chest!!");
            System.out.println("Backpack inventory before chest: " +monster.getBackPackInventoryItems().toString());
            interact(monster, map.getChest(), map);
            System.out.println("Backpack inventory after chest: " +monster.getBackPackInventoryItems().toString());
        }

        Character friendly = finder.checkForFriendly(monster.getCurrentPosition(), map);
        if(friendly != null)
            attack(monster, friendly);

        Character playerChar = finder.checkForPlayer(monster.getCurrentPosition(), map);
        if(playerChar != null)
            attack(monster, playerChar);

        // Set the new position
        return min;
    }

    /**
     * A method to get the point closest to the player out of the valid points
     * @param possiblePoints points the monster can move
     * @param playerPoint the position of the player
     * @return a point closest to the player
     */
    private Point getMinDistance(ArrayList<Point> possiblePoints, Point playerPoint) {
        Point min = possiblePoints.get(0);
        for(Point p : possiblePoints) {
            if((Math.abs(p.x - playerPoint.x)) + Math.abs(p.y - playerPoint.y) <
                    (Math.abs(min.x - playerPoint.x)) + Math.abs(min.y - playerPoint.y))
                min = p;
        }
        return min;
    }

    /**
     * The aggressive character will attack when possible
     * @param mon
     * @param attackedChar
     */
    @Override
    public void attack(Character mon, Character attackedChar) {
        Calculation roll = new Calculation();
        int d20 = roll.getDice20();
        int attackBonus = mon.getAbility().getAttackBonus();
        int attackRoll = d20 + attackBonus;
        attackLog(d20, attackBonus, attackedChar.getAbility().getArmorClass());
        if(attackRoll > attackedChar.getAbility().getArmorClass()) {
            System.out.println(ColorConstants.ANSI_GREEN +"HIT!!" +ColorConstants.ANSI_RESET);
            int d8 = roll.getDice8();
            int strengthMod = mon.getAbility().getStrengthModifier();
            int damageRoll = d8 + strengthMod;
            int attackedCharHP = attackedChar.getHitPoints();
            damageLog(d8, strengthMod, attackedCharHP);
            attackedChar.decreaseHitPoint(damageRoll);
        } else {
            System.out.println(ColorConstants.ANSI_RED +"MISS!!" +ColorConstants.ANSI_RESET);
        }
        System.out.println("------------------------------------------------------------");
    }

    /**
     * A method for showing the attempt for an attack log
     * @param d20 the d20 roll
     * @param attackBonus the monster's attack bonus
     * @param characterAC the attacked character's armor class
     */
    private void attackLog(int d20, int attackBonus, int characterAC) {
        int attackRoll = d20 + attackBonus;
        System.out.println("------------------------Log Window-------------------------");
        System.out.println(ColorConstants.ANSI_RED +"ATTACK!" +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"D20 roll: " +d20 +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"AttackBonus: " +attackBonus +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_GREEN +"Total: " +attackRoll +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"Character's ArmorClass: " +characterAC +ColorConstants.ANSI_RESET);
    }

    /**
     * A method for showing the attack log after it has been attempted successfully
     * @param d8 the dice for longsword and longbow
     * @param strengthMod the strength modifier of the monster
     * @param characterHP the hit points of the attacked character
     */
    private void damageLog(int d8, int strengthMod, int characterHP) {
        int damageRoll = d8 + strengthMod;
        System.out.println(ColorConstants.ANSI_RED +"DAMAGE!" +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"D8 roll: " +d8 +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"StrengthModifier: " +strengthMod +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_GREEN +"Total: " +damageRoll +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"Character's Hit Points Before Attack: " +characterHP +ColorConstants.ANSI_RESET);
        characterHP = characterHP - damageRoll;
        if(characterHP < 0)
            characterHP = 0;
        System.out.println(ColorConstants.ANSI_RED +"Character's Hit Points After Attack: " +characterHP +ColorConstants.ANSI_RESET);
    }

    /**
     * The aggressive character can loot a chest
     * @param mon
     * @param chest
     */
    @Override
    public void interact(Character mon, BackPackInventory chest, Map map) {
        ArrayList<Item> loot = new ArrayList<>();
        if (chest != null) {
            loot.addAll(chest.getItems());
        }
        java.util.List<Item> playerBackpack = mon.getBackPackInventoryItems();
        if (playerBackpack == null) {
            mon.setBackPackInventory(new BackPackInventory());
        }
        while (loot.size() > 0) {
            if(mon.getBackPackInventoryItems().size() < 10) {
               mon.getBackPackInventoryItems().add(loot.remove(loot.size() - 1));
            } else {
                break;
            }
        }
        map.getChest().setItems(loot);
    }
}