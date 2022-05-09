package com.example.letseat.repository.model;

import java.io.Serializable;

public class Recipe implements Serializable {
    private final int id, total_time_minutes, num_servings;
    private final String name, img;

    public Recipe(int id, String name, String img, int total_time_minutes, int num_servings) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.total_time_minutes = total_time_minutes;
        this.num_servings = num_servings;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public int getTotal_time_minutes() {
        return total_time_minutes;
    }

    public int getNum_servings() {
        return num_servings;
    }
}
