package com.example.servo;
import java.util.List;

public class Meal {
    private MealBuilder builder;
    public Meal(MealBuilder fetBuilder) {
        this.builder = fetBuilder;
    }
    public float getTotalCost() {
        float totalCost = 0.0F;
        List<MealAttribute> contents = builder.getContents();
        for (MealAttribute attribute : contents) {
            totalCost += attribute.getCost();
        }
        return totalCost;
    }

    public String getMealInfo() {
        List<MealAttribute> contents = builder.getContents();
        StringBuilder fetInfo = new StringBuilder();
        for (MealAttribute attribute : contents) {
            fetInfo.append(attribute.getName());
            fetInfo.append("\n");
        }
        return fetInfo.toString();
    }
}
