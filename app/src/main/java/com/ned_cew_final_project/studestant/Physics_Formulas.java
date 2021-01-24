package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Physics_Formulas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics__formulas);
    }

    public void physics2_formulas(View view) {
        Intent intent = new Intent(this,Physics2_Formulas.class);
        startActivity(intent);


    }
}