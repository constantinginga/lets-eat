package viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import repository.RecipeRepository;
import repository.data.RecipeResponse;
import repository.model.Recipe;

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

    public LiveData<ArrayList<RecipeResponse>> getSearchedRecipe() {
        return repository.getSearchedRecipe();
    }

    public void searchForRecipe(String r) {
        repository.searchForRecipe(r);
    }
}
