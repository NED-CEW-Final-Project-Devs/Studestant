package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Basic_Formulas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic__formulas);
    }

    public void nex(View view) {
        Intent intent = new Intent(this,Basic2_Formulas.class);
        startActivity(intent);

    }
}