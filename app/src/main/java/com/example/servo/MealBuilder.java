package com.example.servo;
import java.util.ArrayList;
import java.util.List;

public class MealBuilder {
    private List<MealAttribute> contents = new ArrayList<>();

    public MealBuilder withSize(Size size) {
        addMealAttribute(size);
        return this;
    }

    public MealBuilder withStuffing(Stuffing stuffing) {
        addMealAttribute(stuffing);
        return this;
    }

    public MealBuilder withDrink(Drink drink) {
        addMealAttribute(drink);
        return this;
    }

    public MealBuilder withType(Type type) {
        addMealAttribute(type);
        return this;
    }
    private void addMealAttribute(MealAttribute attribute) {
        contents.add(attribute);
    }

    public List<MealAttribute> getContents() {
        return this.contents;
    }

    public Meal build() {
        return new Meal(this);
    }
}
