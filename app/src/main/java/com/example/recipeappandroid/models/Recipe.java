package com.example.recipeappandroid.models;

public class Recipe {
    String recipe_name;
    String recipe_steps;
    String recipe_overview;
    String imageUri;



    public Recipe(String recipe_name, String recipe_steps,String recipe_overview,String imageUri) {
        this.recipe_name = recipe_name;
        this.recipe_steps = recipe_steps;
        this.recipe_overview=recipe_overview;
        this.imageUri=imageUri;
    }
    public Recipe() {
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getRecipe_name() {
        return recipe_name;
    }


    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_steps() {
        return recipe_steps;
    }

    public void setRecipe_steps(String recipe_steps) {
        this.recipe_steps = recipe_steps;
    }

    public String getRecipe_overview() {
        return recipe_overview;
    }

    public void setRecipe_overview(String recipe_overview) {
        this.recipe_overview = recipe_overview;
    }

}
