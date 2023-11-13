package com.example.snakegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public class Control {
    private Bitmap down;
    private Bitmap up;
    private Bitmap left;
    private Bitmap right;
//    private int size = 10;

    Control(Context context, Point mr, int ss){
//        size = ss;
        down = BitmapFactory.decodeResource(context.getResources(), R.drawable.downarrow);
        down = Bitmap.createScaledBitmap(down,ss*2,ss*2,false);


    }
    void draw(Canvas canvas, Paint paint){
        float x = (canvas.getWidth() - down.getWidth());
        float y = canvas.getHeight() - down.getHeight() - 20.0f;
        canvas.drawBitmap(down, x-200, y, paint);

    }



}
