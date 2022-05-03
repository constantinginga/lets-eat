package com.example.letseat.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.letseat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.letseat.view.fragments.LoginFragment;
import com.example.letseat.view.fragments.SignUpFragment;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Intent mainActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mainActivityIntent = new Intent(AuthActivity.this, MainActivity.class);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) startActivity(mainActivityIntent);
    }

    public void replaceFragment(boolean hasAccount) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.authFragment, (hasAccount) ? new LoginFragment() : new SignUpFragment())
                .commit();
    }

    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Login successful
                        startActivity(mainActivityIntent);
                    }
                    else {
                        // Login failed
                        Log.w("Auth", "signInWithEmailAndPassword:failure", task.getException());
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void signup(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign up successful
                        Log.d("Auth", "createUserWithEmail:success");
                        startActivity(mainActivityIntent);
                    } else {
                        // Sign up failed
                        Log.w("Auth", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}