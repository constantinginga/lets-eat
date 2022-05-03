package com.example.letseat.view.fragments;

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

public class SignUpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        AuthActivity activity = ((AuthActivity) getActivity());
        EditText emailInput = view.findViewById(R.id.signUpEmailInput);
        EditText passwordInput = view.findViewById(R.id.signUpPasswordInput);
        Button signUpBtn = view.findViewById(R.id.signUpBtn);
        TextView loginLink = view.findViewById(R.id.loginLink);

        loginLink.setPaintFlags(loginLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        loginLink.setOnClickListener(v -> activity.replaceFragment(true));
        signUpBtn.setOnClickListener(v -> activity.signup(emailInput.getText().toString(), passwordInput.getText().toString()));
        return view;
    }
}