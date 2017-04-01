package main.java.org.model.CharacterPackage;

import main.java.org.Service.Calculation;

    /**
     * This class represent a dexterity object and implement AbilityInterface
     *
     * @author Parisa Nikzad
     * @version 1.0.0
     * @since 2017-02-22
     */
    public class Dexterity implements AbilityInterface {
        protected int dexterity;

        /**
         * Dexterity constructor
         */
        public Dexterity() {
            Calculation score = new Calculation();
            this.dexterity = score.getCalculation();
        }

        /**
         * A method to get the dexterity
         *
         * @return the dexterity as an integer.
         */
        @Override
        public int get() {
            return this.dexterity;
        }

        /**
         * A method to set the dexterity
         *
         * @param value the dexterity as an integer to be set.
         */
        @Override
        public void set(int value) {
            this.dexterity = value;
        }

        /**
         * A method to get the dexterity modifier
         *
         * @return an integer dexterity modifier
         */
        @Override
        public int modifier(){
            Modifier modifier = new Modifier(this.dexterity);
            return modifier.get();
        }
}
