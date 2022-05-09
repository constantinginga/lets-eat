package com.example.letseat.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letseat.R;
import com.example.letseat.repository.data.ComponentResponse;
import com.example.letseat.repository.data.SectionResponse;
import com.example.letseat.repository.model.Ingredient;
import com.example.letseat.repository.model.Recipe;
import com.example.letseat.view.IngredientAdapter;
import com.example.letseat.viewmodel.RecipeViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class RecipeActivity extends AppCompatActivity {

    private RecipeViewModel viewModel;
    private int recipeId;
    private TextView recipeName, servings, ingredients;
    private ImageView recipeImg;
    private RecyclerView rv;
    private ExtendedFloatingActionButton fab;
    private ArrayList<Ingredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        init();
        viewModel.getRecipeInfo(recipeId);
        viewModel.getRecipeInfo().observe(this, r -> {
            Recipe recipe = r.getRecipe();
            Resources res = getResources();
            recipeName.setText(recipe.getName());
            Picasso.get().load(recipe.getImg()).into(recipeImg);
            recipeImg.setClipToOutline(true);
            servings.setText(String.format(res.getString(R.string.detailed_serving_size), recipe.getNum_servings()));
            ingredientList = new ArrayList<>();
            for (SectionResponse sec : r.getSections()) {
                for (ComponentResponse comp : sec.getComponents()) {
                    ingredientList.add(comp.getIngredient());
                }
            }
            IngredientAdapter ingredientAdapter = new IngredientAdapter(ingredientList);
            rv.setAdapter(ingredientAdapter);
            ingredients.setText(String.format(res.getString(R.string.detailed_ingredients), ingredientList.size()));
        });



    }

    private void init() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        setTitle(getString(R.string.recipe_details));
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        Intent intent = this.getIntent();
        Recipe recipe = (Recipe) intent.getExtras().getSerializable("recipe");
        if (recipe != null)
            recipeId = recipe.getId();
        recipeName = findViewById(R.id.detailedRecipeName);
        recipeImg = findViewById(R.id.detailedRecipeImg);
        servings = findViewById(R.id.servingSize);
        ingredients = findViewById(R.id.detailedRecipeIngredients);
        rv = findViewById(R.id.ingredientsRv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.recipeFab);
    }
}