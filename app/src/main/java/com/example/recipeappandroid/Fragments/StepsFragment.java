package com.example.recipeappandroid.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipeappandroid.R;


public class StepsFragment extends Fragment {



    public StepsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_steps, container, false);
        TextView nameR= view.findViewById(R.id.nameR);
        TextView stepsR= view.findViewById(R.id.stepsR);

        Bundle b=this.getArguments();
        String name=b.getString("name");
        String steps=b.getString("steps");

        nameR.setText(name);
        stepsR.setText(steps);


        return view;
    }
}