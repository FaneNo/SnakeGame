package com.example.snakegame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;

import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageButton;

public class SnakeActivity extends Activity {

    // Declare an instance of SnakeGame
    SnakeGame mSnakeGame;




    // Set the game up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the pixel dimensions of the screen
        Display display = getWindowManager().getDefaultDisplay();

        // Initialize the result into a Point object
        Point size = new Point();
        display.getSize(size);

        // Create a new instance of the SnakeEngine class
        mSnakeGame = new SnakeGame(this, size);
        // Make snakeEngine the view of the Activity
//        setContentView(mSnakeGame);


        //make this the main view
        FrameLayout container = findViewById(R.id.container);
        //make the mSnake view the children view of activity_main
        container.addView(mSnakeGame);

        final Button upBtn = findViewById(R.id.upBtn);
        final Button downBtn = findViewById(R.id.downBtn);
        final Button leftBtn = findViewById(R.id.leftBtn);
        final Button rightBtn = findViewById(R.id.rightBtn);

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mSnakeGame.getGameUpdate().getSnake().getHeading().equals(Snake.Heading.DOWN)){
                    mSnakeGame.getGameUpdate().getSnake().switchHeading(Snake.Heading.UP);
                }
            }
        });
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mSnakeGame.getGameUpdate().getSnake().getHeading().equals(Snake.Heading.UP)){
                    mSnakeGame.getGameUpdate().getSnake().switchHeading(Snake.Heading.DOWN);
                }

            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mSnakeGame.getGameUpdate().getSnake().getHeading().equals(Snake.Heading.RIGHT)){
                    mSnakeGame.getGameUpdate().getSnake().switchHeading(Snake.Heading.LEFT);
                }

            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mSnakeGame.getGameUpdate().getSnake().getHeading().equals(Snake.Heading.LEFT)){
                    mSnakeGame.getGameUpdate().getSnake().switchHeading(Snake.Heading.RIGHT);
                }

            }
        });


    }



    // Start the thread in snakeEngine
    @Override
    protected void onResume() {
        super.onResume();
        mSnakeGame.resume();
    }

    // Stop the thread in snakeEngine
    @Override
    protected void onPause() {
        super.onPause();
        mSnakeGame.pause();
    }
}
