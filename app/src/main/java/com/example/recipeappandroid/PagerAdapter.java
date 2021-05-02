package com.example.recipeappandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.recipeappandroid.Fragments.OverviewFragment;
import com.example.recipeappandroid.Fragments.StepsFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;
    private String recipeName;
    private String recipeSteps;
    private String recipeOverview;

    public PagerAdapter(FragmentManager fm, int numberOfTabs,String recipeName,String RecipeSteps, String RecipeOverview)
    {
        super(fm);
        this.numberOfTabs=numberOfTabs;
        this.recipeName=recipeName;
        this.recipeSteps=RecipeSteps;
        this.recipeOverview=RecipeOverview;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch(position)
               {
                   case 0:

                        OverviewFragment overviewFragment= new OverviewFragment();
                       Bundle b = new Bundle();
                       b.putString("name", recipeName);
                       b.putString("overview", recipeOverview);
                       overviewFragment.setArguments(b);
                       return overviewFragment;


                   case 1:
                       StepsFragment stepsFragment=new StepsFragment();
                       Bundle b1= new Bundle();
                       b1.putString("name", recipeName);

                       b1.putString("steps",recipeSteps);
                       stepsFragment.setArguments(b1);
                       return  stepsFragment;

                   default:
                       return null;
               }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
