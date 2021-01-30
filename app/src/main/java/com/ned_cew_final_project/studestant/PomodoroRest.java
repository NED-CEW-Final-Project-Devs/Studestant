package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class PomodoroRest extends AppCompatActivity {

    Intent timer_service_intent;

    public static final long start_time_ms = 300000;
    TextView tv_time_to_rest;
    TextView tv_j_rest;
    CountDownTimer countdown_timer;

    long left_time= start_time_ms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_rest);

        tv_time_to_rest = findViewById(R.id.tv_ttr);
        tv_j_rest= (TextView)findViewById(R.id.tv_pr);

        tv_time_to_rest.setText("Time to rest");

        timer_service_intent = new Intent(PomodoroRest.this, PomodoroTimerService.class);
        timer_service_intent.putExtra("start_time_ms", start_time_ms);
        startService(timer_service_intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(PomodoroTimerService.COUNTDOWN_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        stopService(timer_service_intent);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        stopService(timer_service_intent);
        super.onBackPressed();
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("time_left"))
            {
                left_time = intent.getLongExtra("time_left", 0);
                update_countdown_text();

            }
            if (intent.hasExtra("time_up"))
            {
                if (intent.getBooleanExtra("time_up", false))
                {
                    tv_time_to_rest.setText("Time's Up!\nGo back and start the work timer.");
                }
            }
        }
    };


    public void update_countdown_text() {
        int minutes = (int) (left_time / 1000) / 60;
        int seconds = (int) (left_time / 1000) % 60;

        String time_left_format = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tv_j_rest.setText(time_left_format);
    }
}