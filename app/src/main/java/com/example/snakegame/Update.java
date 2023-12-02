package com.example.snakegame;

public interface Update {
    public void newGame();
    public boolean updateRequired();
    public void update();
    public Snake getSnake();
    public Apple getApple();
    public Poison getPoison();
    public Obstacle getObstacle();
    public Gapple getGapple();
    public int getHighScore();
    public int getScore();
}
