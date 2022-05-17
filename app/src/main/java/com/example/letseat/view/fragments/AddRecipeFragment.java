package com.example.letseat.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.letseat.R;
import com.example.letseat.repository.model.Recipe;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecipeFragment extends Fragment {

    private View view;
    private TextInputLayout recipeName, recipeUrl;
    private Button nextBtn;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        init();

            nextBtn.setOnClickListener(v -> {
                if (recipeName.getEditText() != null && recipeUrl.getEditText() != null) {
                    createRecipe(recipeName.getEditText().getText().toString(), recipeUrl.getEditText().getText().toString(), container);
                }
            });
        return view;
    }

    private void init() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recipeName = view.findViewById(R.id.addRecipeName);
        recipeUrl = view.findViewById(R.id.addRecipeURL);
        nextBtn = view.findViewById(R.id.addRecipeBtn);
    }

    private void createRecipe(String name, String url, ViewGroup container) {
        if (name != null && url != null && !name.equals("") && !url.equals("")) {
            Recipe recipe = new Recipe(name, url);
            mDatabase.child("recipes").child(name.toLowerCase()).setValue(recipe);
            getParentFragmentManager().beginTransaction().replace(container.getId(), new MainPageFragment()).commit();
        }
    }
}