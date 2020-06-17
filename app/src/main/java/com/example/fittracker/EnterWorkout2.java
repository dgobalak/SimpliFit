package com.example.fittracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class EnterWorkout2 extends AppCompatActivity {
    private EditText exerciseName;
    private EditText numOfSets;
    private EditText numOfReps;
    private EditText weight;
    private Button add;
    private Button finish;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_workout2);
        dbHelper = new DatabaseHelper(this);
        exerciseName = findViewById(R.id.exercise_name);
        numOfSets = findViewById(R.id.number_of_sets);
        numOfReps = findViewById(R.id.number_of_reps);
        weight = findViewById(R.id.weight);
        add = findViewById(R.id.add_exercise_button);
        finish = findViewById(R.id.finish_button);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterWorkout2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exercise = exerciseName.getText().toString();
                String reps = numOfReps.getText().toString();
                String sets = numOfSets.getText().toString();
                String weightUsed = weight.getText().toString();
                String workoutName = getIntent().getStringExtra("workout_name");

                if (!exercise.isEmpty() && !sets.isEmpty() && !reps.isEmpty()
                        && !weightUsed.isEmpty()){
                    dbHelper.insert(workoutName, exercise, sets, reps, weightUsed);
                    Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT);
                    exerciseName.setText("");
                    numOfReps.setText("");
                    numOfSets.setText("");
                    weight.setText("");

                };
            }
        });

    }
}