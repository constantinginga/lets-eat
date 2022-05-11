package com.example.letseat.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letseat.R;
import com.example.letseat.view.RecipeAdapter;

import java.util.ArrayList;

import com.example.letseat.repository.data.RecipeResponse;
import com.example.letseat.repository.model.Recipe;
import com.example.letseat.viewmodel.RecipeViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class SearchByProductFragment extends Fragment {

    private RecipeViewModel viewModel;
    private MutableLiveData<Integer> numOfIngredients;
    private ArrayList<String> ingredients;
    private View view;
    private LinearLayout ingredientsContainer;
    private TextInputLayout in;
    private Button addBtn;
    private ExtendedFloatingActionButton fab;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_by_product, container, false);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        init();

        addBtn.setOnClickListener(v -> {
            if (in.getEditText() != null) {
                addNewIngredient(in.getEditText().getText().toString());
                in.getEditText().getText().clear();
            }
        });
        numOfIngredients.observe(requireActivity(), newVal -> {
            if (newVal > 0) fab.setVisibility(View.VISIBLE);
        });
        fab.setOnClickListener(v -> {
            MainPageFragment fragment = new MainPageFragment();
            Bundle args = new Bundle();
            args.putStringArrayList("ingredients", ingredients);
            fragment.setArguments(args);
            getParentFragmentManager().beginTransaction().replace(container.getId(), fragment).commit();
        });
        return view;
    }

    private void init() {
        ingredients = new ArrayList<>();
        numOfIngredients = new MutableLiveData<>();
        numOfIngredients.setValue(0);
        in = view.findViewById(R.id.ingredientInput);
        addBtn = view.findViewById(R.id.addIngredientBtn);
        ingredientsContainer = view.findViewById(R.id.ingredientsContainer);
        fab = view.findViewById(R.id.searchFab);
        fab.setVisibility(View.GONE);
    }

    private void addNewIngredient(String ingredientName) {
        if (ingredientName == null || ingredientName.equals("") || numOfIngredients.getValue() == 3) return;
        View ingredientView = LayoutInflater.from(getActivity()).inflate(R.layout.ingredient_item, null);
        TextView name = ingredientView.findViewById(R.id.ingredientName);
        name.setText(ingredientName);
        ingredientsContainer.addView(ingredientView);
        ingredients.add(ingredientName);
        numOfIngredients.setValue(numOfIngredients.getValue() + 1);
    }
}
