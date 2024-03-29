package com.example.teste;

/**
 * Class that represents the screen in which the user can choose to see the nails' portfolio
 * or the hair portfolio
 *
 * @author Jonas C. Costa
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Portfolio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio);
        setUpListeners();
    }

    private void setUpListeners() {
        //Button that goes to the hair portfolio screen
        Button hair_button = findViewById(R.id.hairPortfolioButton);
        hair_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Portfolio.this, HairPortfolio.class));
            }
        });

        //Button that goes to the nail portfolio screen
        Button nails_button = findViewById(R.id.nailsPortfolioButton);
        nails_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Portfolio.this, NailsPortfolio.class));
            }
        });
    }
}
