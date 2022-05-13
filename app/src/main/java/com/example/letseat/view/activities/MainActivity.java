package com.example.letseat.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.letseat.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this,  R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}