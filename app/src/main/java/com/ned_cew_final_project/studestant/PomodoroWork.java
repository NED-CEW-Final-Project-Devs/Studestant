package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;

public class PomodoroWork extends AppCompatActivity {

    public static final long start_time_ms= 1500000;  //count down time

    TextView tv_j_work;
    CountDownTimer countdown_timer;

    long left_time= start_time_ms;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_work);

        tv_j_work= (TextView)findViewById(R.id.tv_pw);
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
                Intent intent_notify=new Intent(PomodoroWork.this, Tone_Service.class);
                startService(intent_notify);     //play the notification Sound

                finish();  //when timer finishes the , then the activity will close


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