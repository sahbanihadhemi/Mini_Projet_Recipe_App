package com.example.recipeappandroid.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipeappandroid.R;


public class OverviewFragment extends Fragment {


    public OverviewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_overview, container, false);
        TextView name= view.findViewById(R.id.nameR);
        TextView overview= view.findViewById(R.id.overviewR);

        Bundle b=this.getArguments();
        String nameR=b.getString("name");
        String overviewR=b.getString("overview");

        name.setText(nameR);
        overview.setText(overviewR);


        return view;
    }
}