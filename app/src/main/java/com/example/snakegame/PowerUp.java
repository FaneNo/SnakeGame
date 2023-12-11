package com.example.snakegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public abstract class PowerUp {
    protected Point location = new Point();
    protected Point mSpawnRange;
    protected int mSize;
    protected Bitmap mBitmapPowerUp;

    public PowerUp(Point spawnRange, int size, Bitmap bitmap) {
        mSpawnRange = spawnRange;
        mSize = size;
        mBitmapPowerUp = bitmap;
        location.x = -100; // Start off-screen
    }

    abstract void spawn();

    Point getLocation() {
        return location;
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmapPowerUp, location.x * mSize, location.y * mSize, paint);
    }
}
