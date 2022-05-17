package com.example.letseat.repository.model;

import java.io.Serializable;

public class Recipe implements Serializable {
  
    private int id, total_time_minutes, num_servings;
    private String name, img;

    public Recipe() {}

    public Recipe(String name, String img) {
        this.name = name;
        this.img = img;
        this.id = -1;
        this.total_time_minutes = -1;
        this.num_servings = -1;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal_time_minutes(int total_time_minutes) {
        this.total_time_minutes = total_time_minutes;
    }

    public void setNum_servings(int num_servings) {
        this.num_servings = num_servings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
