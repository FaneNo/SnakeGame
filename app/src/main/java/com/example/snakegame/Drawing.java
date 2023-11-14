package com.example.snakegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class Drawing {
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;

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
    public void draw(boolean paused, int score, Apple apple, Snake snake, String tapToPlayMessage, Control control) {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            drawBackground(canvas);
            drawScore(canvas, score);
            apple.draw(canvas, paint);
            snake.draw(canvas, paint);
            // control.draw(canvas, paint);

            if (paused) {
                drawPauseMessage(canvas, tapToPlayMessage);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    /*
     * Draws the background of the game.
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
