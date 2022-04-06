package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import repository.data.RecipeResponse;
import repository.model.Recipe;
import viewmodel.RecipeViewModel;

public class MainActivity extends AppCompatActivity {

    private RecipeViewModel viewModel;
    private RecyclerView rv;
    private RecipeAdapter recipeAdapter;
    private TextView recipeName;
    private Button searchBtn;
    private EditText recipeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}