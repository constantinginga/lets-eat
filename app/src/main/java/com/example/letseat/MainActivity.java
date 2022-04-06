package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import repository.data.RecipeApi;
import repository.data.RecipeResponse;
import repository.data.RecipesResponse;
import repository.data.ServiceGenerator;
import repository.model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import viewmodel.RecipeViewModel;

public class MainActivity extends AppCompatActivity {

    private RecipeViewModel viewModel;
    private RecyclerView rv;
    private RecipeAdapter recipeAdapter;
    private TextView recipeName;
    private Button searchBtn;
    private EditText recipeIn;
    private NavController navController;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        recipeName = findViewById(R.id.recipeName);
        recipeIn = findViewById(R.id.searchRecipeInput);
        searchBtn = findViewById(R.id.searchRecipeBtn);
        rv = findViewById(R.id.rv);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Recipe> recipes = new ArrayList<>();
        BottomNavigationView

//        recipes.add(new Recipe(1, "Recipe1", "https://i1.sndcdn.com/artworks-000405558477-s8gy6t-t500x500.jpg"));
//        recipes.add(new Recipe(1, "Recipe1", "https://i1.sndcdn.com/artworks-000405558477-s8gy6t-t500x500.jpg"));
//        recipes.add(new Recipe(1, "Recipe1", "https://i1.sndcdn.com/artworks-000405558477-s8gy6t-t500x500.jpg"));
//        recipes.add(new Recipe(1, "Recipe1", "https://i1.sndcdn.com/artworks-000405558477-s8gy6t-t500x500.jpg"));
//        recipes.add(new Recipe(1, "Recipe1", "https://i1.sndcdn.com/artworks-000405558477-s8gy6t-t500x500.jpg"));
//        recipes.add(new Recipe(1, "Recipe1", "https://i1.sndcdn.com/artworks-000405558477-s8gy6t-t500x500.jpg"));

        viewModel.getAllRecipes(20);
        viewModel.getRecipes().observe(this, r -> {
            for (RecipeResponse re : r) {
                recipes.add(re.getRecipe());
                System.out.println(re.getRecipe().getId() + re.getRecipe().getName() + re.getRecipe().getImg());
            }
            rv.setAdapter(recipeAdapter);
        });

        recipeAdapter = new RecipeAdapter(recipes);

        searchBtn.setOnClickListener(v -> {
            if (recipeIn.getText() != null) {
                viewModel.searchForRecipe(recipeIn.getText().toString());
            }
        });

//        viewModel.getRecipes().observe(this, r -> {
//           recipeName.setText(r.get(0).getRecipe().getName());
//        });

        // something wrong with recycler view



        private void setBottomNavigationVisibility() {
            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                final int id = destination.getId();
                if (id == R.id.nav_pokedex || id == R.id.navigation_teams) {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                } else {
                    bottomNavigationView.setVisibility(View.GONE);
                }
            });
        }
    }
}