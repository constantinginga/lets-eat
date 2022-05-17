package com.example.letseat.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import java.util.HashMap;
import java.util.Objects;

import com.example.letseat.repository.data.RecipeResponse;
import com.example.letseat.repository.model.Recipe;
import com.example.letseat.view.activities.RecipeActivity;
import com.example.letseat.viewmodel.RecipeViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPageFragment extends Fragment {

    private Intent recipeIntent;
    private View view;
    private RecipeViewModel viewModel;
    private ArrayList<Recipe> recipes;
    private ArrayList<Recipe> recipesFromDb;
    private RecyclerView rv;
    private ProgressBar pb;
    private LinearLayout layout;
    private TextInputLayout recipeIn;
    private Button searchBtn;
    private TextView mainPageTitle;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_page, container, false);
        init();

        if (getArguments() != null) {
            ArrayList<String> ingredients = getArguments().getStringArrayList("ingredients");
            if (ingredients != null) {
                ActionBar topBar = Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar());
                topBar.show();
                topBar.setElevation(0);
                requireActivity().setTitle(getString(R.string.search_results));
                mainPageTitle.setVisibility(View.GONE);
                searchBtn.setVisibility(View.GONE);
                recipeIn.setVisibility(View.GONE);
                layout.setPadding(layout.getPaddingLeft(), 0, layout.getPaddingRight(), layout.getPaddingBottom());
                ingredients = getArguments().getStringArrayList("ingredients");
                viewModel.getRecipesByIngredients(ingredients);
            }
        } else {
            viewModel.getAllRecipes(1000);
            searchBtn.setOnClickListener(v -> {
                if (recipeIn.getEditText() != null && recipeIn.getEditText().getText() != null) viewModel.searchForRecipe(recipeIn.getEditText().getText().toString());
            });
        }
        viewModel.getRecipes().observe(getViewLifecycleOwner(), this::refreshRecipes);

        return view;
    }

    private void init() {
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
        recipeIntent = new Intent(getActivity(), RecipeActivity.class);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        rv = view.findViewById(R.id.rv);
        mainPageTitle = view.findViewById(R.id.mainPageTitle);
        recipeIn = view.findViewById(R.id.searchRecipeInput);
        searchBtn = view.findViewById(R.id.searchRecipeBtn);
        pb = view.findViewById(R.id.progressBar);
        layout = view.findViewById(R.id.linearLayoutParent);
        layout.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recipes = new ArrayList<>();
        recipesFromDb = new ArrayList<>();
        ValueEventListener recipeListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recipesFromDb.clear();
                for (DataSnapshot s : snapshot.child("recipes").getChildren()) {
                    recipesFromDb.add(s.getValue(Recipe.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Firebase RTDB", "load recipes cancelled", error.toException());
            }
        };
        mDatabase.addValueEventListener(recipeListener);
    }

    private void refreshRecipes(ArrayList<RecipeResponse> r) {
        recipes.clear();
        if (recipesFromDb.size() != 0 && getArguments() == null) {
            recipes.addAll(recipesFromDb);
        }
        for (RecipeResponse re : r) {
            recipes.add(re.getRecipe());
        }
        RecipeAdapter recipeAdapter = new RecipeAdapter(recipes, recipe -> {
            if (recipe.getId() == -1) {
                Toast.makeText(getContext(), R.string.no_recipe_details, Toast.LENGTH_SHORT).show();
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable("recipe", recipe);
                recipeIntent.putExtras(bundle);
                startActivity(recipeIntent);
            }
        });
        rv.setAdapter(recipeAdapter);
        pb.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
    }
}
