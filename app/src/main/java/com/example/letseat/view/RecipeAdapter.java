package com.example.letseat.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letseat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.letseat.repository.model.Recipe;
import com.example.letseat.repository.OnClickListener;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private final ArrayList<Recipe> recipes;
    private OnClickListener listener;
    private String postKey;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

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
        String imgUrl = recipes.get(position).getImg();
        postKey =  String.valueOf(position);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView img;
        private final ImageButton btn;
        private Boolean savedChecker = false;


        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipeName);
            img = itemView.findViewById(R.id.recipeImg);
            btn = itemView.findViewById(R.id.savedButton);
            itemView.setOnClickListener(v -> listener.onClick(recipes.get(getBindingAdapterPosition())));

            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String currentUserId = firebaseUser.getUid();

            // savedReference = database.getReference("saved");
            DatabaseReference savedReferenceList = database.getReference("savedList").child(currentUserId);

            btn.setOnClickListener(view -> {
                savedChecker = true;
                btn.setImageResource(R.drawable.ic_turned_in);
                database.getReference().child("users").child(currentUserId).child("saved_recipes").child(postKey).setValue(name.getText().toString());
            });

        }
    }
}
