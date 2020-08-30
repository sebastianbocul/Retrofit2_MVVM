package com.sebix.retrofit_mvvm.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sebix.retrofit_mvvm.models.Recipe;

public class RecipeResponse {
    @SerializedName("recipe")
    @Expose()
    private Recipe recipe;
    public Recipe getRecipe(){
        return recipe;
    }

}
