package main.java.org.model;


public class Ability {
    RollDice dice6 = new RollDice(6);
    RollDice dice10 = new RollDice(10);
    Modifier modifier;

    private int hitPoints = dice10.roll();

    private int level;
    private int armorClass;
    private int attackBonus;
    private int damageBonus;
    private Strength strength = new Strength();
    private Constitution constitution = new Constitution();
    private Dexterity dexterity = new Dexterity();


    /**
     *
     * @return
     */
    public int getStrength() {
        return strength.get();
    }


    public void setStrength(int value) {
        strength.set(value);
        //System.out.println("strength is " + strength.get());

    }

    public int getConstitution() {
        return constitution.get();
    }


    public void setConstitution(int value) {
        constitution.set(value);
        //System.out.println("Constitution is " + constitution.get());

    }

    public int getDexterity() {
        return dexterity.get();
    }


    public void setDexterity(int value) {
        dexterity.set(value);
        //System.out.println("Dexterity is " + dexterity.get());

    }

    public void setHitPoints() {
        //System.out.println("the roll dice for hitpoints " + this.hitPoints);
        //System.out.println("the strength in hitpoints " + this.strength.get());
        //System.out.println("strength modifier in hitpoints " + this.strength.modifier());
        this.hitPoints = this.strength.modifier() + this.hitPoints;
        //System.out.println("HitPoint " + hitPoints);
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public void setArmorClass(int armorClass) {

        this.armorClass = 10 + this.dexterity.modifier() + armorClass;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = 1 + attackBonus;
        //System.out.println("Attack Bonus is " + attackBonus);
    }

    public void setDamageBonus(int itemBonus) {
        modifier = new Modifier(getStrength());
        this.damageBonus = this.strength.modifier() + itemBonus;
        //System.out.println("Damage Bonus is " + damageBonus);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ability{strength = ").append(strength.get()).append(", strength modifier = ").append(strength.modifier());
        sb.append(", constitution=").append(constitution.get()).append(", constitution modifier = ").append(constitution.modifier());
        sb.append(", dexterity=").append(dexterity.get()).append(", dexterity modifier = ").append(dexterity.modifier());
        sb.append(", hitPoints=").append(this.hitPoints).append(", level=").append(this.level).append(", armorClass=").append( this.armorClass);
        sb.append( ", attackBonus=").append(this.attackBonus).append(", damageBonus=").append(this.damageBonus).append('}');
        return sb.toString();
    }
}
