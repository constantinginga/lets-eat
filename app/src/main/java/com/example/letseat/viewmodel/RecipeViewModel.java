package com.example.letseat.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import com.example.letseat.repository.RecipeRepository;
import com.example.letseat.repository.data.RecipeResponse;

public class RecipeViewModel extends ViewModel {
    RecipeRepository repository;

    public RecipeViewModel() {
        repository = RecipeRepository.getInstance();
    }

    public LiveData<ArrayList<RecipeResponse>> getRecipes() {
        return repository.getRecipes();
    }

    public void getAllRecipes(int size) {
        repository.getAllRecipes(size);
    }

//    public LiveData<ArrayList<RecipeResponse>> getSearchedRecipes() {
//        return com.example.letseat.repository.getSearchedRecipes();
//    }

    public void searchForRecipe(String r) {
        repository.searchForRecipe(r);
    }

    public void getRecipesByIngredients(ArrayList<String> r) { repository.getRecipesByIngredients(r); }

}
