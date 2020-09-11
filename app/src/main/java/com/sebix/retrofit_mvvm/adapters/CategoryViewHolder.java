package com.sebix.retrofit_mvvm.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sebix.retrofit_mvvm.R;

import de.hdodenhof.circleimageview.CircleImageView;

class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    OnRecipeListener listener;
    TextView categoryTitle;
    CircleImageView categoryImage;

    public CategoryViewHolder(@NonNull View itemView, OnRecipeListener listener) {
        super(itemView);
        this.listener = listener;
        categoryImage = itemView.findViewById(R.id.category_image);
        categoryTitle = itemView.findViewById(R.id.category_title);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onCategoryClick(categoryTitle.getText().toString());
    }
}
