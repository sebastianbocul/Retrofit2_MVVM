package com.sebix.retrofit_mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RecipeListActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_progress_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mProgressBar.getVisibility()==View.VISIBLE){
                    showProgressBar(false);
                }else {
                    showProgressBar(true);
                }
            }
        });

    }
}