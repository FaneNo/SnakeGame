package com.example.snakegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;
import android.util.Log;

public class ChocolateBar extends PowerUp {
    public static final long EFFECT_DURATION = 10000; // 10 seconds
    public static final int SCORE_MULTIPLIER = 2; // double the score
    private long effectStartTime;

    public ChocolateBar(Context context, Point sr, int s) {
        super(sr, s, BitmapFactory.decodeResource(context.getResources(), R.drawable.chocolatebar));
    }

    @Override
    public void spawn() {
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    public void consume() {
        effectStartTime = System.currentTimeMillis();
        // Speed should be increased, score multiplier should be increased

    }

}
