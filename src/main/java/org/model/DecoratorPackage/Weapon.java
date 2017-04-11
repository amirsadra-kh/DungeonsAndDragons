package main.java.org.model.DecoratorPackage;

import main.java.org.model.CharacterPackage.Character;
import main.java.org.model.ColorConstants;
import main.java.org.model.Item;
import main.java.org.model.Longbow;
import main.java.org.model.Longsword;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileOutputStream;

/**
 * The abstract Weapon class defines the functionality of any Weapon
 * as an extension to a regular item
 *
 * @author Freyja Jokulsdottir
 * @version 1.0
 * @since 02.04.2017
 */
@XmlRootElement
public class Weapon extends Item {
    private String specialEnhancement = "Weapon";
    private int damage = 0;
    private int normalRange = 0;
    private int maxRange = 0;
    private String type;

    /**
     * A method to get the special enhancement of the weapon
     * TODO get this string to save with the weapon item
     * @return WeaponEnhanceDecorator
     */
    @XmlElement
    public String getSpecialEnhance() {
        return this.specialEnhancement;
    }

    /**
     * A method to set the damage and range according to the type of weapon
     * @param character
     */
    public void setDamage(Character character) {
        if(this.type.equalsIgnoreCase("longbow")) {
            Longbow longbow = new Longbow();
            longbow.setDamage(character);
            this.damage = longbow.getDamage();
            this.normalRange = longbow.getNormalRange();
            this.maxRange = longbow.getMaxRange();
        } else {
            Longsword longsword = new Longsword();
            longsword.setDamage(character);
            this.damage = longsword.getDamage();
        }
        this.damage += this.getEnhance();
        // Finish the log window of the bonus
        log();
    }

    /**
     * A method to show the log for enhancement of weapon and total bonus of weapon
     */
    private void log(){
        System.out.println(ColorConstants.ANSI_RED +"Enhancement of the weapon: +" +this.getEnhance()
                +ColorConstants.ANSI_RESET);
        System.out.println(ColorConstants.ANSI_RED +"Total AttackBonus or DamageBonus of the weapon: "
                +damage +ColorConstants.ANSI_RESET);
        System.out.println("------------------------------------------------------------");
    }

    /**
     * A setter for the type of the weapon; longsword or longbow
     * @param type
     */

    public void setType(String type)  {
        this.type = type;
    }

    /**
     * A getter for the type of the weapon
     * @return longsword or longbow
     */
    @XmlElement
    public String getType() {
        return this.type;
    }

    /**
     * A method to get the damage, AttackBonus or DamageBonus, of the weapon
     * @return
     */
    @XmlElement
    public int getDamage() {
        return this.damage;
    }

    /**
     * A method to get the normal range of the weapon
     * @return the normal range
     */
    @XmlElement
    public int getNormalRange() {
        return this.normalRange;
    }

    /**
     * A method to set the normal range if the weapon is a ranged weapon
     */
    public void setNormalRange() {
        if(this.type.equalsIgnoreCase("longbow")) {
            Longbow longbow = new Longbow();
            this.normalRange = longbow.getNormalRange();
        }
    }

    /**
     * A method to get the maximum range of the weapon
     * @return the maximum range
     */
    @XmlElement
    public int getMaxRange() {
        return this.maxRange;
    }

    /**
     * A method for loading an Item object using JAXB
     * @param name
     */
    @Override
    public Item loadItem(String name){
        try {
            JAXBContext jc = JAXBContext.newInstance(Weapon.class);
            Unmarshaller u = null;
            u = jc.createUnmarshaller();
            File f = new File("src/main/java/org/resources/items/"+name);
            return (Item) u.unmarshal(f);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    /**
     * A method for saving an Item object using JAXB
     */
    @Override
    public void saveItem()  {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Weapon.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(this,new FileOutputStream("src/main/java/org/resources/items/"+super.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
