package com.example.letseat.view.fragments;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letseat.R;
import com.example.letseat.view.RecipeAdapter;

import java.util.ArrayList;

import com.example.letseat.repository.data.RecipeResponse;
import com.example.letseat.repository.model.Recipe;
import com.example.letseat.view.activities.RecipeActivity;
import com.example.letseat.viewmodel.RecipeViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class MainPageFragment extends Fragment {

    private Intent recipeIntent;
    private View view;
    private RecipeViewModel viewModel;
    private ArrayList<Recipe> recipes;
    private RecyclerView rv;
    private ProgressBar pb;
    private LinearLayout layout;
    private TextInputLayout recipeIn;
    private Button searchBtn;
    private TextView mainPageTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_page, container, false);
        init();

        if (getArguments() != null) {
            ArrayList<String> ingredients = getArguments().getStringArrayList("ingredients");
            if (ingredients != null) {
                searchBtn.setVisibility(View.GONE);
                recipeIn.setVisibility(View.GONE);
                mainPageTitle.setText(R.string.search_results);
                ingredients = getArguments().getStringArrayList("ingredients");
                /*
                TODO:
                 - add topbar with back button to searchByProductFragment
                 - call api with ingredients from ingredients arraylist
                 */
            }
        }

        viewModel.getAllRecipes(1000);
        viewModel.getRecipes().observe(getViewLifecycleOwner(), this::refreshRecipes);

        searchBtn.setOnClickListener(v -> {
            if (recipeIn.getEditText() != null && recipeIn.getEditText().getText() != null) viewModel.searchForRecipe(recipeIn.getEditText().getText().toString());
        });

        return view;
    }

    private void init() {
        recipeIntent = new Intent(getActivity(), RecipeActivity.class);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        rv = view.findViewById(R.id.rv);
        recipeIn = view.findViewById(R.id.searchRecipeInput);
        searchBtn = view.findViewById(R.id.searchRecipeBtn);
        mainPageTitle = view.findViewById(R.id.mainPageTitle);
        pb = view.findViewById(R.id.progressBar);
        layout = view.findViewById(R.id.linearLayoutParent);
        layout.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recipes = new ArrayList<>();
    }

    private void refreshRecipes(ArrayList<RecipeResponse> r) {
        recipes.clear();
        for (RecipeResponse re : r) {
            recipes.add(re.getRecipe());
        }
        RecipeAdapter recipeAdapter = new RecipeAdapter(recipes, recipe -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("recipe", recipe);
            recipeIntent.putExtras(bundle);
            startActivity(recipeIntent);
        });
        rv.setAdapter(recipeAdapter);
        pb.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
    }
}
