package com.example.letseat.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.letseat.R;
import com.example.letseat.view.activities.AuthActivity;
import com.example.letseat.view.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LogOutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_log_out, container, false);
        MainActivity activity = ((MainActivity) getActivity());
        Button logOut = view.findViewById(R.id.logOutButton);

        logOut.setOnClickListener(v -> activity.logOut());

        return view;
    }
}