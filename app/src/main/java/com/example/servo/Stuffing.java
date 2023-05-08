package com.example.servo;

public abstract class Stuffing implements MealAttribute {
    public static class Vegetables extends Stuffing {
        @Override
        public String getName() {
            return "Vegetables Addition";
        }

        @Override
        public float getCost() {
            return 25.00F;
        }
    }

    public static class Mushroom extends Stuffing {
        @Override
        public String getName() {
            return "Mushroom Addition";
        }

        @Override
        public float getCost() {
            return 25.00F;
        }
    }

    public static class Cheese extends Stuffing {
        @Override
        public String getName() {
            return "Cheese Addition";
        }

        @Override
        public float getCost() {
            return 25.00F;
        }
    }

    public static class Turkey extends Stuffing {
        @Override
        public String getName() {
            return "Turkey Addition";
        }

        @Override
        public float getCost() {
            return 30.00F;
        }
    }
}
