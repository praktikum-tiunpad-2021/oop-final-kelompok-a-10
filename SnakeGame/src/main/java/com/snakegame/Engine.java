package com.snakegame;

public class Engine {
<<<<<<< HEAD
    Boolean isRunning;
    Boolean isGameOver;
    Engine(){
        this.isRunning = true;
        this.isGameOver = false;
=======
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
    };

    public void setIsInMenu(Boolean state) {
        this.isInMenu = state;
>>>>>>> temp
    }

    public void setGameOver(Boolean state){
        this.isGameOver = state;
    }

<<<<<<< HEAD
    public boolean getIsGameOver(){
        return this.isGameOver;
    }
=======
    public boolean getIsInGame(){
        return this.isInGame;
    }

    public boolean getIsInMenu(){
        return this.isInMenu;
    }

    public boolean getIsGameOver(){
        return this.isGameOver;
    }


>>>>>>> temp
}
