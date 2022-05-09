package com.example.letseat.repository.model;

import java.io.Serializable;

public class Instruction implements Serializable {
    private int id, position;
    private String display_text;

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public String getDisplay_text() {
        return display_text;
    }
}
