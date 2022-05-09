package com.example.letseat.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.letseat.R;
import com.example.letseat.repository.model.Instruction;
import com.example.letseat.repository.model.Recipe;

import java.util.ArrayList;
import java.util.Objects;

public class InstructionsActivity extends AppCompatActivity {

    private ArrayList<Instruction> instructions;
    private Button homeBtn, nextBtn, prevBtn;
    private TextView title, text;
    private MutableLiveData<Integer> currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        init();
        currentPosition.observe(this, newVal -> {
            title.setText(String.format(getResources().getString(R.string.instruction_title), currentPosition.getValue(), instructions.size()));
            text.setText(instructions.get(currentPosition.getValue() - 1).getDisplay_text());
        });
        homeBtn.setOnClickListener(v -> switchActivity());
        prevBtn.setOnClickListener(v -> changeInstruction(-1));
        nextBtn.setOnClickListener(v -> changeInstruction(1));
    }

    private void init() {
        currentPosition = new MutableLiveData<>();
        currentPosition.setValue(1);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent = this.getIntent();
        instructions = (ArrayList<Instruction>) intent.getExtras().getSerializable("instructions");
        homeBtn = findViewById(R.id.homeBtn);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);
        title = findViewById(R.id.instructionNo);
        text = findViewById(R.id.instructionText);
        prevBtn.setVisibility(View.GONE);
    }

    private void switchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void changeInstruction(int step) {
        currentPosition.setValue(currentPosition.getValue() + step);

        if (currentPosition.getValue() == instructions.size()) nextBtn.setVisibility(View.GONE);
        else nextBtn.setVisibility(View.VISIBLE);

        if (currentPosition.getValue() == 1) prevBtn.setVisibility(View.GONE);
        else prevBtn.setVisibility(View.VISIBLE);
    }
}