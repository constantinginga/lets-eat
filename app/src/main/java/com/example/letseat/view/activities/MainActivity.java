package com.example.letseat.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.example.letseat.R;
import com.example.letseat.repository.model.Roles;
import com.example.letseat.repository.model.User;
import com.example.letseat.repository.model.UserList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        isCurrentAdmin();
        NavController navController = Navigation.findNavController(this,  R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public void logOut() {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this, AuthActivity.class));
    }

    private void isCurrentAdmin() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference();
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserList users = snapshot.getValue(UserList.class);
                    if (users != null) {
                        if (users.containsKey(user.getUid())) {
                            User userInDb = users.get(user.getUid());
                            // if the user is an admin
                            if (userInDb.role.equals(Roles.ADMIN.name().toLowerCase())) {
                                // add item to navbar
                                bottomNavigationView.getMenu().add(Menu.NONE, R.id.addRecipeFragment, Menu.NONE, R.string.nav_new).setIcon(R.drawable.ic_baseline_add_24);
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}