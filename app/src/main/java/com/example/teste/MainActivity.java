package com.example.teste;

/**
 * Class that represents the main screen of the app, contains the name, slogan, and from there
 * the user can either go see the portfolio or schedule an appointment
 *
 * @author Jonas C. Costa
 */

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpListeners();
    }

    private void setUpListeners() {
        //Button that goes to the registration screen
        Button registrationButton = findViewById(R.id.registrationButton);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Registration.class));
            }
        });

        //Button that goes to the portfolio screen
        Button portfolioButton = findViewById(R.id.portfolioButton);
        portfolioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Portfolio.class));
            }
        });

    }
}