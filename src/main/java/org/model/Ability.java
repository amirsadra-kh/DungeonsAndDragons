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

    public int getHitPoints() {
        //System.out.println("the roll dice for hitpoints " + this.hitPoints);
        //System.out.println("the strength in hitpoints " + this.strength.get());
        //System.out.println("strength modifier in hitpoints " + this.strength.modifier());
        int hitPoints = this.strength.modifier() + this.hitPoints;
        //System.out.println("HitPoint " + hitPoints);
        return hitPoints;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {

        this.armorClass = 10 + this.dexterity.modifier() + armorClass;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = 1+ attackBonus;
        //System.out.println("Attack Bonus is " + attackBonus);
    }

    public int getDamageBonus() {
        return damageBonus;
    }


    public void setDamageBonus(int damageBonus) {

        modifier = new Modifier(getStrength());
        damageBonus = this.strength.modifier(); //  +itemBonus  will add itemBonous to this equation
        //System.out.println("Damage Bonus is " + damageBonus);
        this.damageBonus = damageBonus;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "strength = " + strength.get() +
                ", strength modifier = " + strength.modifier() +
                ", constitution=" + constitution.get() +
                ", constitution modifier = " + constitution.modifier() +
                ", dexterity=" + dexterity.get() +
                ", dexterity modifier = " + dexterity.modifier() +
                ", hitPoints=" + getHitPoints() +
                ", level=" + getLevel() +
                ", armorClass=" + getArmorClass() +
                ", attackBonus=" + getAttackBonus() +
                ", damageBonus=" + getDamageBonus() +
                '}';
    }
}
