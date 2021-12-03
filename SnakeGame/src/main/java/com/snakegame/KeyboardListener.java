package com.snakegame;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardListener{
<<<<<<< HEAD

    public void listenKey(Label label, AnimationTimer aTimer, Scene scene, Snake snake){
        scene.setOnKeyPressed((EventHandler<? super KeyEvent>) new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode kc = event.getCode();
                if(kc == KeyCode.SPACE){
                    label.setText("");
                    aTimer.start();
                    
                }
                else if(kc == KeyCode.ESCAPE){
                    aTimer.stop();
                }
                else if (kc == KeyCode.RIGHT || kc == KeyCode.D) {
                    if (snake.getCurDirection() != Direction.LEFT) {
                        snake.setDirection(Direction.RIGHT);
                    }
                } 
                else if (kc == KeyCode.LEFT || kc == KeyCode.A) {
                    if (snake.getCurDirection() != Direction.RIGHT) {
                        snake.setDirection(Direction.LEFT);
                    }
                } 
                else if (kc == KeyCode.UP || kc == KeyCode.W) {
                    if (snake.getCurDirection() != Direction.DOWN) {
                        snake.setDirection(Direction.UP);
                    }
                } 
                else if (kc == KeyCode.DOWN || kc == KeyCode.S) {
                    if (snake.getCurDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);
                    }
=======
//    public void endKey()


    public void listenKey(AnimationTimer aTimer, Scene scene, Snake snake){
        scene.setOnKeyPressed(event -> {
            KeyCode kc = event.getCode();

            if (kc == KeyCode.RIGHT || kc == KeyCode.D) {
                if (snake.getCurDirection() != Direction.LEFT) {
                    snake.setDirection(Direction.RIGHT);
                }
            }
            else if (kc == KeyCode.LEFT || kc == KeyCode.A) {
                if (snake.getCurDirection() != Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                }
            }
            else if (kc == KeyCode.UP || kc == KeyCode.W) {
                if (snake.getCurDirection() != Direction.DOWN) {
                    snake.setDirection(Direction.UP);
                }
            }
            else if (kc == KeyCode.DOWN || kc == KeyCode.S) {
                if (snake.getCurDirection() != Direction.UP) {
                    snake.setDirection(Direction.DOWN);
>>>>>>> temp
                }
            }
        });
    }
    

}