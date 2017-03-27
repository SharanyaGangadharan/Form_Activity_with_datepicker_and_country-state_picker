package com.example.shara.assignment2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StateFragment extends ListFragment implements AdapterView.OnItemClickListener{

    ArrayList<String> stateList = new ArrayList<String>();
    String[] states;
    String state=null;
    private String country=null;

    public StateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            country = getArguments().getString("country");
            Log.d("rew", country);
            try {
                InputStream statesFile = getActivity().getAssets().open(country);
                BufferedReader x = new BufferedReader(new InputStreamReader(statesFile));
                String state = null;
                while ((state = x.readLine()) != null) {
                    stateList.add(state);
                }
                states = new String[stateList.size()];
                states = stateList.toArray(states);
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, states);
                setListAdapter(adapter);
                getListView().setOnItemClickListener(this);
            } catch (IOException e) {
                Log.e("rew", "read Error", e);
            }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CountryActivity.frag="State";
        Log.i("rew", "position of click " + position );
        Log.i("rew", "id of click " + id );
        state = states[position];
        Log.d("rew", state);
        CountryActivity ca = (CountryActivity)getActivity();
        ca.Country(country);
        ca.State(state);
    }
}
