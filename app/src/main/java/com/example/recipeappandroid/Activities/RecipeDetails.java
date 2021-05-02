package com.example.recipeappandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.recipeappandroid.PagerAdapter;
import com.example.recipeappandroid.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class RecipeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
         String recipeName= intent.getStringExtra("RecipeName");
         String recipeSteps= intent.getStringExtra("RecipeSteps");
        String recipeOverview= intent.getStringExtra("RecipeOverview");

        TabLayout tabLayout =findViewById(R.id.recipe);
        TabItem overview= findViewById(R.id.overview);


        TabItem steps= findViewById(R.id.steps);
        final ViewPager viewPager= findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),recipeName,recipeSteps,recipeOverview);
        viewPager.setAdapter(pagerAdapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());




            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}