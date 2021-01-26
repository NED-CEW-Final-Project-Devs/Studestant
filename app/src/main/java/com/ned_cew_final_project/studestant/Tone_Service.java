package com.ned_cew_final_project.studestant;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

public class Tone_Service extends Service {  //Service
    private MediaPlayer player;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    } //abstract method of service class

    //start service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.alarm);

// It will start the player
        player.start();
        Toast.makeText(this, "The Time is up", Toast.LENGTH_LONG).show();

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        super.onDestroy();
    }
}
