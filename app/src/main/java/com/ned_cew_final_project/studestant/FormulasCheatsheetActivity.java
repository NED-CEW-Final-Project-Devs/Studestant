package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FormulasCheatsheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulas_cheatsheet_activty);
    }

    public void basic_formulas(View view) {
        Intent intent=new Intent(FormulasCheatsheetActivity.this,Basic_Formulas.class);
        startActivity(intent);


    }

    public void trigonometry_formulas(View view) {
        Intent intent=new Intent(FormulasCheatsheetActivity.this,Trigonometry_Formulas.class);
        startActivity(intent);
    }

    public void physics_formulas(View view) {
        Intent intent=new Intent(FormulasCheatsheetActivity.this,Physics_Formulas.class);
        startActivity(intent);
    }
}