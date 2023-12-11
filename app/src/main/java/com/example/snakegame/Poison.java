package com.example.snakegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

class Poison {

    // The location of the poison on the grid
    // Not in pixels
    private Point location = new Point();

    // The range of values we can choose from
    // to spawn a bottle of poison
    private Point mSpawnRange;
    private int mSize;

    // An image to represent the poison
    private Bitmap mBitmapPoison;

    /// Set up the poison in the constructor
    Poison(Context context, Point sr, int s){

        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of a poison bottle
        mSize = s;
        // Hide the poison off-screen until the game starts
        location.x = -10;

        // Load the image to the bitmap
        mBitmapPoison = BitmapFactory.decodeResource(context.getResources(), R.drawable.poison);

        // Resize the bitmap
        mBitmapPoison = Bitmap.createScaledBitmap(mBitmapPoison, s, s, false);
    }

    // This is called every time a poison is drank
    void spawn(){
        // Choose two random values and place the poison
        Random random = new Random();
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    void move(){
        location.x = -10;
    }

    // Let SnakeGame know where the poison is
    // SnakeGame can share this with the snake
    Point getLocation(){
        return location;
    }

    // Draw the Bottle of Poison
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapPoison,
                location.x * mSize, location.y * mSize, paint);

    }

}