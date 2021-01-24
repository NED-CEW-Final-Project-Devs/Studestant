package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class PomodoroTimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_timer);
    }

    public void work_clk(View view) {

        //start the activity of Work
        Intent intent = new Intent(this,PomodoroWork.class);
        startActivity(intent);
    }

    public void rest_clk(View view) {
        //start the activity of Rest
        Intent intent = new Intent(this,PomodoroRest.class);
        startActivity(intent);
    }
}