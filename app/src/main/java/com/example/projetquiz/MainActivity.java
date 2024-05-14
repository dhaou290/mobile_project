package com.example.projetquiz;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetquiz.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<CategoryModel> categories;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//setSupportActionBar(binding.toolbar);
        // Initialize RecyclerView and LayoutManager
        binding.categoryList.setLayoutManager(new GridLayoutManager(this, 2));

        // Initialize data
        categories = new ArrayList<>();
        loadCategories();

        // Initialize adapter and set it to RecyclerView
        adapter = new CategoryAdapter(this, categories);
        binding.categoryList.setAdapter(adapter);
    }

    private void loadCategories() {
        Drawable androidImage = ContextCompat.getDrawable(this, R.drawable.android);
        Drawable javaImage = ContextCompat.getDrawable(this, R.drawable.java);
        Drawable angularImage = ContextCompat.getDrawable(this, R.drawable.angular);
        Drawable bigDataImage = ContextCompat.getDrawable(this, R.drawable.bigdata);
        Drawable pythonImage = ContextCompat.getDrawable(this, R.drawable.python);
        Drawable phpImage = ContextCompat.getDrawable(this, R.drawable.php);


        categories.add(new CategoryModel("1", "Android", androidImage));
        categories.add(new CategoryModel("2", "Java", javaImage));
        categories.add(new CategoryModel("3", "Angular", angularImage));
        categories.add(new CategoryModel("4", "Big Data", bigDataImage));
        categories.add(new CategoryModel("5", "python", pythonImage));
        categories.add(new CategoryModel("6", "Android", androidImage));
        categories.add(new CategoryModel("", "php", phpImage));
        categories.add(new CategoryModel("", "Big Data", bigDataImage));
        categories.add(new CategoryModel("", "python", pythonImage));
        categories.add(new CategoryModel("", "Android", androidImage));
        categories.add(new CategoryModel("", "php", phpImage));
    }
}



