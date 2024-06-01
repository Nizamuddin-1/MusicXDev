package com.example.musicxdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    AudioManager audioManager;
    

    public void Play(View view) {
         player.start();
    }
    public void Pause(View view) {
        player.pause();
    }
    public void Stop(View view) {
        player.stop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = MediaPlayer.create(this,R.raw.abc);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar seekVol = findViewById(R.id.seekVol);
        seekVol.setMax(maxVol);
        seekVol.setProgress(curVol);
        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar seekProg = findViewById(R.id.seekProg);
        seekProg.setMax(player.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
                                            @Override
                                            public void run() {
                                                seekProg.setProgress(player.getCurrentPosition());
                                            }
                                        },0,100);

        seekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}