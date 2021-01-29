package com.ned_cew_final_project.studestant;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PomodoroTimerService  extends Service {
    private final static String TAG = "BroadcastService";
    public static final String COUNTDOWN_BROADCAST = "com.ned_cew_final_project.studestant.countdown_br";
    Intent brodcast_intent = new Intent(COUNTDOWN_BROADCAST);

    public static long start_time_ms;
    CountDownTimer countdown_timer;
    long left_time;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        start_time_ms = intent.getLongExtra("start_time_ms", 0);
        left_time = start_time_ms;
        // create countdown timer
        countdown_timer = new CountDownTimer(left_time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                left_time = millisUntilFinished;

                // send time left to calling activity as brodcast
                brodcast_intent.putExtra("time_left", millisUntilFinished);
                sendBroadcast(brodcast_intent);
            }

            @Override
            public void onFinish() {
                Intent intent_notify = new Intent(getApplicationContext(), Tone_Service.class);
                startService(intent_notify);  //play the notification Sound
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopService(intent_notify);
                    }
                }, 2500);  // stop music player service after 2.5 seconds
                brodcast_intent.putExtra("time_up", true);
                sendBroadcast(brodcast_intent);
            }
        }.start();

        countdown_timer.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
