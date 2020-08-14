package com.example.teste;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Contato  extends AppCompatActivity  {
    private String nome;
    private String email;
    private Button contact_button;
    private boolean nomeIsCorrect, emailIsCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        setUpListeners();
    }

    private void setUpListeners(){

        EditText client = findViewById(R.id.editTextTextPersonName2);
        setUpName(client);

        EditText email = findViewById(R.id.editTextTextEmailAddress);
        setUpEmail(email);

        contact_button = findViewById(R.id.final_button);
        contact_button.setEnabled(false);
        setUpButton(contact_button);

    }

    private void setUpButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Contato.this, Final.class));
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            GMailSender sender = new GMailSender("leilacabeleireira150@gmail.com",
                                    "framboesa");
                            sender.sendMail("Seu horário com a Leila Cabeleireira está confirmado", composeEmail(getNome()),
                                    "leilacabeleireira150@gmail.com", getEmail());
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }

                }).start();
            }
        });
    }

    private String composeEmail(String name){

        Bundle extras = getIntent().getExtras();
        assert extras != null;

        String horario = extras.getString("horario");
        String data = extras.getString("data");
        String tratamento = extras.getString("tratamento");
        String lineSeparator = System.getProperty("line.separator");

        StringBuilder result = new StringBuilder();
        result.append(lineSeparator)
                .append("Olá, ")
                .append(name.replaceAll(" .*", ""))
                .append("!")
                .append(lineSeparator)
                .append(lineSeparator)
                .append("Seu tratamento de ")
                .append(tratamento)
                .append(" está marcado para o dia ")
                .append(data)
                .append(", às ")
                .append(horario)
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
                setNome(text.getText().toString());
                nomeIsCorrect = true;
                enableButton(contact_button);
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
                enableButton(contact_button);
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
        if (nomeIsCorrect && emailIsCorrect) {
            button.setEnabled(true);
        }
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
