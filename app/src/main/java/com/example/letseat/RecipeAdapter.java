package com.example.letseat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import repository.model.Ingredient;
import repository.model.Recipe;
import view.RecyclerViewClickListener;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private ArrayList<Recipe> recipes;
    RecyclerViewClickListener listener;

    public RecipeAdapter(ArrayList<Recipe> recipes, RecyclerViewClickListener listener) {
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
        // holder.img.setImageBitmap(getImageBitmap(recipes.get(position).getImg()));
        Picasso.get().load(recipes.get(position).getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;
        private final ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipeName);
            img = itemView.findViewById(R.id.recipeImg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.recyclerViewListClicked(itemView, this.getLayoutPosition());
        }
    }
}
