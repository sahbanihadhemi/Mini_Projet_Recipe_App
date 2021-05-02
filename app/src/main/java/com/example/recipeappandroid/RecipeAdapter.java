package com.example.recipeappandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeappandroid.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe>listData;

    Context context;
    public RecipeAdapter(List<Recipe> listData, RecyclerViewClickListener listener , Context context) {
        this.listData = listData;
        this.listener=listener;
        this.context=context;
    }
    private RecyclerViewClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipe,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe ld=listData.get(position);
        holder.txtname.setText(ld.getRecipe_name());

        if(context!=null){

       Glide.with(context).load(ld.getImageUri()).dontAnimate().into(holder.image);
        }}

    @Override
    public int getItemCount() {
        return listData.size();
    }
     public  void  filterList(ArrayList<Recipe> filtredList)
     {
         listData= filtredList;
         notifyDataSetChanged();
     }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtstep,txtname,txtoverview;
        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.recipe_name);
            image=(ImageView)itemView.findViewById(R.id.image) ;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(itemView,getAdapterPosition());
        }
    }

    public interface  RecyclerViewClickListener{

        void onClick(View v, int position);
    }
}