package com.example.snakegame;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import java.io.IOException;

public class Sound {
    private SoundPool mSP;
    private int mEat_ID = -1;
    private int mCrashID = -1;
    private int mBGMID = -1;
    private int mDrinkID = -1;
    public Sound(Context context) {
        // Initialize the SoundPool
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            mSP = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            mSP = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            // Prepare the sounds in memory
            descriptor = assetManager.openFd("get_apple.wav");
            mEat_ID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("snake_death.wav");
            mCrashID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("drink.wav");
            mDrinkID = mSP.load(descriptor, 0);

            descriptor = assetManager.openFd("bgm.wav");
            mBGMID = mSP.load(descriptor, 0);
        } catch (IOException e) {
            // Handle error
            Log.d("error", "failed to load sound files");
        }
    }
    public void playEatSound() {
        Log.d("SoundManager", "Playing eat sound");
        mSP.play(mEat_ID, 1, 1, 0, 0, 1);
    }

    public void playCrashSound() {
        Log.d("SoundManager", "Playing crash sound");
        mSP.play(mCrashID, 1, 1, 0, 0, 1);
    }
    public void playDrinkSound() {
        Log.d("SoundManager", "Playing drinking sound");
        mSP.play(mDrinkID, 10, 10, 0, 0, 1);
    }
    public void playBGM() {
        Log.d("SoundManager", "Playing BGM");
        mSP.play(mBGMID, 1, 1, 0, 10, 1);
    }
}
