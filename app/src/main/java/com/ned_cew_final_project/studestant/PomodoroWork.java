package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import java.util.Locale;

public class PomodoroWork extends AppCompatActivity {

    public static final long start_time_ms= 1500000;  //count down time

    TextView tv_j_work;
    TextView tv_ttw;
    CountDownTimer countdown_timer;

    long left_time= start_time_ms;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_work);

        tv_j_work= (TextView)findViewById(R.id.tv_pw);
        tv_ttw = findViewById(R.id.tv_ttw);

        tv_ttw.setText("Time to work");
        start_timer();
    }


    public void start_timer() {
        countdown_timer= new CountDownTimer(left_time,1000) {  //1000ms= 1 second interval
            @Override
            public void onTick(long millisUntilFinished) {
                left_time= millisUntilFinished;
                update_countdown_text();  //updated the text view of timer

            }

            @Override
            public void onFinish() {
                Intent intent_notify = new Intent(PomodoroWork.this, Tone_Service.class);
                startService(intent_notify);  //play the notification Sound
                tv_ttw.setText("Time's Up!\nGo back and start the rest timer.");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopService(intent_notify);
                    }
                }, 2500);  // stop music player service after 2.5 seconds
            }
        }.start();

    }




    public void update_countdown_text() {
        int minutes= (int) (left_time / 1000)  / 60;
        int seconds= (int) (left_time / 1000)  % 60;

        String time_left_format = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        tv_j_work.setText(time_left_format);
    }




}