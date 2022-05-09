package com.example.letseat.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letseat.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.letseat.repository.model.Recipe;
import com.example.letseat.repository.OnClickListener;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private final ArrayList<Recipe> recipes;
    private final OnClickListener listener;

    public RecipeAdapter(ArrayList<Recipe> recipes, OnClickListener listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        holder.name.setText(recipes.get(position).getName());
        Picasso.get().load(recipes.get(position).getImg()).into(holder.img);
        holder.img.setClipToOutline(true);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipeName);
            img = itemView.findViewById(R.id.recipeImg);
            itemView.setOnClickListener(v -> listener.onClick(recipes.get(getBindingAdapterPosition())));
        }
    }
}
