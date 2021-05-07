package com.example.recipeappandroid.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.fonts.FontFamily;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.recipeappandroid.R;
import com.example.recipeappandroid.models.Recipe;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class AddRecipe extends AppCompatActivity {
    ImageView imageView;
    EditText name;
    EditText steps;
    EditText overview;
    Uri uri;
    String imageUri;
    DatabaseReference ref;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView=findViewById(R.id.recipeImage);
        overview=findViewById(R.id.Recipe_overview);
        name=findViewById(R.id.Recipe_name);
        steps=findViewById(R.id.Recipe_steps);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
        }
        else{
            Toast.makeText(this,"permission allowed",Toast.LENGTH_SHORT).show();

        }


    }

    public void pickImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            uri=data.getData();
            imageView.setImageURI(uri);
        }
        else{
            Toast.makeText(this,"pick an image please",Toast.LENGTH_SHORT).show();
        }
    }



    public void uploadImage()
    {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Recipe").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               Task task = taskSnapshot.getStorage().getDownloadUrl();

               while(!task.isSuccessful());
               Uri uriImage= (Uri) task.getResult();
             imageUri=uriImage.toString();
                uploadRecipe();


            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddRecipe.this,"error"+e.getMessage(),Toast.LENGTH_SHORT).show();;
            }
        });
    }
    public void addRecipe(View view) {
       uploadImage();
    }



    public void uploadRecipe(){
        String nameRecipe=name.getText().toString().trim();
        String stepRecipe=steps.getText().toString().trim();
        String overviewRecipe=overview.getText().toString().trim();
        Recipe recipe =new Recipe(nameRecipe,stepRecipe,overviewRecipe,imageUri);
        ref= FirebaseDatabase.getInstance().getReference("Recipe");
         ref.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if(snapshot.exists())
                 {
                     id= (int) snapshot.getChildrenCount();}
                 else{}

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
        FirebaseDatabase.getInstance().getReference("Recipe").child(String.valueOf(id))
       .setValue(recipe).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               Toast.makeText(AddRecipe.this,"Success",Toast.LENGTH_SHORT).show();

           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(AddRecipe.this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();

           }
       });
    }
}
