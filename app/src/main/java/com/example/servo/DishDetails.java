package com.example.servo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class DishDetails extends AppCompatActivity {
    float totalCost = 0.0F;
    float prevPriceSize = 0.0F;
    float prevPriceDrink = 0.0F;

    TextView price;
    Button checkoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_details);

        getSupportActionBar().hide();

        price = (TextView)findViewById(R.id.total_cost);
        price.setText("LE 0.0");

        CheckBox veges = (CheckBox)findViewById(R.id.checkbox_veges);
        CheckBox cheese = (CheckBox)findViewById(R.id.checkbox_cheese);
        CheckBox turkey = (CheckBox)findViewById(R.id.checkbox_turkey);
        CheckBox mushroom = (CheckBox)findViewById(R.id.checkbox_shrom);

        veges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(veges.isChecked())
                    totalCost += 10;
                else
                    totalCost -= 10;
                price.setText("L.E " + totalCost);
            }
        });

        cheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cheese.isChecked())
                    totalCost += 10;
                else
                    totalCost -= 10;
                price.setText("L.E " + totalCost);
            }

        });

        mushroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mushroom.isChecked())
                    totalCost += 10;
                else
                    totalCost -= 10;
                price.setText("L.E " + totalCost);
            }

        });

        turkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(turkey.isChecked())
                    totalCost += 10;
                else
                    totalCost -= 10;
                price.setText("L.E " + totalCost);
            }

        });

        checkoutBtn = (Button)findViewById(R.id.checkout_btn);
        checkoutBtn.setEnabled(false);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MealBuilder chickenFeteeraBuilder = new MealBuilder().withType(new Type.Chicken());

                if (((RadioButton)findViewById((R.id.large))).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withSize(new Size.Large());
                if (((RadioButton)findViewById((R.id.med))).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withSize(new Size.Medium());
                if (((RadioButton)findViewById((R.id.small))).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withSize(new Size.Small());

                if (((CheckBox)findViewById(R.id.checkbox_veges)).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withStuffing(new Stuffing.Vegetables());
                if (((CheckBox)findViewById(R.id.checkbox_cheese)).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withStuffing(new Stuffing.Cheese());
                if (((CheckBox)findViewById(R.id.checkbox_shrom)).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withStuffing(new Stuffing.Mushroom());
                if (((CheckBox)findViewById(R.id.checkbox_turkey)).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withStuffing(new Stuffing.Turkey());


                if (((RadioButton)findViewById((R.id.coke))).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withDrink(new Drink.CocaCola());
                if (((RadioButton)findViewById((R.id.sup))).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withDrink(new Drink.SevenUp());
                if (((RadioButton)findViewById((R.id.pepsi))).isChecked())
                    chickenFeteeraBuilder = chickenFeteeraBuilder.withDrink(new Drink.Pepsi());
                Meal chickenFeteera = chickenFeteeraBuilder.build();
                Log.d("Feteera", chickenFeteera.getMealInfo());

                Intent intent = new Intent(DishDetails.this, Confirmation.class);
                intent.putExtra("details", chickenFeteera.getMealInfo());
                startActivity(intent);
            }
        });

    }

    public void onDrinkRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.pepsi:
                if (checked) {
                    totalCost -= prevPriceDrink;
                    totalCost += 10.0F;
                    prevPriceDrink = 10.0F; // should be fetched from database
                }
                break;
            case R.id.sup:
                if (checked) {
                    totalCost -= prevPriceDrink;
                    totalCost += 10.0F;
                    prevPriceDrink = 10.0F; // should be fetched from database
                }
                break;
            case R.id.coke:
                if (checked) {
                    totalCost -= prevPriceDrink;
                    totalCost += 10.0F;
                    prevPriceDrink = 10.0F; // should be fetched from database
                }
        }
        price.setText("L.E " + totalCost);
    }
    public void onAddCheckboxClicked(View view) {

    }

    public void onSizeRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.large:
                if (checked) {
                    totalCost -= prevPriceSize;
                    totalCost += 170.0F;
                    prevPriceSize = 170.0F; // should be fetched from database
                }
                break;
            case R.id.med:
                if (checked) {
                    totalCost -= prevPriceSize;
                    totalCost += 150.0F;
                    prevPriceSize = 150.0F; // should be fetched from database
                }
                break;
            case R.id.small:
                if (checked) {
                    totalCost -= prevPriceSize;
                    totalCost += 100.0F;
                    prevPriceSize = 100.0F; // should be fetched from database
                }
        }
        if (!checkoutBtn.isEnabled())
            checkoutBtn.setEnabled(true);
        price.setText("L.E " + totalCost);
    }


}


// what to do when we click on the button

