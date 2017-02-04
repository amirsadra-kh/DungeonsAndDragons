package main.java.org.model;

public class Ability {

    private int strength;
    private int constitution;
    private int dexterity;
    private int hitPoints;
    private int level;
    private int armorClass;
    private int attackBonus;
    private int damageBonus;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
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
        this.armorClass = armorClass;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    public void setDamageBonus(int damageBonus) {
        this.damageBonus = damageBonus;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "strength=" + strength +
                ", constitution=" + constitution +
                ", dexterity=" + dexterity +
                ", hitPoints=" + hitPoints +
                ", level=" + level +
                ", armorClass=" + armorClass +
                ", attackBonus=" + attackBonus +
                ", damageBonus=" + damageBonus +
                '}';
    }
}
