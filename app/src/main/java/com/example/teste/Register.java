package com.example.teste;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Register extends AppCompatActivity {
    private List<RadioButton> atividades = new ArrayList<>();
    private String horario;
    private String data;
    public boolean horarioIsCorrect, activityIsSelected, dataIsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        horarioIsCorrect = false;
        activityIsSelected = true;
        dataIsSelected = false;
        setUpListeners();
    }


    private void setUpListeners(){
        final Button contact_button = findViewById(R.id.contact_button);
        contact_button.setEnabled(false);

        EditText horario = findViewById(R.id.horario_textEdit);
        CalendarView calendarView = findViewById(R.id.calendarView);


        setUpHorario(horario, contact_button);




        RadioButton cabelos = findViewById(R.id.cabelo_radioButton);
        RadioButton unhas1 = findViewById(R.id.unhas_radiobutton1);
        RadioButton hitratação = findViewById(R.id.hitratacao_radiobutton);
        RadioButton unhas2 = findViewById(R.id.unhas_radiobutton2);


        addToList(cabelos);
        addToList(unhas1);
        addToList(hitratação);
        addToList(unhas2);

        setUpRadioButtons(cabelos, contact_button);
        setUpRadioButtons(unhas1, contact_button);
        setUpRadioButtons(hitratação, contact_button);
        setUpRadioButtons(unhas2, contact_button);

        setUpData(calendarView, contact_button);



        //TextView text = findViewById(R.id.horario_textEdit);
        //System.out.println(text);




        /*contact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Contato.class)); }
        }
        );*/

        setUpButton(contact_button);




    }

    private void setUpButton(Button button){
        //if(dataIsSelected && horarioIsCorrect && activityIsSelected){
            button.setEnabled(true);
            button.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View view) {
                                                      startActivity(new Intent(Register.this, Contato.class)); }
                                              }
            );
        //}
    }

    private void setUpRadioButtons(RadioButton radioButton, final Button button){
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        enableButton(button);
                        //activityIsSelected = b;
                    }

                }
            });
    }

    private void setUpHorario(final EditText text, final Button yourButton){
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

                horarioIsCorrect = enableSubmitIfReady(text);
                //enableButton(yourButton);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

    }

    public boolean enableSubmitIfReady(EditText text){
        boolean isNotEmpty = text.getText().toString().length() > 0;

        boolean correctFormat = false;

        String hora = text.getText().toString();

        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            date = formatter.parse(hora);
            if (!hora.equals(formatter.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        if (date != null) {
            // Invalid date format
            correctFormat = true;
        }

        return correctFormat && isNotEmpty;
    }

    private void enableButton(Button button){
        if(dataIsSelected && horarioIsCorrect && activityIsSelected){
            button.setEnabled(true);
        }

    }

    private void setUpData(final CalendarView calendarView, final Button button){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dataIsSelected = true;
                //enableButton(button);
            }
        });
    }


    private void addToList(RadioButton button){
        if(button.isChecked()){
            atividades.add(button);
        }
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setData(CalendarView calendarView) {
        DateFormat df = new SimpleDateFormat("dd/MM/aaaa");
        this.data = df.format(calendarView.getDate());
    }

    public List<RadioButton> getAtividades() {
        return atividades;
    }

    public String getHorario() {
        return horario;
    }

    public String getData() {
        return data;
    }
}
