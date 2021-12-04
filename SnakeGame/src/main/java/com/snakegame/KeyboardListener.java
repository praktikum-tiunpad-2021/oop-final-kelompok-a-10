package com.snakegame;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyboardListener{
    public void listenKey(Scene scene, Snake snake){
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
                }
            }
        });
    }
}