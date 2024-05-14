package com.example.projetquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.projetquiz.databinding.ActivityUserBinding;

import java.util.ArrayList;

public class userActivity extends AppCompatActivity implements CategoryAdapter.OnItemClickListener {

    private ActivityUserBinding binding;
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter adapter;
    private ArrayList<CategoryModel> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialise RecyclerView
        categoryRecyclerView = binding.categoryList;
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Initialise la liste de catégories
        categories = new ArrayList<>();

        // Initialise l'adaptateur et le lie au RecyclerView
        adapter = new CategoryAdapter(this, categories);
        categoryRecyclerView.setAdapter(adapter);

        // Charge les catégories
        loadCategories();

        // Définir cette activité comme écouteur de clic pour l'adaptateur
        adapter.setOnItemClickListener(this);
    }

    private void loadCategories() {
        // Ajoutez ici la logique pour charger les catégories depuis la base de données ou tout autre emplacement
        // Par exemple :
        categories.add(new CategoryModel("1", "Android", getResources().getDrawable(R.drawable.android)));
        categories.add(new CategoryModel("2", "Java", getResources().getDrawable(R.drawable.java)));
        categories.add(new CategoryModel("3", "Angular", getResources().getDrawable(R.drawable.angular)));
        categories.add(new CategoryModel("4", "Big Data", getResources().getDrawable(R.drawable.bigdata)));
        categories.add(new CategoryModel("5", "Python", getResources().getDrawable(R.drawable.python)));
        categories.add(new CategoryModel("6", "Php", getResources().getDrawable(R.drawable.php)));

        // Notifie l'adaptateur des changements
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(CategoryModel category) {
        // Rediriger vers quizActivity lorsqu'un élément est cliqué
        Intent intent = new Intent(userActivity.this, quizActivity.class);
        startActivity(intent);
    }
}
