package com.example.servo;

public abstract class Size implements MealAttribute {
    public static class Small extends Size  {
        @Override
        public String getName() {
            return "Small Size Meal";
        }

        @Override
        public float getCost() {
            return 70.00F;
        }
    }

    public static class Medium extends Size  {
        @Override
        public String getName() {
            return "Medium Size Meal";
        }

        @Override
        public float getCost() {
            return 120.00F;
        }
    }

    public static class Large extends Size  {
        @Override
        public String getName() {
            return "Large Size Meal";
        }

        @Override
        public float getCost() {
            return 140.00F;
        }
    }
}
