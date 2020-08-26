package com.example.teste;

/**
   Class that gets the user's name and email
   @author Jonas C. Costa

 */

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ContactInformation extends AppCompatActivity {
    private String name;
    private String email;
    private Button confirmationButton;
    private boolean nameIsCorrect, emailIsCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_information);
        nameIsCorrect = false;
        emailIsCorrect = false;
        setUpListeners();
    }

    private void setUpListeners() {

        EditText clientName = findViewById(R.id.nameEditText);
        setUpName(clientName);

        EditText email = findViewById(R.id.emailEditText);
        setUpEmail(email);

        confirmationButton = findViewById(R.id.final_button);
        confirmationButton.setEnabled(false);
        setUpButton(confirmationButton);

    }

    private void setUpButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactInformation.this, Confirmation.class));
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            GMailSender sender = new GMailSender("leilacabeleireira150@gmail.com",
                                    "framboesa");
                            sender.sendMail("Seu horário com a Leila Cabeleireira está confirmado", composeEmail(getName()),
                                    "leilacabeleireira150@gmail.com", getEmail());
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }

                }).start();
            }
        });
    }

    private String composeEmail(String name) {

        Bundle extras = getIntent().getExtras();
        assert extras != null;

        String treatment = extras.getString("treatment");
        String date = extras.getString("date");
        String time = extras.getString("time");

        String lineSeparator = System.getProperty("line.separator");

        StringBuilder result = new StringBuilder();
        result.append(lineSeparator)
                .append("Olá, ")
                .append(name.replaceAll(" .*", ""))
                .append("!")
                .append(lineSeparator)
                .append(lineSeparator)
                .append("Seu tratamento de ")
                .append(treatment)
                .append(" está marcado para o dia ")
                .append(date)
                .append(", às ")
                .append(time)
                .append(" horas. Esperamos você!")
                .append(lineSeparator)
                .append("OBS: com a pandemia, tudo está esterelizado, pa você não ficar mal!")
                .append(lineSeparator)
                .append(lineSeparator)
                .append("Atenciosamente, ")
                .append(lineSeparator)
                .append("Leila e seu sobrinho neto Luis Claudio")
                .append(lineSeparator)
                .append(lineSeparator)
                .append("LEILA CABELEIREIRA: O SALÃO DE CABELEIREIRA DA CABELEIREIRA LEILA")
        ;

        return result.toString();
    }

    public void setUpName(final EditText text) {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                setName(text.getText().toString());
                nameIsCorrect = true;
                enableButton(confirmationButton);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

    }

    public void setUpEmail(final EditText text) {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                setEmail(text.getText().toString());
                emailIsCorrect = true;
                enableButton(confirmationButton);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

    }

    public void enableButton(Button button) {
        if (nameIsCorrect && emailIsCorrect) {
            button.setEnabled(true);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
