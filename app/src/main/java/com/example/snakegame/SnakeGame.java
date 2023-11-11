package com.example.snakegame;

import android.app.GameManager;
import android.app.GameState;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;


class SnakeGame extends SurfaceView implements Runnable{

    // Objects for the game loop/thread
    private Thread mThread = null;
    // Control pausing between updates
    private long mNextFrameTime;
    // Is the game currently playing and or paused?
    private volatile boolean mPlaying = false;
    private volatile boolean mPaused = true;

    // for playing sound effects
    private SoundPool mSP;
    
    private int mEat_ID = -1;
    private int mCrashID = -1;

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private int mNumBlocksHigh;

    // How many points does the player have
    private int mScore;

    // Objects for drawing
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    // A snake ssss
    private Snake mSnake;
    // And an apple
    private Apple mApple;

    private Sound sound;
    private Drawing drawing;
    private gameState state;
    private newGameNupdate gameUpdate;
    // This is the constructor method that gets called
    // from SnakeActivity
    public SnakeGame(Context context, Point size) {
        super(context);

        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

        sound = new Sound(context);

        //Initialize playing/paused game state
        state = new gameState();

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();

        drawing = new Drawing(mSurfaceHolder);
        // Call the constructors of our two game objects
//        mApple = new Apple(context,
//                new Point(NUM_BLOCKS_WIDE,
//                        mNumBlocksHigh),
//                blockSize);
//
//        mSnake = new Snake(context,
//                new Point(NUM_BLOCKS_WIDE,
//                        mNumBlocksHigh),
//                blockSize);
        gameUpdate = new newGameNupdate(context,size,state);

    }

    public void newGame(){
        gameUpdate.newGame();
    }
    // Check to see if it is time for an update
    public boolean updateRequired(){
        return gameUpdate.updateRequired();
    }
    // Update all the game objects
    public void update(){
        gameUpdate.update();
    }

    public int getScore(){
        return gameUpdate.getScore();
    }


    //this part will do the drawing
    public void draw(){
        drawing.draw(state.isPaused(), getScore(),gameUpdate.getApple(),gameUpdate.getSnake(), getResources().getString(R.string.tap_to_play));
    }


    // Handles the game loop
    @Override
    public void run() {
        while (state.isPlaying()) {
            if(!state.isPaused()) {
                // Update 10 times a second
                if (updateRequired()) {
                    update();
                }
            }

            draw();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (state.isPaused()) {
//                    mPaused = false;
                    state.setPaused(false);
                    newGame();

                    // Don't want to process snake direction for this tap
                    return true;
                }

                // Let the Snake class handle the input
                gameUpdate.getSnake().switchHeading(motionEvent);
                break;

            default:
                break;

        }
        return true;
    }



    // Stop the thread
    public void pause() {
//        mPlaying = false;
        state.setPlaying(false);
        try {
            mThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }


    // Start the thread
    public void resume() {
//        mPlaying = true;
        state.setPlaying(true);
        mThread = new Thread(this);
        mThread.start();
    }
}
