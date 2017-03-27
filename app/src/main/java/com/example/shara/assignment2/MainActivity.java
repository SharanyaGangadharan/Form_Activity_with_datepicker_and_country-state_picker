package com.example.shara.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    int daySelect=0,yearSelect=0,monthSelect=0;
    String fname, lname,email,country_state, dob, age,phone,country, state;

    EditText fnameText, lnameText, ageText, emailText, phoneNo;
    TextView bdateText, csText;

    public static final String PREFS = "Person Profile";
    private static final int INTENT_EXAMPLE_REQUEST = 123;
    private static final int INTENT_SAMPLE_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fnameText = (EditText) findViewById(R.id.fname);
        lnameText = (EditText) findViewById(R.id.lname);
        ageText = (EditText) findViewById(R.id.age);
        emailText = (EditText) findViewById(R.id.email);
        phoneNo = (EditText) findViewById(R.id.phone);
        bdateText = (TextView) findViewById(R.id.birthdate);
        csText = (TextView) findViewById(R.id.csText);
        dob = bdateText.getText().toString();
        country_state = csText.getText().toString();

        SharedPreferences prefs = getSharedPreferences(PREFS, 0);
        fnameText.setText(prefs.getString("fname",""));
        lnameText.setText(prefs.getString("lname", ""));
        ageText.setText(prefs.getString("age", ""));
        emailText.setText(prefs.getString("email", ""));
        phoneNo.setText(prefs.getString("phone", ""));
        bdateText.setText(prefs.getString("dob", ""));
        csText.setText(prefs.getString("cs", ""));


        fnameText.requestFocus();
        fname = fnameText.getText().toString();
        lname = lnameText.getText().toString();
        age = ageText.getText().toString();
        email = emailText.getText().toString();
        phone = phoneNo.getText().toString();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTENT_EXAMPLE_REQUEST) {
            switch (resultCode) {
                case RESULT_OK:
                    daySelect = data.getIntExtra("daySelect", -1);
                    monthSelect = data.getIntExtra("monthSelect", -1);
                    yearSelect = data.getIntExtra("yearSelect", -1);
                    country = data.getStringExtra("country");
                    state = data.getStringExtra("state");
                    bdateText.setText(new StringBuilder()
                            .append(monthSelect + 1).append("/").append(daySelect).append("/")
                            .append(yearSelect).append(" "));
                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
        if(requestCode == INTENT_SAMPLE_REQUEST){
            switch (resultCode) {
                case 1:
                    country = data.getStringExtra("country");
                    state = data.getStringExtra("state");
                    csText.setText(new StringBuilder()
                            .append(country).append("/").append(state).append(" "));
                    Log.d("rew",country);
                    Log.d("rew",state);
                    break;
                case 0:
                    break;
            }
        }
    }

    public void set(View button) {
        Intent go = new Intent(this,DateActivity.class);
        go.putExtra("daySelect", daySelect);
        go.putExtra("monthSelect", monthSelect);
        go.putExtra("yearSelect", yearSelect);
        startActivityForResult(go, INTENT_EXAMPLE_REQUEST);
    }

    public void setCS(View button) {
        Intent go1 = new Intent(this,CountryActivity.class);
        go1.putExtra("country", country);
        go1.putExtra("state", state);
        startActivityForResult(go1, INTENT_SAMPLE_REQUEST);
    }

    public void submit(View button) {
        SharedPreferences pref = getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("fname",fnameText.getText().toString());
        editor.putString("lname", lnameText.getText().toString());
        editor.putString("age",ageText.getText().toString());
        editor.putString("email", emailText.getText().toString());
        editor.putString("phone",phoneNo.getText().toString());
        editor.putString("dob", bdateText.getText().toString());
        editor.putString("cs", csText.getText().toString());
        editor.commit();
    }
}
