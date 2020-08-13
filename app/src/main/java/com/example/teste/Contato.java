package com.example.teste;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Contato  extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        setUpListeners();
    }

    private void setUpListeners(){
        Button contact_button = findViewById(R.id.final_button);
        EditText client = findViewById(R.id.editTextTextPersonName2);
        final String name = client.getText().toString();
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        final String emailAdress = email.getText().toString();
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
                            sender.sendMail("Seu horário com a Leila Cabeleireira está confirmado", composeEmail(name),
                                    "leilacabeleireira150@gmail.com", "jonasccosta72@gmail.com");
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
}
