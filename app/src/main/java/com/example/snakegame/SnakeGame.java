package com.example.snakegame;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


class SnakeGame extends SurfaceView implements Runnable{

    // Objects for the game loop/thread
    private Thread mThread = null;
    // The size in segments of the playable area

    private SurfaceHolder mSurfaceHolder;

    private Sound sound;
    private Drawing drawing;
    private GameState state;
    private NewGameNupdate gameUpdate;

    // This is the constructor method that gets called
    // from SnakeActivity
    public SnakeGame(Context context, Point size) {
        super(context);


        sound = new Sound(context);
        //Initialize playing/paused game state
        state = new GameState();

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();

        drawing = new Drawing(mSurfaceHolder);
        // Call the constructors of our two game objects
        gameUpdate = new NewGameNupdate(context,size,state);

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
        drawing.draw(state.isPaused(), getScore(),gameUpdate.getApple(),gameUpdate.getSnake(), getResources().getString(R.string.tap_to_play),gameUpdate.getControl());

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
