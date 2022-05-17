package com.example.letseat.view.fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.letseat.view.activities.AuthActivity;
import com.example.letseat.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private Intent mainActivityIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        AuthActivity activity = ((AuthActivity) getActivity());
        TextInputLayout emailInput = view.findViewById(R.id.loginEmailInput);
        TextInputLayout passwordInput = view.findViewById(R.id.loginPasswordInput);
        Button loginBtn = view.findViewById(R.id.loginBtn);
        TextView signUpLink = view.findViewById(R.id.signUpLink);

        signUpLink.setPaintFlags(signUpLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        signUpLink.setOnClickListener(v -> activity.replaceFragment(false));
        loginBtn.setOnClickListener(v -> {
            if (emailInput.getEditText() != null && passwordInput.getEditText() != null) {
                activity.login(emailInput.getEditText().getText().toString(), passwordInput.getEditText().getText().toString());
            }
        });
        return view;
    }
}