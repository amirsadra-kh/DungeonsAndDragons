package main.java.org.model;

import main.java.org.Service.Calculation;

/**
     * This class is to validate dexterity
     * @author Parisa Nikzad
     * @version 1.0.0
     * @since 2017-02-22
     */
    public class Dexterity implements AbilityInterface {
        protected int dexterity;

        public Dexterity() {
            Calculation score = new Calculation();
            this.dexterity = score.getCalculation();
            //System.out.println("New Dexterity Value " + this.dexterity);
        }

        @Override
        public int get() {
            //System.out.println("Get Dexterity Value " + this.dexterity);
            return this.dexterity;
        }

        @Override
        public void set(int value) {
            this.dexterity = value;
            //System.out.println("Newly Set Dexterityh Value " + this.dexterity);
        }

        @Override
        public int modifier(){
            Modifier modifier = new Modifier(this.dexterity);
            return modifier.get();
        }
}
