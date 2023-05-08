package com.example.servo;

public abstract class Type implements MealAttribute {
     public static class Chicken extends Type {
        @Override
        public String getName() {
            return "Chicken Feteera";
        }

        @Override
        public float getCost() {
            return 30.0F;
        }
    }
}
