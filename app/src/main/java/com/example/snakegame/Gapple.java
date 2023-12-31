package com.example.snakegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

class Gapple {

    // The location of the apple on the grid
    // Not in pixels
    private Point location = new Point();

    // The range of values we can choose from
    // to spawn an apple
    private Point mSpawnRange;
    private int mSize;

    // An image to represent the apple
    private Bitmap mBitmapGapple;

    /// Set up the green apple in the constructor
    Gapple(Context context, Point sr, int s){

        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of a green apple
        mSize = s;
        // Hide the apple off-screen until the game starts
        location.x = -10;

        // Load the image to the bitmap
        mBitmapGapple = BitmapFactory.decodeResource(context.getResources(), R.drawable.gapple);

        // Resize the bitmap
        mBitmapGapple = Bitmap.createScaledBitmap(mBitmapGapple, s, s, false);
    }

    // This is called every time a green apple is eaten
    void spawn(){
        // Choose two random values and place the apple
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    void move(){
        location.x = -10;
    }

    // Let SnakeGame know where the green apple is
    // SnakeGame can share this with the snake
    Point getLocation(){
        return location;
    }

    // Draw the Gapple
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapGapple,
                location.x * mSize, location.y * mSize, paint);

    }

}