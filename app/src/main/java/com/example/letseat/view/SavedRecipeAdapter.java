package com.example.letseat.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letseat.R;

import java.util.List;

public class SavedRecipeAdapter extends RecyclerView.Adapter<SavedRecipeAdapter.MyHolder> {

    private final Context context;
    private final List<String> recipeList;

    // constructor
    public SavedRecipeAdapter(Context context, List<String> recipeList){
        this.context = context;
        this.recipeList = recipeList;
}

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        //get data
        String recipeName = recipeList.get(position);

        //set data
        holder.recipeName.setText(recipeName);

        //handle item click
        holder.itemView.setOnClickListener(view -> Toast.makeText(context, ""+recipeName, Toast.LENGTH_SHORT).show());

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView recipeName;

        public MyHolder(View itemView) {
            super(itemView);

            //init views
            recipeName = itemView.findViewById(R.id.recipeRowName);
        }
    }
}
