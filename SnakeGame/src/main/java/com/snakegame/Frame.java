package com.snakegame;

public class Frame {
    int width;
    int height;
    int cellSize;

    Frame(){
        this.width = 20;
        this.height = 15;
        this.cellSize = 30;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getCellSize(){
        return this.cellSize;
    }
}
