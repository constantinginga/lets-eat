package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import repository.data.RecipeApi;
import repository.data.RecipeResponse;
import repository.data.RecipesResponse;
import repository.data.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private static RecipeRepository instance;
    private final MutableLiveData<ArrayList<RecipeResponse>> recipes;
    private final ArrayList<RecipeResponse> cachedRecipes;
    private final ArrayList<RecipeResponse> searchedRecipes;

    private RecipeRepository() {
        recipes = new MutableLiveData<>();
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
                Log.i("API", "Error in getAllRecipes()");
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
                Log.i("API", "Error in getting all getRecipesByIngredients");
            }
        });
    }
}
