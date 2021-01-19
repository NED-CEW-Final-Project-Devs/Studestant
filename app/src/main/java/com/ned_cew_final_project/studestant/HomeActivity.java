package com.ned_cew_final_project.studestant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    Button btn_todo_list;
    Button btn_wikipedia_search;
    Button btn_formulas_cheatsheet;
    Button btn_pomodoro_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_todo_list = findViewById(R.id.btn_todo_list);
        btn_wikipedia_search = findViewById(R.id.btn_wikipedia_search);
        btn_formulas_cheatsheet = findViewById(R.id.btn_formulas_cheatsheet);
        btn_pomodoro_timer = findViewById(R.id.btn_pomodoro_timer);

        btn_todo_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TodoListActivity.class);
                startActivity(intent);
            }
        });

        btn_wikipedia_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WikipediaSearchActivity.class);
                startActivity(intent);
            }
        });

        btn_formulas_cheatsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FormulasCheatsheetActivity.class);
                startActivity(intent);
            }
        });

        btn_pomodoro_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PomodoroTimerActivity.class);
                startActivity(intent);
            }
        });
    }
}