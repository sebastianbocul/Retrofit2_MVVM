package com.sebix.retrofit_mvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sebix.retrofit_mvvm.models.Recipe;
import com.sebix.retrofit_mvvm.repositories.RecipeRepository;

import java.util.List;

public class RecipesListViewModel extends ViewModel {
    private RecipeRepository mRecipeRepository;
    private boolean mIsViewingRecipes;


    private boolean mIsPerformingQuery;

    public RecipesListViewModel() {
        mIsViewingRecipes = false;
        mIsPerformingQuery=false;
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getmRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        mIsViewingRecipes = true;
        mIsPerformingQuery=true;
        mRecipeRepository.searchRecipesApi(query, pageNumber);
    }
    public void searchNextPage(){
        if(!mIsPerformingQuery&&mIsViewingRecipes){
            mRecipeRepository.searchNextPage();
        }
    }

    public boolean ismIsViewingRecipes() {
        return mIsViewingRecipes;
    }

    public void setmIsViewingRecipes(boolean mIsViewingRecipes) {
        this.mIsViewingRecipes = mIsViewingRecipes;
    }

    public boolean onBackPressed(){
        if(mIsPerformingQuery){
            mRecipeRepository.cancelRequest();
        }
        if(mIsViewingRecipes){
            mIsViewingRecipes=false;
            return false;
        }
        return true;
    }

    public boolean ismIsPerformingQuery() {
        return mIsPerformingQuery;
    }

    public void setmIsPerformingQuery(boolean mIsPerformingQuery) {
        this.mIsPerformingQuery = mIsPerformingQuery;
    }

}
