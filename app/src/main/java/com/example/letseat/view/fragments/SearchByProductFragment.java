package com.example.letseat.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letseat.R;
import com.example.letseat.view.RecipeAdapter;

import java.util.ArrayList;

import com.example.letseat.repository.data.RecipeResponse;
import com.example.letseat.repository.model.Recipe;
import com.example.letseat.viewmodel.RecipeViewModel;

public class SearchByProductFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_by_product, container, false);
        RecipeViewModel viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        EditText addIngredients = view.findViewById(R.id.add_ingredients);
        Button addIngredientsButton = view.findViewById(R.id.add_ingredients_button);
        Button findRecipes = view.findViewById(R.id.find_recipes);
        RecyclerView rv = view.findViewById(R.id.rv);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ArrayList<String> ingr = new ArrayList<>();
        ArrayList<Recipe> recipes = new ArrayList<>();

        addIngredientsButton.setOnClickListener(r -> {
            ingr.add(addIngredients.getText().toString());
        });

        findRecipes.setOnClickListener(r -> {
            viewModel.getRecipesByIngredients(ingr);
        });

        viewModel.getRecipes().observe(getViewLifecycleOwner(), r -> {
            for (RecipeResponse re : r) {
                recipes.add(re.getRecipe());
                System.out.println(re.getRecipe().getId() + re.getRecipe().getName() + re.getRecipe().getImg());
            }
            RecipeAdapter adapter = new RecipeAdapter(recipes, v -> {});
            rv.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        });
        return view;
    }
}
