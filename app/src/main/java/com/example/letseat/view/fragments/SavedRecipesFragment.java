package com.example.letseat.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letseat.R;
import com.example.letseat.view.SavedRecipeAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SavedRecipesFragment extends Fragment {
    
    RecyclerView recyclerView;
    SavedRecipeAdapter adapterRecipes;
    List<String> recipesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saved_recipes, container, false);
        
        // init recyclerview
        recyclerView = view.findViewById(R.id.savedRecipesRecyclerView);
        
        // set its properties
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        // init recipes list
        recipesList = new ArrayList<>();
        
        getAllRecipes();
        
        return view;
    }

    private void getAllRecipes() {
        // get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();

        //get path to of database named "saved_recipes" containing user info
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//
//        ValueEventListener recipeListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                recipesList.clear();
//                for (DataSnapshot s : snapshot.child("users").child(currentUserId).child("saved_recipes").getChildren()) {
//                    recipesList.add(s.getValue(String.class));
//                }
//                Log.i("hopa", recipesList.get(0));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w("Firebase RTDB", "load recipes cancelled", error.toException());
//            }
//        };
//        reference.addValueEventListener(recipeListener);

        //get all data from path
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.child(currentUserId).child("saved_recipes").getChildren()) {
                    String recipe = s.getValue(String.class);

                    recipesList.add(recipe);

                    adapterRecipes = new SavedRecipeAdapter(getActivity(), recipesList);

                    recyclerView.setAdapter(adapterRecipes);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}