package com.example.letseat.repository.data;

import com.example.letseat.repository.model.Instruction;
import com.example.letseat.repository.model.Recipe;

import java.util.ArrayList;

public class RecipeResponse {
    private String name, display, thumbnail_url;
    private int id, total_time_minutes, num_servings;
    private ArrayList<SectionResponse> sections;
    private ArrayList<Instruction> instructions;

    public Recipe getRecipe() {
        return new Recipe(id, (name != null) ? name : display, thumbnail_url, total_time_minutes, num_servings);
    }

    public ArrayList<SectionResponse> getSections() {
        return sections;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }
}
