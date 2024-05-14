package com.example.projetquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<CategoryModel> categoryList; // La liste des catégories
    private OnItemClickListener listener; // L'interface pour gérer les clics sur les éléments

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }
    // Interface pour gérer les clics sur les éléments de la liste
    public interface OnItemClickListener {
        void onItemClick(CategoryModel category);
    }
// Méthode pour définir le gestionnaire de clics sur les éléments de la liste
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    // Crée une nouvelle vue (appelée par le layout manager)
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }
    // Remplace le contenu de la vue (appelée par le layout manager)
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel category = categoryList.get(position);   // Obtient la catégorie à la position spécifiée
        holder.categoryName.setText(category.getName());
        holder.categoryImage.setImageDrawable(category.getImage());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImage;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category);
            categoryImage = itemView.findViewById(R.id.imgQuiz);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(categoryList.get(position));
                    }
                }
            });
        }
    }
}
