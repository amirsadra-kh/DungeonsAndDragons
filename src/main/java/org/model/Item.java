package main.java.org.model;

import main.java.org.Service.ObjectSaver;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

@XmlRootElement
public class Item {

    ItemEnum item;
    EnhancementTypesEnum enhancementType;
    int enhance;
    Point coordinate;


    public Item(){

    }

    public Item(ItemEnum item, EnhancementTypesEnum enhancementType, Point coordinate) {
        createItem(item, enhancementType);
        this.coordinate = coordinate;
        this.item = item;
    }

    public Item(ItemEnum item, EnhancementTypesEnum enhancementType) {
        createItem(item, enhancementType);
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

    public void setEnhancement(EnhancementTypesEnum enhancement) {
        this.enhance = enhance;
    }

    //create getter setter for enhance


    public void createItem(ItemEnum itemEnum, EnhancementTypesEnum enhancementType) {
        //check if the item is "HELMET"

        for (ItemEnum e : ItemEnum.values()) {
            if (e == itemEnum) {
                this.item = itemEnum;
                this.enhancementType = enhancementType;
                enhance = +1;
            }

        }


        if (itemEnum == ItemEnum.HELMET) {
            if (enhancementType == EnhancementTypesEnum.ARMORCLASS) {
                //Create Helmet
                this.item = itemEnum;
                this.enhancementType = enhancementType;
                enhance = +1;
                //save into file
            } else if (itemEnum == ItemEnum.ARMOR) {
                if (enhancementType == EnhancementTypesEnum.ARMORCLASS) {
                    //Create ArmorClass
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }

            } else if (itemEnum == ItemEnum.SHIELD) {
                if (enhancementType == EnhancementTypesEnum.ARMORCLASS ||
                        enhancementType == EnhancementTypesEnum.STRENGTH ||
                        enhancementType == EnhancementTypesEnum.CONSTITUTION) {
                    //Create Shield
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            } else if (itemEnum == ItemEnum.RING) {
                if (enhancementType == EnhancementTypesEnum.ARMORCLASS ||
                        enhancementType == EnhancementTypesEnum.STRENGTH ||
                        enhancementType == EnhancementTypesEnum.CONSTITUTION) {
                    //Create Ring
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            } else if (itemEnum == ItemEnum.BELT) {
                if (enhancementType == EnhancementTypesEnum.STRENGTH ||
                        enhancementType == EnhancementTypesEnum.CONSTITUTION) {
                    //Create Belt
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            } else if (itemEnum == ItemEnum.BOOTS) {
                if (enhancementType == EnhancementTypesEnum.ARMORCLASS ||
                        enhancementType == EnhancementTypesEnum.DEXTERITY) {
                    //Create Boots
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            } else if (itemEnum == ItemEnum.WEAPON) {
                if (enhancementType == EnhancementTypesEnum.ATTACKBONUS ||
                        enhancementType == EnhancementTypesEnum.DAMAGEBONUS) {
                    //Crete weapon
                    this.item = itemEnum;
                    this.enhancementType = enhancementType;
                    enhance = +1;
                    //save into file
                }
            } else {
                System.out.println("Invalid item");
            }
        }
    }

    public void editItem(boolean remove, EnhancementTypesEnum abilityEnum) {
        if (remove) {
            // load and save functions
        } else {
            createItem(this.item, abilityEnum);
        }
    }

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
            //e.printStackTrace();
            System.out.println("No such item exists!");
        }
        return null;
    }

    public EnhancementTypesEnum getEnhancementType() {
        return enhancementType;
    }

    public void setEnhancementType(EnhancementTypesEnum enhancementType) {
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
