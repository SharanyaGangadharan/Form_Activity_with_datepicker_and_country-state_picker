package com.example.shara.assignment2;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class CountryActivity extends FragmentActivity{

    String country, state;
    public static String frag=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        CountryFragment cf = new CountryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_holder, cf, "Country");
        fragmentTransaction.commit();

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(frag.equals("State")) {
                    StateFragment sf = new StateFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.fragment_holder, sf, "State");
                    fragmentTransaction.commit();

                }
            }
        };
    }

    public void Country(String country)
    {
        this.country = country;
    }

    public void State(String state)
    {
        this.state = state;
    }

    public void done(View button)
    {
        if(frag=="Country") {finish();}
        else if(frag=="State"){
        Intent toPassBack = getIntent();
        toPassBack.putExtra("country",country);
        toPassBack.putExtra("state",state);
        setResult(1, toPassBack);
        finish();}
        else{finish();}
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    public void cancel(View button)
    {
        finish();
    }
}
