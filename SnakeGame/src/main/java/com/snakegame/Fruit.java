package com.snakegame;

import java.util.Random;

public class Fruit {
    int color;
    int x;
    int y;
    Random rand;

    Fruit(){
        this.color = 0;
        this.x = 0;
        this.y = 0;
        this.rand = new Random();
    }

    public void newFruit(Frame frame, Snake snake) {
        start: while (true) {
            setX(rand.nextInt(frame.getWidth()));
            setY(rand.nextInt(frame.getHeight()));

            for (Cell c : snake.bodyCells) {
                if (c.x == getX() && c.y == getY()) {
                    continue start;
                }
            }

            setColor();
            snake.incrementScore();
            break;

        }
    }

    public void setColor(){
        this.color = rand.nextInt(5);
    }

    public int getColor(){
        return this.color;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int randomFruitX(Frame frame){
        this.x = rand.nextInt(frame.getWidth());
        return this.x;
    }

    public int randomFruitY(Frame frame){
        this.y = rand.nextInt(frame.getHeight());
        return this.y;
    }
}
