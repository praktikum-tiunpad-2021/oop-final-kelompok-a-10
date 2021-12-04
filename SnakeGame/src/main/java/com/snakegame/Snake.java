package com.snakegame;

import java.util.ArrayList;
import java.util.List;

public class Snake{
    int speed;
    List<Cell> bodyCells;
    Direction curDirection;

    Snake(){
        this.speed = 5;
        this.bodyCells = new ArrayList<>();
        this.curDirection = Direction.LEFT;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

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
