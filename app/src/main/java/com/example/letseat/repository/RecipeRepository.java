package com.example.letseat.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import com.example.letseat.repository.data.RecipeApi;
import com.example.letseat.repository.data.RecipeResponse;
import com.example.letseat.repository.data.RecipesResponse;
import com.example.letseat.repository.data.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private static RecipeRepository instance;
    private final MutableLiveData<ArrayList<RecipeResponse>> recipes;
    private final MutableLiveData<RecipeResponse> recipeInfo;
    private final ArrayList<RecipeResponse> cachedRecipes;
    private final ArrayList<RecipeResponse> searchedRecipes;

    private RecipeRepository() {
        recipes = new MutableLiveData<>();
        recipeInfo = new MutableLiveData<>();
        cachedRecipes = new ArrayList<>();
        searchedRecipes = new ArrayList<>();
    }

    public static synchronized RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public void searchForRecipe(String r) {

        searchedRecipes.clear();

        // if the search term is empty, show all recipes
        if (r == null || r.equals("") || recipes.getValue() == null) {
            recipes.setValue(cachedRecipes);
            return;
        }

        if (recipes.getValue().size() != 0) {
            for (RecipeResponse recipe : recipes.getValue()) {
                if (recipe.getRecipe().getName().toLowerCase().contains(r.toLowerCase()))
                    searchedRecipes.add(recipe);
            }
        } else {
            for (RecipeResponse recipe : cachedRecipes) {
                if (recipe.getRecipe().getName().toLowerCase().contains(r.toLowerCase()))
                    searchedRecipes.add(recipe);
            }
        }

        // save the recipe list
        if (cachedRecipes.size() == 0) {
            cachedRecipes.addAll(recipes.getValue());
        }

        recipes.setValue(searchedRecipes);
    }

    public LiveData<ArrayList<RecipeResponse>> getRecipes() {
        return recipes;
    }

    public MutableLiveData<RecipeResponse> getRecipeInfo() {
        return recipeInfo;
    }

    public void getAllRecipes(int size) {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
        Call<RecipesResponse> call = recipeApi.getRecipeList(0, size);
        call.enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println("Recipes in Repo: " + response.body().getResults().size());
                    recipes.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                Log.i("API", "Error in getAllRecipes() " + t.getMessage());
            }
        });

    }

    public void getRecipesByIngredients(ArrayList<String> ingredients) {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
        Call<RecipesResponse> call = recipeApi.getIngredientsList(0, 1000, ingredients);
        call.enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                if (response.isSuccessful()) {
                    recipes.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                Log.i("API", "Error in getRecipesByIngredients() " + t.getMessage());
            }
        });
    }

    public void getRecipeInfo(int id) {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
        Call<RecipeResponse> call = recipeApi.getRecipeInfo(id);
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()) {
                    recipeInfo.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.i("API", "Error in getRecipeInfo() " + t.getMessage());
            }
        });
    }
}
