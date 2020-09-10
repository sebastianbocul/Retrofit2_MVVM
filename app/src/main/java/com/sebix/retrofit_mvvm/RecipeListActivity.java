package com.sebix.retrofit_mvvm;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sebix.retrofit_mvvm.adapters.OnRecipeListener;
import com.sebix.retrofit_mvvm.adapters.RecipeRecyclerAdapter;
import com.sebix.retrofit_mvvm.models.Recipe;
import com.sebix.retrofit_mvvm.viewmodels.RecipesListViewModel;

import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {
    private static final String TAG = "RecipeListActivity";
    private RecipesListViewModel mRecipesListViewModel;
    private RecyclerView mRecyclerView;
    private RecipeRecyclerAdapter mRecipeRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecipesListViewModel = new ViewModelProvider(this).get(RecipesListViewModel.class);
        mRecyclerView = findViewById(R.id.list_recycler_view);
        initRecyclerView();
        initSearchView();
        subscribeObservers();
    }

    private void subscribeObservers() {
        Log.d("subscribeObservers", "subscribeObservers: " + mRecipesListViewModel);
        mRecipesListViewModel.getmRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                Log.d("subscribeObservers", "getRecipes Observer: " + recipes);
                if (recipes != null) {
                    mRecipeRecyclerAdapter.setRecipes(recipes);
                }
            }
        });
    }

    private void initRecyclerView() {
        mRecipeRecyclerAdapter = new RecipeRecyclerAdapter(this);
        mRecyclerView.setAdapter(mRecipeRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initSearchView() {
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mRecipesListViewModel.searchRecipesApi(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onRecipeClick(int position) {
    }

    @Override
    public void onCategoryClick(String category) {
    }
}