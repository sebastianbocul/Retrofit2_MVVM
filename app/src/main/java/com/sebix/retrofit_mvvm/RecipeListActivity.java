package com.sebix.retrofit_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sebix.retrofit_mvvm.models.Recipe;
import com.sebix.retrofit_mvvm.requests.RecipeApi;
import com.sebix.retrofit_mvvm.requests.ServiceGenerator;
import com.sebix.retrofit_mvvm.requests.responses.RecipeSearchResponse;
import com.sebix.retrofit_mvvm.util.Constants;
import com.sebix.retrofit_mvvm.viewmodels.RecipesListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {
    private static final String TAG = "RecipeListActivity";
    private RecipesListViewModel mRecipesListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipesListViewModel = new ViewModelProvider(this).get(RecipesListViewModel.class);


        subscribeObservers();

        findViewById(R.id.show_progress_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
                if(mProgressBar.getVisibility()==View.VISIBLE){
                    showProgressBar(false);
                }else {
                    showProgressBar(true);
                }
            }
        });
    }

    private void subscribeObservers(){
        mRecipesListViewModel.getmRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
            }
        });
    }


    private void testRetrofitRequest(){
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();


//        Call<RecipeSearchResponse> responseCall = recipeApi.searchRecipe(
//                Constants.API_KEY,
//                "chicken breast",
//                "1"
//                );
//
//        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
//            @Override
//            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
//                Log.d(TAG, "onResponse: " + response.toString());
//                if(response.code()==200){
//                    Log.d(TAG, "onResponse: " + response.body().toString());
//                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
//                    for(Recipe r:recipes){
//                        Log.d(TAG, "items from list:" + r.getTitle());
//                    }
//                }else {
//                    try {
//                        Log.d(TAG, "onResponse: " + response.errorBody().string());
//                    }catch (Exception e){
//                        e.getStackTrace();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
//                Log.d(TAG, "onfailure");
//
//            }
//        });
    }
}