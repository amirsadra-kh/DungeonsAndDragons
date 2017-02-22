package main.java.org.model;

import java.awt.*;

public class Item {

    ItemEnum item;
    EnhancementTypes enhancementType;
    int enhance;
    Point coordinate;

    public Item(ItemEnum item, EnhancementTypes enhancementType, Point coordinate) {
        createItem(item, enhancementType);
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



    public void createItem (ItemEnum itemEnum, EnhancementTypes enhancementType){
        //check if the item is "HELMET"
        if (itemEnum == ItemEnum.HELMET){
            if(enhancementType == EnhancementTypes.ARMORCLASS){
                //Create Helmet
                this.item = itemEnum;
                this.enhancementType = enhancementType;
                enhance = +1;
                //save into file
            }
            else if(itemEnum == ItemEnum.ARMOMR){
                if(enhancementType == EnhancementTypes.ARMORCLASS){
                    //Create ArmorClass
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }

            }else if (itemEnum == ItemEnum.SHIELD){
                if(enhancementType == EnhancementTypes.ARMORCLASS || enhancementType == EnhancementTypes.STRENGTH || enhancementType == EnhancementTypes.CONSTITUTION ){
                    //Create Shield
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            }else if(itemEnum == ItemEnum.RING) {
                if (enhancementType == EnhancementTypes.ARMORCLASS || enhancementType == EnhancementTypes.STRENGTH || enhancementType == EnhancementTypes.CONSTITUTION) {
                    //Create Ring
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            }else if(itemEnum == ItemEnum.BELT) {
                if (enhancementType == EnhancementTypes.STRENGTH || enhancementType == EnhancementTypes.CONSTITUTION) {
                    //Create Belt
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            }else if(itemEnum == ItemEnum.BOOTS) {
                if (enhancementType == EnhancementTypes.ARMORCLASS || enhancementType == EnhancementTypes.DEXTERITY) {
                    //Create Boots
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            }else if (itemEnum == ItemEnum.WEAPON) {
                if (enhancementType == EnhancementTypes.ATTACKBONUS || enhancementType == EnhancementTypes.DAMAGEBONUS) {
                    //Crete weapon
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            }else {
                System.out.println("Invalid item");
            }
        }
    }

    public void editItem(boolean remove,EnhancementTypes abilityEnum){
        if(remove){
            // load and save functions
        }else {
            createItem(this.item,abilityEnum);
        }
    }

    public void enhancement(int enhance){
        // character should call this when enters the map to enhance the item regarding to his level
    }

    /**
     * Overriding equals function for testing.
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object){

        if(object instanceof  Item){
            Item ob = (Item) object;

            return  ob.item == this.item && ob.enhancementType == this.enhancementType && ob.coordinate == this.coordinate && ob.enhance == this.enhance;
        }
        return false;
    }
}
