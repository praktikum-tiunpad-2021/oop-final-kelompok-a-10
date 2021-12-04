package com.snakegame;

public class Engine {
    private Boolean isInGame;
    private Boolean isGameOver;
    private Boolean isInMenu;

    Engine(){
        this.isInGame = false;
        this.isGameOver = false;
        this.isInMenu = true;
    }

    public void setIsInGame(Boolean state) {
        this.isInGame = state;
    }

    public void setGameOver(Boolean state){
        this.isGameOver = state;
    }

    public boolean getIsInGame(){ return this.isInGame; }

    public boolean getIsInMenu(){ return this.isInMenu; }

    public boolean getIsGameOver(){ return this.isGameOver; }
}
