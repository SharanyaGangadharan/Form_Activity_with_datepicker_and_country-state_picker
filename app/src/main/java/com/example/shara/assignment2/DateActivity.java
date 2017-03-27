package com.example.shara.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import static com.example.shara.assignment2.R.id.simpleDatePicker;

public class DateActivity extends AppCompatActivity {

    DatePicker datePicker;
    private int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        datePicker = (DatePicker)findViewById(R.id.simpleDatePicker);

        Bundle personData = getIntent().getExtras();
        day = personData.getInt("daySelect");
        month = personData.getInt("monthSelect");
        year = personData.getInt("yearSelect");
    }

    public void done(View button) {
        Intent toPassBack = getIntent();
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth();
        year = datePicker.getYear();
        toPassBack.putExtra("daySelect",day);
        toPassBack.putExtra("monthSelect",month);
        toPassBack.putExtra("yearSelect",year);
        setResult(RESULT_OK, toPassBack);
        finish();
    }

    public void cancel(View button) {
        Intent toPassBack = getIntent();
        toPassBack.putExtra("daySelect",day);
        toPassBack.putExtra("monthSelect",month);
        toPassBack.putExtra("yearSelect",year);
        setResult(RESULT_CANCELED, toPassBack);
        finish();
    }

}
