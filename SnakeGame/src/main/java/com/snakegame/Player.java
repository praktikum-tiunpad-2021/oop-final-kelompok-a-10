package com.snakegame;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;

    Player(){
        this.name = null;
        this.score = -1;
    }

    Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public void setName(String name){
        this.name = name;
    }

    public void incrementScore(){
        this.score = this.score + 1;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){ return this.score; }

}
