package com.snakegame;

public class Engine {
    Boolean isRunning;
    Boolean isGameOver;
    Engine(){
        this.isRunning = true;
        this.isGameOver = false;
    }

    public void setGameOver(Boolean state){
        this.isGameOver = state;
    }

    public boolean getIsGameOver(){
        return this.isGameOver;
    }
}
