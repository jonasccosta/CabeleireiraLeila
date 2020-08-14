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

public class Registration extends AppCompatActivity {
    private List<String> activities = new ArrayList<>();
    private String time;
    private String date;
    private Button contactButton;
    public boolean timeIsCorrect, activityIsSelected, dateIsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        timeIsCorrect = false;
        activityIsSelected = false;
        dateIsSelected = false;
        contactButton = findViewById(R.id.contact_button);
        setUpListeners();
    }


    private void setUpListeners() {
        RadioButton hair = findViewById(R.id.hairRadioButton);
        setUpRadioButtons(hair);

        RadioButton nails1 = findViewById(R.id.nailsRadioButton2);
        setUpRadioButtons(nails1);

        RadioButton moisturizing = findViewById(R.id.moisturizingRadioButton);
        setUpRadioButtons(moisturizing);

        RadioButton nails2 = findViewById(R.id.nailsRadioButton1);
        setUpRadioButtons(nails2);

        CalendarView calendarView = findViewById(R.id.calendarView);
        setUpDate(calendarView);

        EditText time = findViewById(R.id.timeTextEdit);
        setUpTime(time);

        contactButton.setEnabled(false);
        setUpButton(contactButton);

    }

    public void setUpButton(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this, ContactInformation.class));
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
                    System.out.println(getActivities().get(0));
                    activityIsSelected = true;
                    enableButton(contactButton);
                }

            }
        });
    }

    public void setUpDate(final CalendarView calendarView) {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                setDate(dayOfMonth + "/" + month + "/" + year);
                dateIsSelected = true;
                enableButton(contactButton);
            }
        });
    }

    public void setUpTime(final EditText text) {
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                timeIsCorrect = enableSubmitIfReady(text);
                enableButton(contactButton);

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
                this.setTime(hora);
                correctFormat = true;
            }


        }
        return correctFormat;
    }

    public void enableButton(Button button) {
        if (dateIsSelected && timeIsCorrect && activityIsSelected) {
            button.setEnabled(true);
        }
    }

    private void sendInfo(){
        String treatment = this.concatenate(this.getActivities());
        String date = this.getDate();
        String time = this.getTime();

        Intent i = new Intent(Registration.this, ContactInformation.class);

        i.putExtra("treatment", treatment);
        i.putExtra("date", date);
        i.putExtra("time", time);

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
            activities.add(button.getText().toString());
        }
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getActivities() {
        return activities;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
