package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import repository.data.RecipeApi;
import repository.data.RecipeResponse;
import repository.data.RecipesResponse;
import repository.data.ServiceGenerator;
import repository.model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private static RecipeRepository instance;
    private MutableLiveData<ArrayList<RecipeResponse>> recipes;
    private final MutableLiveData<ArrayList<RecipeResponse>> searchedRecipes;

    private RecipeRepository() {
        recipes = new MutableLiveData<>();
        searchedRecipes = new MutableLiveData<>();
    }

    public static synchronized RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }

        return instance;
    }

    public LiveData<ArrayList<RecipeResponse>> getSearchedRecipe() {
        return searchedRecipes;
    }

    public void searchForRecipe(String r) {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();
        Call<RecipesResponse> call = recipeApi.getRecipe(r);
        call.enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                if (response.isSuccessful()) {
                    searchedRecipes.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                Log.i("API", "Error in searchForRecipe(" + r + ")");
            }
        });
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
                    recipes.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                Log.i("API", "Error in getAllRecipes()");
            }
        });

    }
}
