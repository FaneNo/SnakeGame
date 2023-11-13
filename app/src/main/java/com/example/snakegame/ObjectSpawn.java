package com.example.snakegame;

import android.graphics.Point;

import java.util.Random;

public class ObjectSpawn {
    private Point mSpawnRange;

    public ObjectSpawn(Point mSpawnRange){
        this.mSpawnRange = mSpawnRange;
    }
    // Spawn an object at a random location within the given range
    public Point spawn(){
        Random random = new Random();
        int x = random.nextInt(mSpawnRange.x) + 1;
        int y = random.nextInt(mSpawnRange.y - 1) + 1;
        return new Point(x,y);
    }
}
