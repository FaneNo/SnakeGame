package com.example.snakegame;

import android.content.Context;
import android.graphics.Point;
import android.view.SurfaceView;

public class NewGameNupdate {
    private Snake snake;
    private final int NUM_BLOCKS_WIDE = 40;
    private Apple apple;
    private Sound sound;
    private int score;
    private int numBlocksHigh;
    private long nextFrameTime;
    private GameState state;
    private Control control;
    public NewGameNupdate(Context context, Point size, GameState state){

        this.state = state;
        int blockSize = size.x/NUM_BLOCKS_WIDE;
        numBlocksHigh = size.y/blockSize;
        sound = new Sound(context);
        apple = new Apple(context, new Point(NUM_BLOCKS_WIDE- 12, numBlocksHigh), blockSize);
        snake = new Snake(context, new Point(NUM_BLOCKS_WIDE - 12, numBlocksHigh), blockSize);
//        control = new Control(context, new Point(NUM_BLOCKS_WIDE, numBlocksHigh), blockSize);
    }

    public void newGame(){
        snake.reset(NUM_BLOCKS_WIDE, numBlocksHigh);
        apple.spawn();
        score = 0;
        nextFrameTime = System.currentTimeMillis();
    }

    public boolean updateRequired() {

        // Run at 10 frames per second
        final long TARGET_FPS = 10;
        // There are 1000 milliseconds in a second
        final long MILLIS_PER_SECOND = 1000;

        // Are we due to update the frame
        if(nextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            nextFrameTime =System.currentTimeMillis()
                    + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw
            // methods are executed
            return true;
        }

        return false;
    }
    public void update() {

        // Move the snake
        snake.move();

        // Did the head of the snake eat the apple?
        if(snake.checkDinner(apple.getLocation())){
            // This reminds me of Edge of Tomorrow.
            // One day the apple will be ready!
            apple.spawn();

            // Add to  mScore
            score += 1;

            // Play a sound
            sound.playEatSound();
        }

        // Did the snake die?
        if (snake.detectDeath()) {
            // Pause the game ready to start again
            sound.playCrashSound();
            state.setPaused(true);
        }

    }
    public Snake getSnake() {
        return snake;
    }
    public Control getControl() {
        return control;
    }

    public Apple getApple() {
        return apple;
    }

    public int getScore() {
        return score;
    }
}
