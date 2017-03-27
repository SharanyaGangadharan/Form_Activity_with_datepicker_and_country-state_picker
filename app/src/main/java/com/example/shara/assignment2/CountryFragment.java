package com.example.shara.assignment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
public class CountryFragment extends ListFragment implements AdapterView.OnItemClickListener{


    ArrayList<String> cList = new ArrayList<String>();
    String[] countries;

    public CountryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CountryActivity.frag="Country";
        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            try {
            InputStream countriesFile = getActivity().getAssets().open("countries");
            BufferedReader in = new BufferedReader( new InputStreamReader(countriesFile));
            String country = null;
            while((country=in.readLine())!=null){
                cList.add(country);
            }
            countries=new String[cList.size()];
            countries=cList.toArray(countries);
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, countries);
            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);
        } catch (IOException e) {
            Log.e("rew", "read Error", e);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Log.i("rew", "position of click " + position);
        Log.i("rew", "id of click " + id);
        String countryName = countries[position];
        FragmentManager fragments = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragments.beginTransaction();
        StateFragment fragment = new StateFragment();
        Bundle var = new Bundle();
        var.putString("country",countryName);
        fragment.setArguments(var);
        fragmentTransaction.replace(R.id.fragment_holder, fragment);
        fragmentTransaction.addToBackStack("frags");
        fragmentTransaction.commit();
    }
}