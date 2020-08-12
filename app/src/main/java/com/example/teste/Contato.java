package com.example.teste;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Contato  extends Register  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        setUpListeners();
    }

    private void setUpListeners(){
        Button contact_button = findViewById(R.id.final_button);
        contact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Contato.this, Final.class));

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            GMailSender sender = new GMailSender("leilacabeleireira150@gmail.com",
                                    "framboesa");
                            sender.sendMail("This is a test subject", "This is the test body content",
                                    "leilacabeleireira150@gmail.com", "jonasccosta72@gmail.com");
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }

                }).start();
            }
        });
    }
}
