package main.java.org.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

@XmlRootElement
public class Item {

    ItemEnum item;
    EnhancementTypes enhancementType;
    int enhance;
    Point coordinate;


    public Item(){

    }

    public Item(ItemEnum item, EnhancementTypes enhancementType, Point coordinate, int enhance) {
        createItem(item, enhancementType, enhance);
        this.coordinate = coordinate;
        this.item = item;
    }

    public Item(ItemEnum item, EnhancementTypes enhancementType, int enhance) {
        createItem(item, enhancementType, enhance);
        this.item = item;

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

    //public EnhancementTypes getEnhancement(){ return enhancement;}

    public void setEnhancement(EnhancementTypes enhancement) {
        this.enhance = enhance;
    }

    //create getter setter for enhance


    public void createItem(ItemEnum itemEnum, EnhancementTypes enhancementType, int enhance) {
        //check if the item is "HELMET"

        for (ItemEnum e : ItemEnum.values()) {
            if (e == itemEnum) {
                this.item = itemEnum;
                this.enhancementType = enhancementType;
                this.enhance = enhance;
            }

        }


        if (itemEnum == ItemEnum.HELMET) {
            if (enhancementType == EnhancementTypes.ARMORCLASS) {
                //Create Helmet
                this.item = itemEnum;
                this.enhancementType = enhancementType;
                this.enhance = enhance;
                //save into file
            } else if (itemEnum == ItemEnum.ARMOR) {
                if (enhancementType == EnhancementTypes.ARMORCLASS) {
                    //Create ArmorClass
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    //save into file
                }

            } else if (itemEnum == ItemEnum.SHIELD) {
                if (enhancementType == EnhancementTypes.ARMORCLASS || enhancementType == EnhancementTypes.STRENGTH || enhancementType == EnhancementTypes.CONSTITUTION) {
                    //Create Shield
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
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
                    //save into file
                }
            } else if (itemEnum == ItemEnum.BOOTS) {
                if (enhancementType == EnhancementTypes.ARMORCLASS || enhancementType == EnhancementTypes.DEXTERITY) {
                    //Create Boots
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
                    //save into file
                }
            } else if (itemEnum == ItemEnum.WEAPON) {
                if (enhancementType == EnhancementTypes.ATTACKBONUS || enhancementType == EnhancementTypes.DAMAGEBONUS) {
                    //Crete weapon
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    this.enhance = enhance;
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

    public void saveItem()  {

        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Item.class);

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(this,new FileOutputStream("src/main/java/org/resources/items/"+this.item.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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

    public EnhancementTypes getEnhancementType() {
        return enhancementType;
    }

    public void setEnhancementType(EnhancementTypes enhancementType) {
        this.enhancementType = enhancementType;
    }

    public int getEnhance() {
        return enhance;
    }

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
