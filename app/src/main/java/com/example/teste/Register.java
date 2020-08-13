package com.example.teste;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Register extends AppCompatActivity {
    private List<String> atividades = new ArrayList<>();
    private String horario;
    private String data;
    private Button button;
    public boolean horarioIsCorrect, activityIsSelected, dataIsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        horarioIsCorrect = false;
        activityIsSelected = false;
        dataIsSelected = false;
        button = findViewById(R.id.contact_button);
        setUpListeners();
    }


    private void setUpListeners() {
        RadioButton cabelos = findViewById(R.id.cabelo_radioButton);
        setUpRadioButtons(cabelos);

        RadioButton unhas1 = findViewById(R.id.unhas_radiobutton1);
        setUpRadioButtons(unhas1);

        RadioButton hitratacao = findViewById(R.id.hitratacao_radiobutton);
        setUpRadioButtons(hitratacao);

        RadioButton unhas2 = findViewById(R.id.unhas_radiobutton2);
        setUpRadioButtons(unhas2);

        CalendarView calendarView = findViewById(R.id.calendarView);
        setUpData(calendarView);

        EditText horario = findViewById(R.id.horario_textEdit);
        setUpHorario(horario);

        button.setEnabled(false);
        setUpButton(button);

    }

    public void setUpButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Contato.class));
                sendInfo();
            }
        });

    }

    public void setUpRadioButtons(final RadioButton radioButton) {
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    addToList(radioButton);
                    System.out.println(getAtividades().get(0));
                    activityIsSelected = true;
                    enableButton(button);
                }

            }
        });
    }

    public void setUpData(final CalendarView calendarView) {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                setData(dayOfMonth + "/" + month + "/" + year);
                dataIsSelected = true;
                enableButton(button);
            }
        });
    }

    public void setUpHorario(final EditText text) {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                horarioIsCorrect = enableSubmitIfReady(text);
                enableButton(button);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

    }

    public boolean enableSubmitIfReady(EditText text) {
        boolean correctFormat = false;
        if (text.getText().toString().length() == 5) {
            String hora = text.getText().toString();
            Date date = null;
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                date = formatter.parse(hora);
                assert date != null;
                if (!hora.equals(formatter.format(date))) {
                    date = null;
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            if (date != null) {
                // Invalid date format
                this.setHorario(hora);
                correctFormat = true;
            }


        }
        return correctFormat;
    }

    public void enableButton(Button button) {
        if (dataIsSelected && horarioIsCorrect && activityIsSelected) {
            button.setEnabled(true);
        }
    }

    private void sendInfo(){
        String horario = this.getHorario();
        String data = this.getData();
        String tratamento = this.concatenate(this.getAtividades());

        Intent i = new Intent(Register.this, Contato.class);

        i.putExtra("tratamento", tratamento);
        i.putExtra("horario", horario);
        i.putExtra("data", data);

        startActivity(i);

    }

    private String concatenate(List<String> strings){
        String result = "";
        if(strings.size() == 0){
            return "";
        }
        else if(strings.size() == 1){
            result = strings.get(0);
        }
        else if(strings.size() == 2){
            result = strings.get(0) + " e " + strings.get(1);
        }
        else{
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < strings.size()-1; i++){
                builder.append((strings.get(i)))
                .append(", ");
            }
            builder.deleteCharAt(builder.length()-1);
            builder.deleteCharAt(builder.length()-1);
            builder.append(" e ");
            builder.append(strings.get(strings.size()-1));
            result = builder.toString();
        }

        return result;
    }

    private void addToList(RadioButton button) {
        if (button.isChecked()) {
            atividades.add(button.getText().toString());
        }
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getAtividades() {
        return atividades;
    }

    public String getHorario() {
        return horario;
    }

    public String getData() {
        return data;
    }
}
