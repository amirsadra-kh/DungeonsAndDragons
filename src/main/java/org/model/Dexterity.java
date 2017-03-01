package main.java.org.model;

import main.java.org.Service.Calculation;

    /**
     * This class represent a dexterity object and implement AbilityInterface
     * @author Parisa Nikzad
     * @version 1.0.0
     * @since 2017-02-22
     */
    public class Dexterity implements AbilityInterface {
        protected int dexterity;

        /**
         * Constructor
         */
        public Dexterity() {
            Calculation score = new Calculation();
            this.dexterity = score.getCalculation();
        }

        @Override
        public int get() {
            return this.dexterity;
        }

        @Override
        public void set(int value) {
            this.dexterity = value;
        }

        @Override
        public int modifier(){
            Modifier modifier = new Modifier(this.dexterity);
            return modifier.get();
        }
}
