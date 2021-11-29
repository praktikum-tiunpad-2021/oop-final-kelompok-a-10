package com.snakegame;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;

    Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void incrementScore(){
        this.score++;
    }

    public int getScore(){
        return this.score;
    }

}
