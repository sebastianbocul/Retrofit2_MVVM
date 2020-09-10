package com.sebix.retrofit_mvvm.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sebix.retrofit_mvvm.AppExecutors;
import com.sebix.retrofit_mvvm.models.Recipe;
import com.sebix.retrofit_mvvm.requests.responses.RecipeSearchResponse;
import com.sebix.retrofit_mvvm.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.sebix.retrofit_mvvm.util.Constants.NETWORK_TIMEOUT;

public class RecipeApiClient {
    private static final String TAG = "RecipeApiClient";
    private static RecipeApiClient instance;
    private MutableLiveData<List<Recipe>> mRecipes = new MutableLiveData<>();
    private RetrieveRecipesRunnable mRetrieveRecipesRunnable;

    public static RecipeApiClient getInstance() {
        if (instance == null) {
            instance = new RecipeApiClient();
        }
        return instance;
    }

    public RecipeApiClient() {
    }

    public LiveData<List<Recipe>> getRecipes() {
        searchRecipesApi("chicken",1);
        return mRecipes;
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (mRetrieveRecipesRunnable != null) {
            mRetrieveRecipesRunnable = null;
        }
        mRetrieveRecipesRunnable = new RetrieveRecipesRunnable(query, pageNumber);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveRecipesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know its timed out;
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveRecipesRunnable implements Runnable {
        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveRecipesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Log.d("runLog", "run: ");
                Response response = getRecipes(query, pageNumber).execute();
                if (cancelRequest) {
                    Log.d("runLog", "cancelRequest: ");
                    return;
                }
                if (response.code() == 200) {
                    Log.d("runLog", "ifCode =200: ");
                    List<Recipe> list = new ArrayList<>(((RecipeSearchResponse) response.body()).getRecipes());
                    if (pageNumber == 1) {
                        Log.d("runLog", "ifpage==1: ");
                        mRecipes.postValue(list);
                    } else {
                        Log.d("runLog", "ifpage else: ");
                        List<Recipe> currentRecipes = mRecipes.getValue();
                        currentRecipes.addAll(list);
                        mRecipes.postValue(currentRecipes);
                    }
                } else {
                    Log.d("runLog", "ifcode else: ");
                    String error = response.errorBody().string();
                    Log.e(TAG, "run error: " + error);
                    mRecipes.postValue(null);
                }
            } catch (IOException e) {
                Log.d("runLog", "catch except: ");
                e.printStackTrace();
                mRecipes.postValue(null);
            }
        }

        private Call<RecipeSearchResponse> getRecipes(String query, int pageNumber) {
            return ServiceGenerator.getRecipeApi().searchRecipe(
                    Constants.API_KEY,
                    query,
                    String.valueOf(pageNumber)
            );
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling request");
            cancelRequest = true;
        }
    }
}