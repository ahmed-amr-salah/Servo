package com.example.servo;

public abstract class Drink implements MealAttribute {
    public static class Pepsi extends Drink {
        @Override
        public String getName() {
            return "Pepsi Drink";
        }

        @Override
        public float getCost() {
            return 10.00F;
        }
    }

    public static class SevenUp extends Drink {
        @Override
        public String getName() {
            return "Seven Up Drink";
        }

        @Override
        public float getCost() {
            return 10.00F;
        }
    }

    public static class CocaCola extends Drink {
        @Override
        public String getName() {
            return "Coca Cola Drink";
        }

        @Override
        public float getCost() {
            return 10.00F;
        }
    }

}
