package main.java.org.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

/**
 * TODO Add a comment here
 */
@XmlRootElement
public class Item {
    private ItemEnum item;
    private EnhancementTypes enhancementType;
    private int enhance;
    private Point coordinate;
    private String name;

    /**
     * TODO add a comment here
     */
    public Item(){

    }

    /**
     * TODO Add a comment here
     * @param name
     * @param item
     * @param enhancementType
     * @param coordinate
     * @param enhance
     */
    public Item(String name, ItemEnum item, EnhancementTypes enhancementType, Point coordinate, int enhance) {
        createItem(name, item, enhancementType, enhance);
        this.coordinate = coordinate;
        this.item = item;
        this.name = name;
    }

    /**
     * TODO Add a comment here
     * @param name
     * @param item
     * @param enhancementType
     * @param enhance
     */
    public Item(String name, ItemEnum item, EnhancementTypes enhancementType, int enhance) {
        createItem(name, item, enhancementType, enhance);
        this.item = item;
        this.name = name;

    }

    /**
     * TODO Add a comment here
     * @return
     */
    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * TODO Add a comment here
     * @param coordinate
     */
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * TODO Add a comment here
     * @return
     */
    public ItemEnum getItem() {
        return item;
    }

    /**
     * TODO Add a comment here
     * @param item
     */
    public void setItem(ItemEnum item) {
        this.item = item;
    }

    //public EnhancementTypes getEnhancement(){ return enhancement;}

    /**
     * TODO Add a comment here
     * @param enhancement
     */
    public void setEnhancement(EnhancementTypes enhancement) {
        this.enhance = enhance;
    }

    //create getter setter for enhance

    /**
     * TODO Add a comment here
     * @param name
     * @param itemEnum
     * @param enhancementType
     * @param enhance
     */
    public void createItem(String name, ItemEnum itemEnum, EnhancementTypes enhancementType, int enhance) {
        //check if the item is "HELMET"

        for (ItemEnum e : ItemEnum.values()) {
            if (e == itemEnum) {
                this.item = itemEnum;
                this.enhancementType = enhancementType;
                this.enhance = enhance;
                this.name = name;
            }

        }


        if (itemEnum == ItemEnum.HELMET) {
            if (enhancementType == EnhancementTypes.ARMORCLASS) {
                //Create Helmet
                this.item = itemEnum;
                this.enhancementType = enhancementType;
                this.enhance = enhance;
                this.name = name;
                //save into file
            } else if (itemEnum == ItemEnum.ARMOR) {
                if (enhancementType == EnhancementTypes.ARMORCLASS) {
                    //Create ArmorClass
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                    //save into file
                }

            } else if (itemEnum == ItemEnum.SHIELD) {
                if (enhancementType == EnhancementTypes.ARMORCLASS || enhancementType == EnhancementTypes.STRENGTH || enhancementType == EnhancementTypes.CONSTITUTION) {
                    //Create Shield
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                    //save into file
                }
            } else if (itemEnum == ItemEnum.RING) {
                if (enhancementType == EnhancementTypes.ARMORCLASS || enhancementType == EnhancementTypes.STRENGTH || enhancementType == EnhancementTypes.CONSTITUTION) {
                    //Create Ring
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    //save into file
                }
            } else if (itemEnum == ItemEnum.BELT) {
                if (enhancementType == EnhancementTypes.STRENGTH || enhancementType == EnhancementTypes.CONSTITUTION) {
                    //Create Belt
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                    //save into file
                }
            } else if (itemEnum == ItemEnum.BOOTS) {
                if (enhancementType == EnhancementTypes.ARMORCLASS || enhancementType == EnhancementTypes.DEXTERITY) {
                    //Create Boots
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                    //save into file
                }
            } else if (itemEnum == ItemEnum.WEAPON) {
                if (enhancementType == EnhancementTypes.ATTACKBONUS || enhancementType == EnhancementTypes.DAMAGEBONUS) {
                    //Crete weapon
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    this.name = name;
                    //save into file
                }
            } else {
                System.out.println("Invalid item");
            }
        }
    }

//    public void editItem(boolean remove, EnhancementTypes abilityEnum) {
//        if (remove) {
//            // load and save functions
//        } else {
//            createItem(this.item, abilityEnum);
//        }
//    }

    /**
     * TODO Add a comment here
     * @param enhance
     */
    public void enhancement(int enhance) {
        // character should call this when enters the map to enhance the item regarding to his level
    }

    /**
     * Overriding equals function for testing.
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {

        if (object instanceof Item) {
            Item ob = (Item) object;

            return ob.item == this.item && ob.enhancementType == this.enhancementType && ob.coordinate == this.coordinate && ob.enhance == this.enhance;
        }
        return false;
    }

    /**
     * TODO Add a comment here
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
     * TODO Add a comment here
     * @param name
     * @return
     */
    public Item loadItem(String name){
        try {
        JAXBContext jc = JAXBContext.newInstance(Item.class);
        Unmarshaller u = null;
        u = jc.createUnmarshaller();
        File f = new File("src/main/java/org/resources/items/"+name);
        return (Item) u.unmarshal(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO Add a comment here
     * @return
     */
    public EnhancementTypes getEnhancementType() {
        return enhancementType;
    }

    /**
     * TODO Add a comment here
     * @param enhancementType
     */
    public void setEnhancementType(EnhancementTypes enhancementType) {
        this.enhancementType = enhancementType;
    }

    /**
     * TODO Add a comment here
     * @return
     */
    public int getEnhance() {
        return enhance;
    }

    /**
     * TODO Add a comment here
     * @param enhance
     */
    public void setEnhance(int enhance) {
        this.enhance = enhance;
    }

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
