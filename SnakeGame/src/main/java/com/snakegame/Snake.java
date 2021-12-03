package com.snakegame;

import java.util.List;
import java.util.ArrayList;

public class Snake{
    int speed;
    int score;
    List<Cell> bodyCells;
    Direction curDirection;

    Snake(){
        this.speed = 5;
        this.score = -1;
        this.bodyCells = new ArrayList<>();
        this.curDirection = Direction.LEFT;
    }

    public void incrementSpeed(){
        this.speed = this.speed++;
    }

    public void incrementScore(){
        this.score++;
    }

    public int getScore(){
        return this.score;
    }

<<<<<<< HEAD
=======
    public void setSpeed(int speed){
        this.speed = speed;
    }

>>>>>>> temp
    public int getSpeed(){
        return this.speed;
    }

    public int getBodySize(){
        return this.bodyCells.size();
    }

    public void setDirection(Direction dir){
        this.curDirection = dir;
    }

    public Direction getCurDirection(){
        return this.curDirection;
    }

}
