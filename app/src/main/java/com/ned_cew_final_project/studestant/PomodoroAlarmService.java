package com.ned_cew_final_project.studestant;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class PomodoroAlarmService extends Service {
    MediaPlayer pomoAlarmPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (pomoAlarmPlayer == null)
        {
            pomoAlarmPlayer = MediaPlayer.create(this, R.raw.alarm);
            pomoAlarmPlayer.setLooping(false);
        }
        pomoAlarmPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        pomoAlarmPlayer.stop();
        pomoAlarmPlayer.release();
        pomoAlarmPlayer = null;
        super.onDestroy();
    }
}
