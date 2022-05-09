package com.example.letseat.repository.data;

import com.example.letseat.repository.model.Ingredient;

import java.util.Locale;

public class ComponentResponse {
//    private Ingredient ingredient;
    private String raw_text;

    public Ingredient getIngredient() {
        return new Ingredient(raw_text.toLowerCase());
    }


    public String getRaw_text() {
        return raw_text;
    }
}
