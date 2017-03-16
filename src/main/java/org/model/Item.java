package main.java.org.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

/**
 * A Item object class for creating and editing an Item
 *
 * @author Sadra
 * @version 2.0
 * @since 2017-03-01
 */
@XmlRootElement
public class Item {
    private ItemEnum item;
    private EnhancementTypesEnum enhancementType;
    private int enhance;
    private Point coordinate;
    private String name;

    /**
     * An empty Item constructor for the save and load methods.
     */
    public Item(){}

    /**
     * An Item constructor for item in maps.
     *
     * @param name of the item
     * @param item ItemEnum of the item
     * @param enhancementType of the item
     * @param coordinate a point on the map the item is at
     * @param enhance an integer representing the enhancement amount of the item
     */
    public Item(String name, ItemEnum item, EnhancementTypesEnum enhancementType, Point coordinate, int enhance) {
        createItem(name, item, enhancementType, enhance);
        this.coordinate = coordinate;
        this.item = item;
        this.name = name;
    }

    /**
     * An item constructor for creating or editing an item
     * @param name of the item
     * @param item ItemEnum of the item
     * @param enhancementType of the item
     * @param enhance an integer representing the enhancement amount of the item
     */
    public Item(String name, ItemEnum item, EnhancementTypesEnum enhancementType, int enhance) {
        createItem(name, item, enhancementType, enhance);
        this.item = item;
        this.name = name;

    }

    /**
     * A method to get the coordinate of the item on a map.
     * @return a point on the map
     */
    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * A method to set the coordinate on a map of an item
     * @param coordinate to be set
     */
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * a method to get the itemEnum of this item
     * @return an Item Enum
     */
    public ItemEnum getItem() {
        return this.item;
    }

    /**
     * A method to set the itemEnum of this item
     * @param item the itemEnum to set this items itemEnum to
     */
    public void setItem(ItemEnum item) {
        this.item = item;
    }

    /**
     * A method to get the EnhancementType of this item
     * @return
     */
    public EnhancementTypesEnum getEnhancementType() {
        return this.enhancementType;
    }

    /**
     * A method to set the EnhancementType of this item
     * @param enhancementType
     */
    public void setEnhancementType(EnhancementTypesEnum enhancementType) {
        this.enhancementType = enhancementType;
    }

    /**
     * A method to get the enhance amount for this item
     * @return the enhance amount as an integer
     */
    public int getEnhance() {
        return this.enhance;
    }

    /**
     * A method to set the enhance amount for this item
     * @param enhance the enhance amount to set the enhance of this item to
     */
    public void setEnhance(int enhance) {
        this.enhance = enhance;
    }

    /**
     * A method for creating an item
     * @param name of the item to be created
     * @param itemEnum itemEnum of the item to be created
     * @param enhancementType EnhancementType of the item to be created
     * @param enhance an integer for the enhancement amount of the item to be created
     */
    public void createItem(String name, ItemEnum itemEnum, EnhancementTypesEnum enhancementType, int enhance) {
        // Set the values of the item
        for (ItemEnum e : ItemEnum.values()) {
            if (e == itemEnum) {
                this.item = itemEnum;
                this.enhancementType = enhancementType;
                this.enhance = enhance;
                this.name = name;
            }
        }

        //check the itemEnum of the item
        if (itemEnum == ItemEnum.HELMET) {
            if (enhancementType == EnhancementTypesEnum.ARMORCLASS) {
                //Create Helmet
                this.item = itemEnum;
                this.enhancementType = enhancementType;
                this.enhance = enhance;
                this.name = name;
            } else if (itemEnum == ItemEnum.ARMOR) {
                if (enhancementType == EnhancementTypesEnum.ARMORCLASS) {
                    //Create ArmorClass
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                }
            } else if (itemEnum == ItemEnum.SHIELD) {
                if (enhancementType == EnhancementTypesEnum.ARMORCLASS ||
                        enhancementType == EnhancementTypesEnum.STRENGTH ||
                        enhancementType == EnhancementTypesEnum.CONSTITUTION) {
                    //Create Shield
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                }
            } else if (itemEnum == ItemEnum.RING) {

                if (enhancementType == EnhancementTypesEnum.ARMORCLASS ||
                        enhancementType == EnhancementTypesEnum.STRENGTH ||
                        enhancementType == EnhancementTypesEnum.CONSTITUTION) {
                    //Create Ring
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                }
            } else if (itemEnum == ItemEnum.BELT) {
                if (enhancementType == EnhancementTypesEnum.STRENGTH ||
                        enhancementType == EnhancementTypesEnum.CONSTITUTION) {
                    //Create Belt
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                }
            } else if (itemEnum == ItemEnum.BOOTS) {
                if (enhancementType == EnhancementTypesEnum.ARMORCLASS ||
                        enhancementType == EnhancementTypesEnum.DEXTERITY) {
                    //Create Boots
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                }
            } else if (itemEnum == ItemEnum.WEAPON) {
                if (enhancementType == EnhancementTypesEnum.ATTACKBONUS ||
                        enhancementType == EnhancementTypesEnum.DAMAGEBONUS) {
                    //Crete weapon
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                }
            } else {
                System.out.println("Invalid item");
            }
        }
    }

    /**
     * This method is for setting the enhance amount of items on a map depending on the level of the character.
     * @param enhance the enhancement amount
     * @param level the level of the character
     */
    public void setItemOnMapEnhancement(int enhance, int level) {
        // Check if the level is a valid number
        if(level < 1) {
            System.out.println("ERROR!! The level of the Character is less than 1!!");
            return;
        }

        // enhancement according to the level of a character
        if(level > 0 && level < 5) {
            this.enhance = 1;
            return;
        } else if(level > 4 && level < 9) {
            this.enhance = 2;
            return;
        } else if(level > 8 && level < 13) {
            this.enhance = 3;
            return;
        } else if(level > 12 && level < 17) {
            this.enhance = 4;
            return;
        } else {
            this.enhance = 5;
        }
    }

    /**
     * Overriding equals function for testing.
     *
     * @param object an object to be compared
     * @return a boolean indicating it the items are equal.
     */
    @Override
    public boolean equals(Object object) {

        if (object instanceof Item) {
            Item ob = (Item) object;

            return ob.item == this.item && ob.enhancementType == this.enhancementType && ob.coordinate == this.coordinate
                    && ob.enhance == this.enhance;
        }
        return false;
    }

    /**
     * A method for saving an Item object using JAXB
     */
    public void saveItem()  {

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Item.class);

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(this,new FileOutputStream("src/main/java/org/resources/items/"+this.name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method for loading an Item object using JAXB
     * @param name of the item to be loaded
     * @return an existing item
     */
    public Item loadItem(String name){
        try {
        JAXBContext jc = JAXBContext.newInstance(Item.class);
        Unmarshaller u = null;
        u = jc.createUnmarshaller();
        File f = new File("src/main/java/org/resources/items/"+name);
        return (Item) u.unmarshal(f);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("No such item exists!");
        }
        return null;
    }

    /**
     * A method to set the Item object to a string
     * @return a string containing the information about this item.
     */
    @Override
    public String toString() {
        return "Item{" +
                "item=" + item +
                ", enhancementType=" + enhancementType +
                ", enhance=" + enhance +
                ", coordinate=" + coordinate +
                '}';
    }
}
