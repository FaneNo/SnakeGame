package com.example.snakegame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;


class SnakeGame extends SurfaceView implements Runnable{

    // Objects for the game loop/thread
    private Thread mThread = null;
    // The size in segments of the playable area

    private SurfaceHolder mSurfaceHolder;

    private Sound sound;
    private Drawing drawing;
    private GameState state;
    private NewGameNupdate gameUpdate;
    private TextView scoreView;
    private Button highScoreView;
    private Button highScoreView2;


    // This is the constructor method that gets called
    // from SnakeActivity
    public SnakeGame(Context context, Point size) {
        super(context);


        sound = new Sound(context);
        //Initialize playing/paused game state
        state = new GameState();

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();

        drawing = new Drawing(mSurfaceHolder, size);
        // Call the constructors of our two game objects
        gameUpdate = new NewGameNupdate(context,size,state);
        scoreView = ((Activity)context).findViewById(R.id.score);
        highScoreView = ((Activity)context).findViewById(R.id.highScore);
        highScoreView2 = ((Activity)context).findViewById(R.id.highScore2);


    }




    //this part will do the drawing
    public void draw(){
        drawing.draw(state.isPaused(), getScore(),gameUpdate.getApple(),gameUpdate.getGapple(),gameUpdate.getPoison(),gameUpdate.getSnake(), getResources().getString(R.string.tap_to_play),gameUpdate.getObstacle(), gameUpdate.getChocolateBar());

    }


    // Handles the game loop
    @Override
    public void run() {
        while (state.isPlaying()) {

            if(!state.isPaused()) {
                // Update 10 times a second
                if (updateRequired()) {
                    update();
                    updateScoreTextView();
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


                break;



            default:
                break;

        }
        return true;
    }

    //this method will update the score on the main thread
    //basically sending the request to the UI thread to update the information
    //since you need permission from the UI keeper to do any updating
    //The Ui keeper make sure the rule are follow like drawing or changing thing
    //This let it happen in a safe and orderly manner
    private void updateScoreTextView(){
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreView.setText("Score: " + getScore());
                highScoreView.setText("High Score: " + getHighScore());
                highScoreView2.setText("High Score: " + getHighScore());
            }
        });
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
//        mPlaying = true'
        state.setPlaying(true);
        mThread = new Thread(this);
        mThread.start();
    }
    public NewGameNupdate getGameUpdate(){
        return gameUpdate;
    }

    public void newGame(){
        gameUpdate.newGame();
    }
    // Check to see if it is time for an update
    private boolean updateRequired(){
        return gameUpdate.updateRequired();
    }
    // Update all the game objects
    public void update(){
        gameUpdate.update();
    }

    private int getScore(){
        return gameUpdate.getScore();
    }
    private int getHighScore(){
        return gameUpdate.getHighScore();
    }
}
