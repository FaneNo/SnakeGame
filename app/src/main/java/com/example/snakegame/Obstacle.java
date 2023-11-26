package com.example.snakegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Obstacle {
    // The location of the apple on the grid
    // Not in pixels
    private Point location = new Point();

    // The range of values we can choose from
    // to spawn an apple
    private Point mSpawnRange;
    private int mSize;

    // An image to represent the apple
    private Bitmap mBitmapObstacle;

    private ObjectSpawn objectSpawn;
    /// Set up the apple in the constructor
    Obstacle(Context context, Point sr, int s){

        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of an obstacle
        mSize = s;
        // Hide the apple off-screen until the game starts
        location.x = -100;

        // Load the image to the bitmap
        mBitmapObstacle = BitmapFactory.decodeResource(context.getResources(), R.drawable.obstacle);

        // Resize the bitmap
        mBitmapObstacle = Bitmap.createScaledBitmap(mBitmapObstacle, s, s, false);

        objectSpawn = new ObjectSpawn(mSpawnRange);
    }

    // This is called every time an apple is eaten
    void spawn(){
        Point spawnLocation = objectSpawn.spawn();
        location.x = spawnLocation.x;
        location.y = spawnLocation.y;
    }

    // Let SnakeGame know where the apple is
    // SnakeGame can share this with the snake
    Point getLocation(){
        return location;
    }

    // Draw the obstacle
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapObstacle,
                location.x * mSize, location.y * mSize, paint);

    }
}
