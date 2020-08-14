package com.example.teste;

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

    private void setUpListeners(){
        Button cabelos_button = findViewById(R.id.hairPortfolioButton);
        cabelos_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Portfolio.this, HairPortfolio.class));
            }
        }
        );

        Button unhas_button = findViewById(R.id.nailsPortfolioButton);
        unhas_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Portfolio.this, NailsPortfolio.class));
            }
        });
    }
}
