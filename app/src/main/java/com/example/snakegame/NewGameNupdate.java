package com.example.snakegame;

import android.content.Context;
import android.graphics.Point;
import android.view.SurfaceView;
import android.widget.TextView;
import android.os.*;

import java.util.ArrayList;

public class NewGameNupdate implements Update{
    private Snake snake;
    private final int NUM_BLOCKS_WIDE = 40;
    private Apple apple;
    private Sound sound;

    private Gapple gapple;
    private Poison poison;
    private int score;
    private int numBlocksHigh;
    private long nextFrameTime;
    private GameState state;
    private Obstacle obstacle;
    private int highScore = 0;

    private boolean poisoned = false;

    public NewGameNupdate(Context context, Point size, GameState state){

        this.state = state;
        int blockSize = size.x/NUM_BLOCKS_WIDE;
        numBlocksHigh = size.y/blockSize;
        sound = new Sound(context);
        //create apple and snake the Point control where the boundary the snake can go and where the apple can spawn
        apple = new Apple(context, new Point(NUM_BLOCKS_WIDE- 12, numBlocksHigh -1), blockSize);
        gapple = new Gapple(context, new Point(NUM_BLOCKS_WIDE- 12, numBlocksHigh -1), blockSize);
        snake = new Snake(context, new Point(NUM_BLOCKS_WIDE - 12, numBlocksHigh), blockSize);
        obstacle = new Obstacle(context,new Point(NUM_BLOCKS_WIDE- 12, numBlocksHigh -1), blockSize);
        poison = new Poison(context, new Point(NUM_BLOCKS_WIDE -12, numBlocksHigh -1), blockSize);
    }
    @Override
    public void newGame(){
        snake.reset(NUM_BLOCKS_WIDE, numBlocksHigh);
        apple.spawn();
        gapple.spawn();
        obstacle.spawn();
        poison.spawn();
        score = 0;
        nextFrameTime = System.currentTimeMillis();
    }
    @Override
    public boolean updateRequired() {

        final long DEFAULT_TARGET_FPS = 10;
        // Run at 5 frames per second when poisoned
        final long POISONED_TARGET_FPS = 5;

        // Determine the target FPS based on whether the snake is poisoned
        final long TARGET_FPS = poisoned ? POISONED_TARGET_FPS : DEFAULT_TARGET_FPS;
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
    @Override
    public void update() {

        // Move the snake

        snake.move();

        // Did the head of the snake eat the apple?
        if(snake.checkDinner(apple.getLocation())){
            // This reminds me of Edge of Tomorrow.
            // One day the apple will be ready!
            apple.spawn();
            obstacle.spawn();

            // Add to  mScore
            score += 1;
            if(score > highScore){
                highScore = score;
            }

            // Play a sound
            sound.playEatSound();
        }
        //Did the head of the snake eat the Green Apple?
        if(snake.checkDinner(gapple.getLocation())){
            gapple.move();
            snake.addsegment();
            //this handler adds a delay when the green apple is eaten
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 5000ms
                    gapple.spawn();
                }
            }, 5000);

            obstacle.spawn();

            // Add to  mScore
            score += 3;
            if(score > highScore){
                highScore = score;
            }

            // Play a sound
            sound.playGrappleSound();
        }
        if(snake.checkDinner(poison.getLocation())){
            // Play a sound
            sound.playDrinkSound();
            poisoned = true;
            poison.move();

            //this handler adds a delay when the poison is drank
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 3000ms
                    poison.spawn();
                    poisoned = false;
                }
            }, 3000);


        }


        // Did the snake die?
        if (snake.detectDeath() || snake.checkDinner(obstacle.getLocation())) {
            // Pause the game ready to start again
            sound.playCrashSound();
            state.setPaused(true);
        }

    }
    @Override
    public Snake getSnake() {
        return snake;
    }

    @Override
    public Apple getApple() {
        return apple;
    }
    @Override
    public Gapple getGapple() {
        return gapple;
    }
    @Override
    public Poison getPoison(){
        return poison;
    }
    @Override
    public Obstacle getObstacle() {return obstacle;}
    @Override
    public int getHighScore(){
        return highScore;
    }
    @Override
    public int getScore() {
        return score;
    }
}
