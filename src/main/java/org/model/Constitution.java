package main.java.org.model;

/**
 * This class is to validate constitution
 * @author Parisa Nikzad
 * @version 1.0.0
 * @since 2017-02-22
 */
public class Constitution implements AbilityInterface {
    protected int constitution;

    public Constitution() {
        Calculation score = new Calculation();
        this.constitution = score.getCalculation();
        //System.out.println("New Constitution Value " + this.constitution);
    }

    @Override
    public int get() {
        //System.out.println("Get Constitution Value " + this.constitution);
        return this.constitution;
    }

    @Override
    public void set(int value) {
        this.constitution = value;
        //System.out.println("Newly Set Constitution Value " + this.constitution);
    }

    @Override
    public int modifier(){
        Modifier modifier = new Modifier(this.constitution);
        return modifier.get();
    }
}

