package com.example.letseat.repository.data;

import java.util.ArrayList;

public class RecipesResponse {
    private int count;
    private ArrayList<RecipeResponse> results;

    public ArrayList<RecipeResponse> getResults() {
        return results;
    }

    public int getCount() {
        return count;
    }
}
