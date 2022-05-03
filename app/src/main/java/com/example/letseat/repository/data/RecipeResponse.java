package com.example.letseat.repository.data;

import com.example.letseat.repository.model.Recipe;

public class RecipeResponse {
    private String name, display, thumbnail_url;
    private int id, total_time_minutes, num_servings;

    public Recipe getRecipe() {
        return new Recipe(id, (name != null) ? name : display, thumbnail_url, total_time_minutes, num_servings);
    }
}
