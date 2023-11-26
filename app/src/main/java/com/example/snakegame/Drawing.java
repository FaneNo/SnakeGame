package com.example.snakegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.widget.TextView;

public class Drawing {
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private TextView score;

    /*
     * Constructor for Drawing class.
     * Initializes the SurfaceHolder and Paint objects
     */
    public Drawing(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;

        paint = new Paint();

    }
    /*
     * Main draw method that orchestrates rendering of game elements.
     */
    public void draw(boolean paused, int score, Apple apple,Gapple gapple, Snake snake, String tapToPlayMessage, Obstacle obstacle) {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();


            // Set the size and color of the paint for the text
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(120);

            // Draw the score
            canvas.drawText("" + score, 20, 120, paint);

            // Draw the apple and the snake

            drawBackground(canvas);
            drawScore(canvas, score);

            apple.draw(canvas, paint);
            gapple.draw(canvas,paint);
            snake.draw(canvas, paint);
            obstacle.draw(canvas,paint);
            // control.draw(canvas, paint);

            if (paused) {
                drawPauseMessage(canvas, tapToPlayMessage);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    /*
     * Draws the background color of the game.
     */
    private void drawBackground(Canvas canvas) {
        canvas.drawColor(Color.argb(255, 26, 128, 182));
    }

    /*
     * Draws the current score.
     */
    private void drawScore(Canvas canvas, int score) {
        paint.setColor(Color.argb(255, 255, 255, 255));
        paint.setTextSize(120);
        canvas.drawText("" + score, 20, 120, paint);
    }

    /*
     * Draws the pause message.
     */
    private void drawPauseMessage(Canvas canvas, String message) {
        paint.setColor(Color.argb(255, 255, 255, 255));
        paint.setTextSize(250);
        canvas.drawText(message, 200, 700, paint);
    }

}
