package main.java.org.model;

import java.awt.*;

public class Item {

    ItemEnum item;
    AbilityEnum ability;
    int enhance;
    Point coordinate;

    public Item(ItemEnum item,AbilityEnum ability ,Point coordinate) {
        createItem(item,ability);
        this.coordinate = coordinate;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public ItemEnum getItem() {
        return item;
    }

    public void setItem(ItemEnum item) {
        this.item = item;
    }

    //create getter setter for enhance



    public void createItem (ItemEnum itemEnum, AbilityEnum abilityEnum){
        //check if the item is "HELMET"
        if (itemEnum == ItemEnum.HELMET){
            if(abilityEnum == AbilityEnum.ARMORCLASS){
                //Create Helmet
                this.item = itemEnum;
                this.ability = abilityEnum;
                enhance = +1;
                //save into file
            }
            else if(itemEnum == ItemEnum.ARMOMR){
                if(abilityEnum == AbilityEnum.ARMORCLASS){
                    //Create ArmorClass
                    this.item = itemEnum;
                    this.ability = abilityEnum;
                    enhance = +1;
                    //save into file
                }

            }else if (itemEnum == ItemEnum.SHIELD){
                if(abilityEnum == AbilityEnum.ARMORCLASS || abilityEnum == AbilityEnum.STRENGTH || abilityEnum == AbilityEnum.CONSTITUTION ){
                    //Create Shield
                    this.item = itemEnum;
                    this.ability = abilityEnum;
                    enhance = +1;
                    //save into file
                }
            }else if(itemEnum == ItemEnum.RING) {
                if (abilityEnum == AbilityEnum.ARMORCLASS || abilityEnum == AbilityEnum.STRENGTH || abilityEnum == AbilityEnum.CONSTITUTION) {
                    //Create Ring
                    this.item = itemEnum;
                    this.ability = abilityEnum;
                    enhance = +1;
                    //save into file
                }
            }else if(itemEnum == ItemEnum.BELT) {
                if (abilityEnum == AbilityEnum.STRENGTH || abilityEnum == AbilityEnum.CONSTITUTION) {
                    //Create Belt
                    this.item = itemEnum;
                    this.ability = abilityEnum;
                    enhance = +1;
                    //save into file
                }
            }else if(itemEnum == ItemEnum.BOOTS) {
                if (abilityEnum == AbilityEnum.ARMORCLASS || abilityEnum == AbilityEnum.DEXTERITY) {
                    //Create Boots
                    this.item = itemEnum;
                    this.ability = abilityEnum;
                    enhance = +1;
                    //save into file
                }
            }else if (itemEnum == ItemEnum.WEAPON) {
                if (abilityEnum == AbilityEnum.ATTACKBONUS || abilityEnum == AbilityEnum.DAMAGEBONUS) {
                    //Crete weapon
                    this.item = itemEnum;
                    this.ability = abilityEnum;
                    enhance = +1;
                    //save into file
                }
            }else {
                System.out.println("Invalid item");
            }
        }
    }

    public void editItem(boolean remove,AbilityEnum abilityEnum){
        if(remove){
            // load and save functions
        }else {
            createItem(this.item,abilityEnum);
        }
    }

    public void enhancement(int enhance){
        // character should call this when enters the map to enhance the item regarding to his level
    }

    @Override
    public boolean equals(Object object){

        if(object instanceof  Item){
            Item ob = (Item) object;

            return  ob.item == this.item && ob.ability == this.ability && ob.coordinate == this.coordinate && ob.enhance == this.enhance;
        }
        return false;
    }
}
