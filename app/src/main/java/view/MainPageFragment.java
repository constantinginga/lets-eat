package view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.letseat.R;
import com.example.letseat.RecipeAdapter;

import java.util.ArrayList;

import repository.data.RecipeResponse;
import repository.model.Recipe;
import viewmodel.RecipeViewModel;

public class MainPageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        RecipeViewModel viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        TextView recipeName = view.findViewById(R.id.recipeName);
        EditText recipeIn = view.findViewById(R.id.searchRecipeInput);
        Button searchBtn = view.findViewById(R.id.searchRecipeBtn);

        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ArrayList<Recipe> recipes = new ArrayList<>();
        RecipeAdapter recipeAdapter = new RecipeAdapter(recipes, (v, position)  -> {});

        viewModel.getAllRecipes(20);
        viewModel.getRecipes().observe(getViewLifecycleOwner(), r -> {
            for (RecipeResponse re : r) {
                recipes.add(re.getRecipe());
                System.out.println(re.getRecipe().getId() + re.getRecipe().getName() + re.getRecipe().getImg());
            }
            rv.setAdapter(recipeAdapter);
        });


        searchBtn.setOnClickListener(v -> {
            if (recipeIn.getText() != null) {
                viewModel.searchForRecipe(recipeIn.getText().toString());
            }
        });

        return view;
    }
}
