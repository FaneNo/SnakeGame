package com.example.snakegame;

public class GameState {
    private volatile boolean mPlaying = false;
    private volatile boolean mPaused = true;

    public boolean isPaused() {
        return mPaused;
    }

    // Setter method for mPaused
    public void setPaused(boolean paused) {
        mPaused = paused;
    }

    // Getter method for mPlaying
    public boolean isPlaying() {
        return mPlaying;
    }

    // Setter method for mPlaying
    public void setPlaying(boolean playing) {
        mPlaying = playing;
    }
}
