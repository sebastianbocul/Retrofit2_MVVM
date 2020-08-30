package com.sebix.retrofit_mvvm;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public abstract class BaseActivity extends AppCompatActivity {
    public ProgressBar mProgressBar;

    @Override
    public void setContentView(int layoutResID) {
        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base,null);
        mProgressBar = constraintLayout.findViewById(R.id.progress_bar);
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.acitivity_content);
        getLayoutInflater().inflate(layoutResID,frameLayout,true);
        super.setContentView(constraintLayout);
    }

    public void showProgressBar(boolean show){
        mProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }
}
