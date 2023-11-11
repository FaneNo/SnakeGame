package com.example.snakegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class Drawing {
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;


    public Drawing(SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        paint = new Paint();
    }
    public void draw(boolean paused, int score, Apple apple, Snake snake, String tapToPlayMessage){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            // Fill the screen with a color
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the paint for the text
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(120);

            // Draw the score
            canvas.drawText("" + score, 20, 120, paint);

            // Draw the apple and the snake
            apple.draw(canvas, paint);
            snake.draw(canvas, paint);

            // Draw some text while paused
            if (paused) {
                // Set the size and color of the paint for the text
                paint.setColor(Color.argb(255, 255, 255, 255));
                paint.setTextSize(250);

                // Draw the message
                canvas.drawText(tapToPlayMessage, 200, 700, paint);

            }

            // Unlock the canvas and reveal the graphics for this frame
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
