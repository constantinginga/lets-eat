package com.example.letseat.view.items;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewHolderIngredientItem extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView recipeName;
    ImageButton savedInNot;
    DatabaseReference savedReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public ViewHolderIngredientItem(View itemView) {
        super(itemView);
    }



}
