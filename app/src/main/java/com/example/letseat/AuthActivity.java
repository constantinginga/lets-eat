package com.example.letseat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import view.LoginFragment;
import view.MainPageFragment;
import view.SignUpFragment;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Intent mainActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mainActivityIntent = new Intent(AuthActivity.this, MainActivity.class);
        mAuth = FirebaseAuth.getInstance();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.authFragment, new SignUpFragment())
//                .commit();
//        EditText emailInput = findViewById(R.id.emailInput);
//        EditText passwordInput = findViewById(R.id.passwordInput);
//        Button loginBtn = findViewById(R.id.loginBtn);
//        TextView signUpLink = findViewById(R.id.signUpLink);

        // signUpLink.setPaintFlags(signUpLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // loginBtn.setOnClickListener(v -> login());

        // signUpLink.setOnClickListener(v -> {
            // switch to signup fragment here
        // });
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