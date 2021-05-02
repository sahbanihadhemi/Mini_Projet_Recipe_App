package com.example.recipeappandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.recipeappandroid.R;
import com.example.recipeappandroid.RecipeAdapter;
import com.example.recipeappandroid.models.Recipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Recipe> listData;
    private RecyclerView rv;
    private RecipeAdapter adapter;
    private RecipeAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filtre(s.toString());

            }
        });

        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();


        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Recipe");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listData.clear();
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        Recipe l=npsnapshot.getValue(Recipe.class);
                        listData.add(l);
                    }


                     listener= new RecipeAdapter.RecyclerViewClickListener() {
                         @Override
                         public void onClick(View v, int position) {
                             Intent intent= new Intent(getApplicationContext(),RecipeDetails.class);
                             intent.putExtra("RecipeName",listData.get(position).getRecipe_name());
                             intent.putExtra("RecipeSteps",listData.get(position).getRecipe_steps());
                             intent.putExtra("RecipeOverview",listData.get(position).getRecipe_overview());

                             startActivity(intent);


                         }
                     };
                    adapter=new RecipeAdapter(listData,listener,MainActivity.this);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void filtre(String text)
    {
        ArrayList<Recipe> filtredList = new ArrayList<>();
        for(Recipe r: listData)
        {
            if(r.getRecipe_name().toLowerCase().contains(text.toLowerCase()));
            {
            filtredList.add(r);}
        }
       adapter.filterList(filtredList);
    }
    public void addRecipe(View view) {

        Intent intent= new Intent(getApplicationContext(),AddRecipe.class);
        startActivity(intent);
    }
}
